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

package scalafx.ensemble.sbt

import java.io.{IOException, File}
import java.nio.file.{StandardCopyOption, Files}
import scala.io.Source
import scalafx.ensemble.commons.ExampleInfo

/** Creates SBT project for a sample code. */
object SBTProjectBuilder {

  private var _parentDir = new File(System.getProperty("user.home", ".")).getCanonicalFile

  private val sourceSubDir = "src/main/scala/"
  private val resourceSubDir = "src/main/resources/"

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
    * @param sampleInfo information about the sample code
    * @throws FileAlreadyExistsException - if `projectDir` exists but is not a directory
    * @throws IOException - if an I/O error occurs
    */
  def createSampleProject(projectDir: File, sampleInfo: ExampleInfo) {

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

    val sampleSubDir = sourceSubDir + sampleInfo.packagePath

    // Write sample Scala code
    val samplePath = new File(projectDir, sampleSubDir + "/" + sampleInfo.classSimpleName + ".scala").toPath
    Files.createDirectories(samplePath.getParent)
    Files.write(samplePath, sampleInfo.sourceCode.getBytes)

    // Copy resources, if used by the sample.
    sampleInfo.resources.foreach(resource => copyResource(new File(projectDir, resourceSubDir), resource))

    // Copy project files
    copyText(projectDir, "build.sbt",
      filters = List("@name@" -> projectName, "@mainClass@" -> (sampleInfo.packageName + "." + sampleInfo.classSimpleName)))
    copyText(projectDir, "project/build.properties")
    copyText(projectDir, "project/plugins.sbt")
    copyText(projectDir, "README.md")
  }

  /** Copy text resource from the classpath relative to this object to a `projectDir`.
    * Line ending will be changed to platform specific.
    */
  private def copyText(projectDir: File, fileName: String, filters: List[(String, String)] = Nil) {
    /** Apply all filters in turn. */
    def filter(string: String, filters: List[(String, String)]): String = {
      filters match {
        case Nil       => string
        case f :: tail => filter(string.replaceAll(f._1, f._2), tail)
      }
    }

    try {
      val uri = this.getClass.getResource(fileName).toURI
      val contentRaw = Source.fromFile(uri).getLines().mkString("\n")
      val content = filter(contentRaw, filters)
      val path = new File(projectDir, fileName).toPath
      Files.createDirectories(path.getParent)
      Files.write(path, content.getBytes)
    } catch {
      case t: Throwable =>
        throw new IOException("Error while creating SBT project. Failed to copy text file: " + fileName, t)
    }
  }

  /** Copy a resource that may be an image or other binary file. */
  private def copyResource(projectDir: File, fileName: String) {
    try {
      val uri = this.getClass.getResource(fileName).toURI
      val src = new File(uri).toPath
      val dest = new File(projectDir, fileName).toPath
      Files.createDirectories(dest.getParent)
      Files.copy(src, dest, StandardCopyOption.REPLACE_EXISTING)
    } catch {
      case t: Throwable =>
        throw new IOException("Error while creating SBT project. Failed to copy resource: " + fileName, t)
    }
  }

}
