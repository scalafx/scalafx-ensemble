package scalafx.ensemble.stage

import scalafx.ensemble.EnsembleTree
import scalafx.ensemble.commons.DisplayablePage
import scalafx.scene.control.ScrollPane
import scalafx.scene.layout.Priority
import scalafx.scene.layout.VBox
import scalafx.stage.Screen

// Dashboard 
class DashboardPage(dashPart: String = "dashboard") extends DisplayablePage {
  val tree = EnsembleTree.create
  val adjustFactor = 140
  val screen :Screen = Screen.primary
  def getPage = {
    val boxes = new VBox {
      vgrow = Priority.ALWAYS
      hgrow = Priority.ALWAYS
      minWidth = screen.getVisualBounds().getWidth()-130
     //  styleClass.add("category-header")
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

    val scrollPane = new ScrollPane {
      vgrow = Priority.ALWAYS
      hgrow = Priority.ALWAYS
      fitToHeight = true
      fitToWidth = true
      content = boxes
      styleClass.add("category-header")
    }

    scrollPane
  }
}

