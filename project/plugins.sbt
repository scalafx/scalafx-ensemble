scalacOptions ++= Seq("-unchecked", "-deprecation")

// assembly plugin to package and run the app
// [https://github.com/sbt/sbt-assembly]
addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.10")

// Plugin to create native installers
addSbtPlugin("com.typesafe.sbt" %% "sbt-native-packager" % "1.4.1")

