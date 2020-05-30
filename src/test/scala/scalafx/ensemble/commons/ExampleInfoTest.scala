package scalafx.ensemble.commons

import java.io.File

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers._

import scala.io.Source


class ExampleInfoTest extends AnyFlatSpec {

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
