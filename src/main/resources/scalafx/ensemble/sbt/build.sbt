name := "@name@"

version := "1.20"

scalaVersion := "3.1.3"

libraryDependencies += "org.scalafx" %% "scalafx" % "18.0.1-R28"

Compile / mainClass := Some("@mainClass@")

// Run in separate VM, so there are no issues with double initialization of JavaFX
fork := true

Test / fork := true
