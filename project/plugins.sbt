scalacOptions ++= Seq("-unchecked", "-deprecation")

addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "2.3.0")

addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.5.1")

// assembly plugin to package and run the app
addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.9.2")

resolvers += "sbt-assembly-resolver-0" at "http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases"



