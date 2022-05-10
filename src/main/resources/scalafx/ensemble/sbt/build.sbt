name := "@name@"

version := "1.19"

scalaVersion := "2.13.8"

libraryDependencies += "org.scalafx" %% "scalafx" % "17.0.1-R26"

// Determine OS version of JavaFX binaries
lazy val osName = System.getProperty("os.name") match {
  case n if n.startsWith("Linux")   => "linux"
  case n if n.startsWith("Mac")     => "mac"
  case n if n.startsWith("Windows") => "win"
  case _                            => throw new Exception("Unknown platform!")
}

// Add JavaFX dependencies
lazy val javaFXModules = Seq("base", "controls", "fxml", "graphics", "media", "swing", "web")
libraryDependencies ++= javaFXModules.map(m =>
  "org.openjfx" % s"javafx-$m" % "17.0.1" classifier osName
)

scalacOptions ++= Seq("-unchecked", "-deprecation")

Compile / mainClass := Some("@mainClass@")

// Run in separate VM, so there are no issues with double initialization of JavaFX
fork := true

Test / fork := true
