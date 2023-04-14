name := "@name@"

version := "1.21"

scalaVersion := "3.2.2"

libraryDependencies += "org.scalafx" %% "scalafx" % "20.0.0-R31"

Compile / mainClass := Some("@mainClass@")

// Run in separate VM, so there are no issues with double initialization of JavaFX
fork := true

Test / fork := true
