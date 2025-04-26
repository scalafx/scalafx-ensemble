//import java.io.File

name := "ScalaFX Ensemble"

version := "24.0.0-R35"

organization := "org.scalafx"

val scala2Version = "2.13.16"
val scala3Version = "3.6.4"
// To cross compile with Scala 2 and Scala 3
crossScalaVersions := Seq(scala3Version, scala2Version)
scalaVersion       := scala3Version

libraryDependencies ++= Seq(
  "org.scalafx"   %% "scalafx"   % "24.0.0-R35",
  "org.scalatest" %% "scalatest" % "3.2.19"
)

resolvers ++= Resolver.sonatypeOssRepos("snapshots")

scalacOptions ++= Seq("-unchecked", "-deprecation")
scalacOptions ++= (
  CrossVersion.partialVersion(scalaVersion.value) match {
    case Some((3, _)) =>
      Seq("-explain", "-explain-types")
    case _ =>
      Seq("-Xlint", "-explaintypes", "-Xsource:3", "-Xmigration")
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

  /**
   * Scan source directory for available examples
   * Return pairs 'directory' -> 'collection of examples in that directory'.
   */
  def loadExampleNames(inSourceDir: File): Array[(String, Array[String])] = {
    val examplesDir      = "/scalafx/ensemble/example/"
    val examplePath      = new File(inSourceDir, examplesDir)
    val exampleRootFiles = examplePath.listFiles()
    for (dir <- exampleRootFiles if dir.isDirectory) yield {
      val leaves = for (f <- dir.listFiles() if f.getName.contains(".scala")) yield {
        f.getName.stripSuffix(".scala").stripPrefix("Ensemble")
      }
      dir.getName.capitalize -> leaves.sorted
    }
  }

  /**
   * Create file representing names and directories for all availabe examples.
   * It will be loaded by the application at runtime and used to popolate example tree.
   */
  def generateExampleTreeFile(inSourceDir: File, outSourceDir: File, templatePath: String): Seq[File] = {
    val exampleDirs = loadExampleNames(inSourceDir)
    val contents    = exampleDirs.map { case (dir, leaves) => dir + " -> " + leaves.mkString(", ") }.mkString("\n")
    val outFile     = new File(outSourceDir, templatePath)
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
