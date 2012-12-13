package scalafx.ensemble.commons

import java.io.BufferedReader
import java.io.FileReader
import scalafx.ensemble.EnsembleThumbNail
import scalafx.ensemble.stage.DashboardPage
import scalafx.ensemble.stage.EnsembleTabbedPage
import scalafx.scene.Node
import scalafx.scene.control.TreeItem
import scalafx.scene.layout.VBox
import scala.io.Source
import scalafx.scene.text.Text
import scalafx.scene.control.Label
import scalafx.scene.control.ScrollPane

/**
 * the class that updates tabbed view or dashboard view
 * based on the TreeItem selected from left pane
 */
object PageDisplayer {

  def choosePage(value: String = "dashBoard"): Node = {
    value match {
      case "dashBoard" => {
        displayPage(new DashboardPage)
      }
      case _ => {
        if (value.startsWith("dashBoard - ")) {
          displayPage(new DashboardPage((value.split("-")(1)).trim()))
        } else {
          displayPage(EnsembleTabbedPage.buildTab(value.split(">")(1).trim(),
            value.split(">")(0).trim()))
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

/**
 * utility to sort the items
 */
object SortUtils {

  def treeItemSort = (ti: TreeItem[String], t2: TreeItem[String]) =>
    compare(ti.getValue(), t2.getValue())

  def thumbNailsSort = (t1: EnsembleThumbNail, t2: EnsembleThumbNail) =>
    compare(t1.caption.getText(), t2.caption.getText())

  def sortKeys = (x: String, y: String) => compare(x, y)

  private def compare = (x: String, y: String) =>
    x.compareToIgnoreCase(y) < 0
}

/**
 * populates the tabbed content by loading
 * EnsembleExample instance
 */
object ContentFactory {
  def createContent(ctrlName: String, ctrlgroupName: String = "") = {
    val qualCtrl = "scalafx.ensemble.example." +
      ctrlgroupName + ".Ensemble" + ctrlName
    var cache = Map[String, EnsembleExample]()
    if (cache.get(qualCtrl).isDefined) {
      cache.get(qualCtrl).get.getContent
    } else {
      val inst = Class.forName(qualCtrl).newInstance().asInstanceOf[EnsembleExample]
      cache = cache.+((qualCtrl, inst))
      inst.getContent
    }
  }

  def createSrcContent(ctrlName: String, ctrlgroupName: String = "") = {
    val file = this.getClass().getResource(
      "/ensemble/examples/" + ctrlgroupName + "/" + ctrlName + ".txt")
    val src = scala.io.Source.fromFile(file.getFile())
    val srcSp = new ScrollPane
    val srcCode = new Label
    srcCode.text = src.mkString
    src.close
    srcSp.fitToHeight = true
    srcSp.fitToWidth = true
    srcSp.content = srcCode
    srcSp
  }
}

trait EnsembleExample {
  def getContent: Node
}

trait DisplayablePage {
  def getPage: Node
}
