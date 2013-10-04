/*
 * Copyright (c) 2013, ScalaFX Ensemble Project
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
package scalafx.ensemble.sbt

import java.io.File
import java.nio.file.Files
import scala.io.Source

/** Creates SBT project for a sample code. */
object SBTProjectBuilder {

  private var _parentDir = new File(System.getProperty("user.home", ".")).getCanonicalFile

  private val sourceSubDir = "src/main/scala"

  /** Last used parent directory of a saved project or users home directory */
  def parentDir: File = synchronized {
    _parentDir
  }

  def parentDir_=(dir: File) = synchronized {
    _parentDir = dir
  }


  /** Create and save SBT project for a sample.
    *
    * @param projectDir directory where to save the project
    * @param source source of the sample
    * @throws FileAlreadyExistsException - if `projectDir` exists but is not a directory
    * @throws IOException - if an I/O error occurs
    */
  def createSampleProject(projectDir: File, source: String) {
    val sampleClassName = extractClassName(source)

    // extract project name
    val projectName = {
      val path = {
        val p = projectDir.toURI.toString
        if (p.last == '/') p.dropRight(1) else p
      }
      path.substring(path.lastIndexOf('/') + 1)
    }

    // Create project directory
    Files.createDirectories(projectDir.toPath)

    val samplePackage = extractPackageName(source)
    val sampleSubDir = sourceSubDir + "/" + samplePackage.replaceAll("\\.", "/")

    // Write sample Scala code
    val samplePath = new File(projectDir, sampleSubDir + "/" + sampleClassName + ".scala").toPath
    Files.createDirectories(samplePath.getParent)
    Files.write(samplePath, source.getBytes)

    // Copy project files
    copy(projectDir, "build.sbt",
      filters = List("@name@" -> projectName, "@mainClass@" -> (samplePackage + "." + sampleClassName)))
    copy(projectDir, "project/build.properties")
    copy(projectDir, "project/plugins.sbt")
  }

  /** Determine samples package stated in sample source code. */
  private def extractPackageName(source: String): String = {
    val packageNamePattern = ".*package\\s(\\S*)".r
    packageNamePattern findFirstIn source match {
      case Some(packageNamePattern(packageName)) => packageName
      case None => ""
    }
  }

  /** Determine name of the main sample's class. */
  private def extractClassName(source: String): String = {
    val classNamePattern = "object\\s*(\\S*)\\s*extends\\s*JFXApp".r
    classNamePattern findFirstIn source match {
      case Some(classNamePattern(className)) => className
      case None => throw new IllegalArgumentException("Cannot extract sample class name.")
    }
  }

  /** Copy resource from the classpath relative to this object to a `projectDir`. */
  private def copy(projectDir: File, fileName: String, filters: List[(String, String)] = Nil) {
    /** Apply all filters in turn. */
    def filter(string: String, filters: List[(String, String)]): String = {
      filters match {
        case Nil => string
        case f :: tail => filter(string.replaceAll(f._1, f._2), tail)
      }
    }

    val uri = this.getClass.getResource(fileName).toURI
    val contentRaw = Source.fromFile(uri).getLines().mkString("\n")
    val content = filter(contentRaw, filters)
    val path = new File(projectDir, fileName).toPath
    Files.createDirectories(path.getParent)
    Files.write(path, content.getBytes)
  }
}
