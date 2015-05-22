import java.io.File

name := "scalafx-ensemble"

version := "1.0-SNAPSHOT"

organization := "org.scalafx"

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  "org.scalafx" %% "scalafx" % "8.0.40-R8",
  "org.scala-lang.modules" %% "scala-xml" % "1.0.3"
)

resolvers += Opts.resolver.sonatypeSnapshots

scalacOptions ++= Seq("-unchecked", "-deprecation", "-Xlint")

// Sources should also be copied to output, so the sample code, for the viewer,
// can be loaded from the same file that is used to execute the example
unmanagedResourceDirectories in Compile <+= baseDirectory {_ / "src/main/scala"}

// Set the prompt (for this build) to include the project id.
shellPrompt := { state => System.getProperty("user.name") + ":" + Project.extract(state).currentRef.project + "> " }

// Run in separate VM, so there are no issues with double initialization of JavaFX
fork := true

fork in Test := true

resourceGenerators in Compile <+= Def.task {

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

  def transformVersionTemplate(inSourceDir: File,
                               outSourceDir: File,
                               templatePath: String): Seq[File] = {
    val exampleDirs = loadExampleNames(inSourceDir)
    val contents = exampleDirs.map { case (dir, leaves) => dir + " -> " + leaves.mkString(", ") }.mkString("\n")
    val outFile = new File(outSourceDir, templatePath)
    IO.write(outFile, contents)

    Seq(outFile)
  }

  transformVersionTemplate(
    (scalaSource in Compile).value,
    (resourceManaged in Compile).value,
    "/scalafx/ensemble/example/example.tree"
  )
}

mainClass in assembly := Some("scalafx.ensemble.Ensemble")