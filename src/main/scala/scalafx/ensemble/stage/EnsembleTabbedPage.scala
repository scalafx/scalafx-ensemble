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

object MockContent {
  def giveMockContent() = {
    new VBox {
      vgrow = javafx.scene.layout.Priority.ALWAYS
      hgrow = javafx.scene.layout.Priority.ALWAYS
      style = "-fx-padding: 8px"
      content = List(
        new Text {
          text = "Samples"
          font = new Font("Sans-serif", 30)
          style = "-fx-font-weight: bold;"
        }, new TextField)
    }
  }
}

class EnsembleTabbedPage(tabPane: TabPane) {
  def drawPage() = {
    tabPane.getTabs().get(0).setContent(
        EnsembleTabbedPage.buildTabContent(MockContent.giveMockContent))
    tabPane    
  }
}

