name := "@name@"

version := "1.22"

scalaVersion := "3.3.1"

libraryDependencies += "org.scalafx" %% "scalafx" % "21.0.0-R32"

Compile / mainClass := Some("@mainClass@")

// Run in separate VM, so there are no issues with double initialization of JavaFX
fork := true

Test / fork := true
