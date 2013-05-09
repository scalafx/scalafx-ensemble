
name := "scalafxEnsemble"

version := "1.0-SNAPSHOT"

organization := "org.scalafx"

scalaVersion := "2.10.1"

assemblySettings

libraryDependencies ++= Seq(
  "org.scalafx" %% "scalafx" % "1.0.0-M3",
  "org.scalatest" %% "scalatest" % "1.9.1" % "test"
)

shellPrompt := { state => System.getProperty("user.name") + "> " }

// add JavaFX 2.0 to the unmanaged classpath
// unmanagedJars in Compile += Attributed.blank(file(System.getenv("JAVAFX_HOME") + "/rt/lib/jfxrt.jar"))
// For Java 7 update 06 the JFXRT JAR is part of the Java Runtime Environment
unmanagedJars in Compile += Attributed.blank(file(System.getenv("JAVA_HOME") + "/jre/lib/jfxrt.jar"))
