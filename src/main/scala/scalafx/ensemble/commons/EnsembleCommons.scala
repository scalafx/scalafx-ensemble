package scalafx.ensemble.commons

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.URISyntaxException

import scalafx.Includes._
import scalafx.ensemble.EnsembleThumbNail
import scalafx.ensemble.stage.DashboardPage
import scalafx.ensemble.stage.EnsembleTabbedPage
import scalafx.scene.Node
//import scalafx.scene.Node.sfxNode2jfx
//import scalafx.scene.control.Label.sfxLabel2jfx
import scalafx.scene.control.ScrollPane
import scalafx.scene.control.TreeItem
import scalafx.scene.control.TreeItem.sfxTreeItemTojfx
import scalafx.scene.layout.BorderPane
import scalafx.scene.layout.VBox
import scalafx.scene.web.WebView
//import scalafx.scene.web.WebView.sfxWebView2jfx

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
    val borderPane = new BorderPane

    //Construct content of the samples dynamically
    val qualCtrl = "scalafx.ensemble.example." +
      ctrlgroupName + ".Ensemble" + ctrlName
    var cache = Map[String, EnsembleExample]()
    if (cache.get(qualCtrl).isDefined) {
      borderPane.setCenter(cache.get(qualCtrl).get.getContent)
    } else {
      val inst = Class.forName(qualCtrl).newInstance().asInstanceOf[EnsembleExample]
      cache = cache.+((qualCtrl, inst))
      borderPane.setCenter(inst.getContent)
    }
    //Scrollpane is applied for borderpane that contains samples
    val scrollPane = new ScrollPane {
      fitToWidth = true
      fitToHeight = true
      minWidth = 725
      content = borderPane
    }
    scrollPane.getStyleClass().add("noborder-scroll-pane")
    scrollPane
  }

  def createSrcContent(ctrlName: String, ctrlgroupName: String = "") = {
    //read function to read the file content one by one 
    val readFun = (s: String, builder: StringBuilder) => {
      s match {
        case null => false
        case _ => builder.append(s); builder.append("<br/>"); true
      }
    }
    // File to read src file
    val file = this.getClass().getResource(
      "/ensemble/examples/" + ctrlgroupName + "/" + ctrlName + ".txt")
    /*val src = scala.io.Source.fromFile(file.getFile())*/
    // Stringbuilder to store src code lines
    val builder = new StringBuilder().append("<html><head></head><body><div>")

    try {
      // load src into String
      val in = file.openStream()
      val reader = new BufferedReader(new InputStreamReader(in))
      var isContent = true
      do {
        isContent = readFun(reader.readLine(), builder)
      } while (isContent)
      reader.close()
    } catch {
      case cnfe: ClassNotFoundException => cnfe.printStackTrace()
      case urie: URISyntaxException => urie.printStackTrace()
      case ioe: IOException => ioe.printStackTrace()
    }
    // Complete the html content
    builder.append("</div></body></html>")

    //Border pane is sufficient to handle the content
    val borderPane = new BorderPane() {
      // Load source through webview
      center = new WebView() {
        contextMenuEnabled = false
        prefWidth = 300
        this.engine.loadContent(builder.mkString)
      }
    }
    /*
    val srcSp = new ScrollPane
    val srcCode = new Label
    srcCode.text = src.mkString
    src.close
    srcSp.fitToHeight = true
    srcSp.fitToWidth = true
    srcSp.content = srcCode
    srcSp*/
    borderPane
  }
}

trait EnsembleExample {
  def getContent: Node
}

trait DisplayablePage {
  def getPage: Node
}
