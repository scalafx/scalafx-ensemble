scalacOptions ++= Seq("-unchecked", "-deprecation")

addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "3.0.0")

// assembly plugin to package and run the app
addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.13.0")

// Plugin to create native installers
addSbtPlugin("com.typesafe.sbt" %% "sbt-native-packager" % "1.0.2")

