package scalafx.ensemble.stage

import scalafx.scene.Scene
import scalafx.scene.layout.BorderPane
import scalafx.scene.layout.VBox
import scalafx.scene.control.ToolBar
import scalafx.scene.image.ImageView
import scalafx.scene.image.Image
import scalafx.geometry.Insets
import scalafx.scene.layout.Region
import scalafx.scene.control.Button
import scalafx.scene.control.SplitPane
import scalafx.scene.text.Text
import scalafx.scene.control.Label
import scalafx.scene.layout.HBox
import scalafx.scene.control.Accordion
import scalafx.scene.control.TitledPane
import scalafx.scene.shape.Arc
import scalafx.scene.paint.Color
import scalafx.scene.control.ScrollPane
import scalafx.scene.layout.TilePane
import scalafx.scene.text.Font
import javafx.geometry.Orientation
import javafx.geometry.Pos
import scalafx.scene.layout.FlowPane
import javafx.scene.text.TextAlignment
import scalafx.scene.Node
import scalafx.scene.control.TextField
import scalafx.ensemble.commons.DisplayablePage
import scalafx.ensemble.EnsembleTree

// Dashboard 
class DashboardPage(dashPart: String = "dashboard") extends DisplayablePage {
  val tree = EnsembleTree.create
  def getPage = {
    val boxes = new VBox {
      vgrow = javafx.scene.layout.Priority.ALWAYS
      hgrow = javafx.scene.layout.Priority.ALWAYS
      styleClass.add("category-header")
    }

    dashPart match {
      case "dashboard" => {
        tree.getDashThumbsCtrl.foreach(box => {
          boxes.content.add(box)
        })
      }
      case _ => {
        tree.getDashThumb(dashPart).foreach(box => {
          boxes.content.add(box)
        })
      }
    }

    boxes
  }
}

