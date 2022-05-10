//import java.io.File

name := "ScalaFX Ensemble"

version := "17.0.1-R26-SNAPSHOT"

organization := "org.scalafx"

val scala2Version = "2.13.8"
val scala3Version = "3.1.2"
// To cross compile with Scala 2 and Scala 3
crossScalaVersions := Seq(scala2Version, scala3Version)
scalaVersion := scala2Version

//@formatter:off
libraryDependencies ++= Seq(
  "org.scalafx"   %% "scalafx"   % "17.0.1-R26",
  "org.scalatest" %% "scalatest" % "3.2.10"
  )

// Add OS specific JavaFX dependencies
libraryDependencies ++= {
  val osName = System.getProperty("os.name") match {
    case n if n.startsWith("Linux")   => "linux"
    case n if n.startsWith("Mac")     => "mac"
    case n if n.startsWith("Windows") => "win"
    case _                            => throw new Exception("Unknown platform!")
  }
  Seq("base", "controls", "fxml", "graphics", "media", "swing", "web")
    .map(m => "org.openjfx" % s"javafx-$m" % "17.0.1" classifier osName)
}
//@formatter:on

scalacOptions ++= Seq("-unchecked", "-deprecation")
scalacOptions ++= (
  CrossVersion.partialVersion(scalaVersion.value) match {
    case Some((3, _)) =>
      Seq("-explain", "-explain-types")
    case _ =>
      Seq("-Xlint", "-explaintypes")
  }
  )

// Sources should also be copied to output, so the sample code, for the viewer,
// can be loaded from the same file that is used to execute the example
Compile / unmanagedResourceDirectories += baseDirectory(_ / "src/main/scala").value

// Set the prompt (for this build) to include the project id.
shellPrompt := { state => System.getProperty("user.name") + ":" + Project.extract(state).currentRef.project + "> " }

// Run in separate VM, so there are no issues with double initialization of JavaFX
fork := true

Test / fork := true

// Create file used to determine available examples at runtime.
Compile / resourceGenerators += Def.task {
  /** Scan source directory for available examples
    * Return pairs 'directory' -> 'collection of examples in that directory'.
    */
  def loadExampleNames(inSourceDir: File): Array[(String, Array[String])] = {
    val examplesDir = "/scalafx/ensemble/example/"
    val examplePath = new File(inSourceDir, examplesDir)
    val exampleRootFiles = examplePath.listFiles()
    for (dir <- exampleRootFiles if dir.isDirectory) yield {
      val leaves = for (f <- dir.listFiles() if f.getName.contains(".scala")) yield {
        f.getName.stripSuffix(".scala").stripPrefix("Ensemble")
      }
      dir.getName.capitalize -> leaves.sorted
    }
  }

  /** Create file representing names and directories for all availabe examples.
    * It will be loaded by the application at runtime and used to popolate example tree.
    */
  def generateExampleTreeFile(inSourceDir : File,
                              outSourceDir: File,
                              templatePath: String): Seq[File] = {
    val exampleDirs = loadExampleNames(inSourceDir)
    val contents = exampleDirs.map { case (dir, leaves) => dir + " -> " + leaves.mkString(", ") }.mkString("\n")
    val outFile = new File(outSourceDir, templatePath)
    IO.write(outFile, contents)

    Seq(outFile)
  }

  generateExampleTreeFile(
    (Compile / scalaSource).value,
    (Compile / resourceManaged).value,
    "/scalafx/ensemble/example/example.tree"
    )
}.taskValue

Compile / mainClass := Some("scalafx.ensemble.Ensemble")
