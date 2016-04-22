scalacOptions ++= Seq("-unchecked", "-deprecation")

addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "4.0.0")

// assembly plugin to package and run the app
// [https://github.com/sbt/sbt-assembly]
addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.3")

// Plugin to create native installers
addSbtPlugin("com.typesafe.sbt" %% "sbt-native-packager" % "1.0.6")

