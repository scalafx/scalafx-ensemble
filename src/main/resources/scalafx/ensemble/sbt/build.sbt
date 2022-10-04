name := "@name@"

version := "1.20"

scalaVersion := "3.2.0"

libraryDependencies += "org.scalafx" %% "scalafx" % "19.0.0-R30"

Compile / mainClass := Some("@mainClass@")

// Run in separate VM, so there are no issues with double initialization of JavaFX
fork := true

Test / fork := true
