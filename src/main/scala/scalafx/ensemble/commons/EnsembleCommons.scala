package scalafx.ensemble.commons

import scalafx.scene.Node
import scalafx.scene.layout.VBox
import scalafx.ensemble.stage.DashboardPage
import scalafx.ensemble.stage.EnsembleTabbedPage
import scalafx.stage.Screen
import scalafx.ensemble.EnsembleThumbNail
import scalafx.scene.control.TreeItem

/**
 * populates the tabbed content by loading
 * EnsembleExample instance
 */
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
      case _ => {
        if (value.startsWith("dashBoard - ")) {
          displayPage(new DashboardPage((value.split("-")(1)).trim()))
        } else {
          displayPage(EnsembleTabbedPage.buildTab(value, "controls"))
        }
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

/**
 * utility to sort the items
 */
object SortUtils {

  def treeItemSort = (ti: TreeItem[String], t2: TreeItem[String]) =>
    compare(ti.getValue(), t2.getValue())

  def thumbNailsSort = (t1: EnsembleThumbNail, t2: EnsembleThumbNail) =>
    compare(t1.caption.getText(), t2.caption.getText())

  private def compare = (x: String, y: String) =>
    x.toLowerCase() < y.toLowerCase()
}
