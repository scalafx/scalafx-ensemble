//
// Install4J Setup
//

exportJars := true

enablePlugins(SBTInstall4J)
//install4jcFile := file("C:/Program Files/install4j8/bin/install4jc.exe")
install4jProjectFile := "installer/ScalaFX_Ensemble.install4j"
install4jRelease := version.value
//install4jVerbose := true
