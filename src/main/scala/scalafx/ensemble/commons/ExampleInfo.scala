/*
 * Copyright (c) 2012-2015, ScalaFX Ensemble Project
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the ScalaFX Project nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE SCALAFX PROJECT OR ITS CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED
 * AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package scalafx.ensemble.commons

import scala.util.matching.Regex


object ExampleInfo {

  val examplesDir = "/scalafx/ensemble/example/"

  def formatAddSpaces(name: String): String =
    name.replaceAll( """([\p{Upper}\d])""", " $1").trim

  def formatNoSpaces(name: String): String = name.replaceAllLiterally(" ", "")

  def thumbnailPath(exampleName: String, groupName: String): String =
    examplesDir + groupName.toLowerCase + "/" + formatNoSpaces(exampleName) + "Sample.png"

  def sourcecodePath(exampleName: String, groupName: String): String =
    examplesDir + groupName.toLowerCase + "/" + "Ensemble" + formatNoSpaces(exampleName) + ".scala"

  def className(exampleName: String, groupName: String): String =
    "scalafx.ensemble.example." + groupName.toLowerCase + ".Ensemble" + ExampleInfo.formatNoSpaces(exampleName)
}

/** Creates stand alone example source code. */
class ExampleInfo(exampleName: String, exampleGroupName: String) {

  import ExampleInfo._

  /** Source code for the sample. */
  lazy val sourceCode: String = loadAndConvertSourceCode(sourcecodePath(exampleName, exampleGroupName))

  /** Name of example's main class, extracted from the source code, excluding package prefix. */
  lazy val classSimpleName: String = {
    val pattern = "object\\s*(\\S*)\\s*extends\\s*JFXApp".r
    pattern findFirstIn sourceCode match {
      case Some(pattern(name)) => name
      case None                => throw new IllegalArgumentException("Cannot extract sample class name.")
    }
  }

  /** Samples package stated in sample source code. */
  lazy val packageName: String = extractPackageName(sourceCode)

  /** Samples package stated in sample source code, as a path, with `/` instead of `.` */
  lazy val packagePath: String = packageName.replaceAll("\\.", "/")

  /** Collection of resources used by this example */
  lazy val resources: Set[String] = {
    def extract(pattern: Regex): Seq[String] = {
      val resources = for (pattern(resourcePath) <- pattern findAllIn sourceCode) yield resourcePath
      resources.map(r => if (r.startsWith("/")) r else "/" + packagePath + "/" + r).toSeq
    }

    extract( """@resource\s*(\S*)""".r).toSet
  }

  private def extractPackageName(source: String): String = {
    val pattern = ".*package\\s(\\S*)".r
    pattern findFirstIn source match {
      case Some(pattern(name)) => name.trim
      case None                => ""
    }
  }

  private def extractSampleName(source: String): String = {
    val pattern = """class\s*Ensemble(\S*)\s*extends\s*EnsembleExample\s*\{""".r
    pattern findFirstIn source match {
      case Some(pattern(name)) => name.trim
      case None                => ""
    }
  }

  private def extractStageProperties(sourceRaw: String): Seq[String] = {
    val pattern = """@stage-property\s*(.*)""".r
    val properties = for (pattern(property) <- pattern findAllIn sourceRaw) yield property.trim
    properties.toSeq
  }

  private def loadAndConvertSourceCode(path: String): String = {

    // Load source code text
    val sourceRaw = IOUtils.loadResourceAsString(this, path)

    // Collect metadata from comments
    val stageProperties = extractStageProperties(sourceRaw)

    // Remove initial comment
    var source = sourceRaw.replaceFirst( """(?s)/\*(.*?)\*/""", "")

    // Remove package statement, for a time being, so it is easier to prepend to the source code
    val originalPackageName = extractPackageName(source)
    source = source.replaceFirst( """package\s*\S*""", "")

    // Remove empty lines at the beginning
    source = source.replaceFirst( """(?s)\s*""", "")

    // Remove information about added properties
    source = source.replaceAll( """\s*//\s*@stage-property\s*(.*)""", "")

    // Append copyright, package, and required imports
    source = "" +
      "/*\n" +
      " * Copyright 2013 ScalaFX Project\n" +
      " * All right reserved.\n" +
      " */\n" +
      (if (!originalPackageName.isEmpty) "package " + originalPackageName + "\n" else "") +
      "\n" +
      "import scalafx.application.JFXApp\n" +
      "import scalafx.scene.Scene\n" +
      source

    // Remove local imports
    source = source.replaceAll( """import scalafx.ensemble.\S*\s*""", "")

    // Change `class ExampleSomething extends EnsembleExample {`
    // to     `object SomethingSample extends JFXApp
    source = source.replaceFirst(
      """class\s*Ensemble(\S*)\s*extends\s*EnsembleExample\s*\{""",
      """object $1Sample extends JFXApp {""")

    // Replace `getContent` method with stage and scene creation
    val stageHeader = "" +
      "\n\n" +
      "  stage = new JFXApp.PrimaryStage {\n" +
      "    title = \"" + formatAddSpaces(extractSampleName(sourceRaw)) + " Example\"\n" +
      (if (stageProperties.isEmpty) "" else stageProperties.mkString("    ", "\n    ", "\n")) +
      "    scene = new Scene {\n" +
      "      root ="
    source = source.replaceFirst( """\s*def\s*getContent\s*=""", stageHeader)

    // Cleanup extra carriage-return characters
    source = source.replaceAll( """\r\n""", "\n")

    // Locate code that needs additional braces since two were introduced in `stageHeader`
    val openingBraceIndex = {
      val start = source.indexOf(stageHeader)
      require(start >= 0, "Internal error, failed to find `stageHeader`.")
      source.indexOf("{", start + stageHeader.length)
    }
    require(openingBraceIndex >= 0, "Internal error, failed to find `stageHeader`.")
    // Get index of closing brace
    val closingBraceIndex = {
      var braceCount = 1
      var index = openingBraceIndex
      while (braceCount > 0) {
        index += 1
        source(index) match {
          case '{' => braceCount += 1
          case '}' => braceCount -= 1
          case _   =>
        }
      }
      index
    }

    // Ident body of the code that used to be `getContent` but now is assigned to scene.root.
    val prefix = source.substring(0, openingBraceIndex + 1)
    val bodyIndented = {
      val body = source.substring(openingBraceIndex + 1, closingBraceIndex + 1)
      body.lines.mkString("\n    ")
    }
    val postfix = source.substring(closingBraceIndex + 1)

    // Combine final code
    prefix +
      bodyIndented + "\n" +
      "    }\n" +
      "  }" +
      postfix
  }
}
