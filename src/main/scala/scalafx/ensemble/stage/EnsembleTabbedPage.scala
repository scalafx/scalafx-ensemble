package scalafx.ensemble.stage

import scalafx.scene.control.TabPane
import scalafx.scene.control.Tab
import scalafx.scene.control.TextField
import scalafx.scene.layout.StackPane
import javafx.geometry.Pos
import scalafx.scene.layout.VBox
import scalafx.scene.text.Text
import scalafx.scene.text.Font
import scalafx.scene.Node
import scalafx.ensemble.example.EnsembleExample

object EnsembleTabbedPage {
  
  def buildTab() = {
    val tabbedPage = new TabPane()
    val demoTab = new Tab()
    demoTab.text = "Demo"
    val srcTab = new Tab()
    srcTab.text = "Source"
    tabbedPage.getTabs().add(demoTab)
    tabbedPage.getTabs().add(srcTab)
    tabbedPage
    new EnsembleTabbedPage(tabbedPage)
  }

  def buildTabContent(node: Node) = {
    val demoTabStack = new StackPane {
      alignment = Pos.CENTER
      content = List(node)
    }
    demoTabStack
  }
}

object ContentFactory {
  def createContent(ctrlName:String) = {
    val example = Class.forName("scalafx.ensemble.example.Ensemble"+ctrlName)
    example.newInstance().asInstanceOf[EnsembleExample].getContent
  }
}

class EnsembleTabbedPage(tabPane: TabPane) {
  def drawPage() = {
    tabPane.getTabs().get(0).setContent(
        EnsembleTabbedPage.buildTabContent(ContentFactory.createContent("TextField")))
    tabPane    
  }
}

