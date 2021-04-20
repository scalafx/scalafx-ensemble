/*
 * Copyright (c) 2012-2020, ScalaFX Ensemble Project
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

import org.scalatest.flatspec._
import org.scalatest.matchers._

import java.io.File
import scala.io.Source
import scala.language.implicitConversions


class ExampleInfoTest extends AnyFlatSpec with should.Matchers {

  val exampleBaseDir = new File("src/main/scala/scalafx/ensemble/example")

  it should "generate code for all examples with no errors" in {
    val dirs = exampleBaseDir.listFiles(file => file.isDirectory)
    println(s"Dirs: ${dirs.map(_.getName).mkString(", ")}")
    for (dir <- dirs) {
      val files = dir.listFiles(file => file.getName.endsWith(".scala"))
      for (file <- files) {
        val sourceRaw = loadExampleSource(file)
        ExampleInfo.convertSourceCode(sourceRaw)
      }
    }
  }

  it should "extractStageProperties" in {
    val sourceRaw = loadExampleSource("controls/EnsembleToolBar.scala")

    val props = ExampleInfo.extractStageProperties(sourceRaw)

    props should be(Seq("width = 400", "height = 150"))
  }

  def loadExampleSource(example: String): String = {
    val baseDir = new File("src/main/scala/scalafx/ensemble/example")
    val file = new File(baseDir, example)
    loadExampleSource(file)
  }

  def loadExampleSource(file: File): String = {
    val bs = Source.fromFile(file)
    try {
      bs.getLines().mkString("\n")
    } finally {
      bs.close()
    }
  }

}
