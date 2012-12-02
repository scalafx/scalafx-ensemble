package scalafx.ensemble.commons

import scalafx.scene.Node
import scalafx.scene.layout.VBox
import scalafx.ensemble.stage.DashboardPage
import scalafx.ensemble.stage.EnsembleTabbedPage

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
 * the class that updates tabbed view or dashboard view
 * based on the TreeItem selected from left pane
 */
object PageDisplayer {

  def choosePage(value: String = "dashBoard"): Node = {
    value match {
      case "dashBoard" => {
        displayPage(new DashboardPage())
      }
  //   		case "dashBoard - " => {
  //        
  //      }
      case _ => {
        displayPage(EnsembleTabbedPage.buildTab(value, "controls"))
      }
    }
  }

  private def displayPage(nodeToAdd: DisplayablePage): Node = {
    val pageContent = new VBox {
      vgrow = javafx.scene.layout.Priority.ALWAYS
      hgrow = javafx.scene.layout.Priority.ALWAYS
      styleClass.add("category-page")
    }
    pageContent.content.removeAll()
    pageContent.content.add(nodeToAdd.getPage)
    pageContent
  }

}

trait EnsembleExample {
  def getContent: Node
}

trait DisplayablePage {
  def getPage: Node
}