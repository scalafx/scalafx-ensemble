
name := "scalafxEnsemble"

version := "1.0-SNAPSHOT"

organization := "org.scalafx"

scalaVersion := "2.10.2"

assemblySettings

libraryDependencies ++= Seq(
  "org.scalafx" %% "scalafx" % "1.0.0-M5",
  "org.scalatest" %% "scalatest" % "1.9.2" % "test"
)

// Sources should also be copied to output, so the sample code, for the viewer,
// can be loaded from the same file that is used to execute the example
unmanagedResourceDirectories in Compile <+= baseDirectory { _/"src/main/scala"}

shellPrompt := { state => System.getProperty("user.name") + "> " }

// add JavaFX 2.0 to the unmanaged classpath
// unmanagedJars in Compile += Attributed.blank(file(System.getenv("JAVAFX_HOME") + "/rt/lib/jfxrt.jar"))
// For Java 7 update 06 the JFXRT JAR is part of the Java Runtime Environment
unmanagedJars in Compile += Attributed.blank(file(System.getenv("JAVA_HOME") + "/jre/lib/jfxrt.jar"))
