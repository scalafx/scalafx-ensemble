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
import scalafx.ensemble.commons.EnsembleExample
import scalafx.ensemble.commons.ContentFactory
import scalafx.ensemble.commons.DisplayablePage
import scalafx.stage.Screen

object EnsembleTabbedPage {

  def buildTab(ctrlName: String, ctrlgrop: String) = {
    val screen = Screen.primary
    val tabbedPage = new TabPane()
    tabbedPage.minHeight = screen.getBounds().getHeight()
    val demoTab = new Tab()
    demoTab.text = "Demo"
    val srcTab = new Tab()
    srcTab.text = "Source"

    demoTab.closable = false
    srcTab.closable = false

    tabbedPage.getTabs().add(demoTab)
    tabbedPage.getTabs().add(srcTab)
    new EnsembleTabbedPage(tabbedPage, ctrlName, ctrlgrop)
  }

  def buildTabContent(node: Node) = {
    val demoTabStack = new StackPane {
      alignment = Pos.CENTER
      content = List(node)
    }
    demoTabStack
  }
}

class EnsembleTabbedPage(tabPane: TabPane, ctrlName: String, ctrlGroup: String)
  extends DisplayablePage {
  def getPage() = {
    tabPane.getTabs().get(0).setContent(
      EnsembleTabbedPage.buildTabContent(ContentFactory.createContent(ctrlName, ctrlGroup)))
    tabPane
  }
}
