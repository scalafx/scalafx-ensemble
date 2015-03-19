name := "@name@"

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.6"

libraryDependencies += "org.scalafx" %% "scalafx" % "8.0.40-R8"

scalacOptions ++= Seq("-unchecked", "-deprecation")

mainClass in Compile := Some("@mainClass@")

// Run in separate VM, so there are no issues with double initialization of JavaFX
fork := true

fork in Test := true
