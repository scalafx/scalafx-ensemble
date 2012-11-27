package scalafx.ensemble.commons

import scalafx.scene.Node
import scalafx.scene.layout.VBox
import scalafx.ensemble.stage.DashboardPage
import scalafx.ensemble.stage.EnsembleTabbedPage

trait EnsembleExample {
  def getContent: Node
}

object ContentFactory {
  def createContent(ctrlName: String, ctrlgroupName: String = "") = {
    val qualCtrl = "scalafx.ensemble.example." + ctrlgroupName + ".Ensemble" + ctrlName
    var cache = Map[String, EnsembleExample]()
    if (cache.get(qualCtrl).isDefined) {
      cache.get(qualCtrl).get.getContent
    } else {
      val inst = Class.forName(qualCtrl).newInstance().asInstanceOf[EnsembleExample]
      cache = cache.+((qualCtrl, inst))
      println(cache)
      inst.getContent
    }
  }
}

/**
 * the class that displays content
 * based on the TreeItem selected from left pane
 */
object PageDisplayer {

  def choosePage(value: String = "dashBoard"): Node = {
    value match {
      case "dashBoard" => {
        displayPage(new DashboardPage())
      }
      case _ => {
        displayPage(EnsembleTabbedPage.buildTab(value,"controls"))
      }
    }
  }

  private def displayPage(nodeToAdd: DisplayablePage): Node = {
    val pageContent = new VBox {
      vgrow = javafx.scene.layout.Priority.ALWAYS
      hgrow = javafx.scene.layout.Priority.ALWAYS
    }
    pageContent.content.removeAll()
    pageContent.content.add(nodeToAdd.getPage)
    pageContent
  }

}

trait DisplayablePage {
  def getPage: Node
}