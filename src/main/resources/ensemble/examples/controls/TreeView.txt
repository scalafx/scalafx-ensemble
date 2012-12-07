package scalafx.ensemble.example.controls

import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.scene.control.TreeItem
import scalafx.scene.control.TreeItem.sfxTreeItemTojfx
import scalafx.scene.control.TreeView
import scalafx.scene.layout.VBox
import scalafx.scene.text.Font
import scalafx.scene.text.Text

class EnsembleTreeView extends EnsembleExample {
  def getContent = {

    //Root Node
    val rootItem = new TreeItem[String]("Root Node") {
      expanded = true
    }
    //Node 3 has two child nodes and expanded
    val childRoot3 = new TreeItem[String] { value = "Node 3" }
    childRoot3.getChildren().addAll(
      new TreeItem[String]("Child Node 1"),
      new TreeItem[String]("Child Node 2"))

    rootItem.getChildren.addAll(
      new TreeItem[String] { value = "Node 1" },
      new TreeItem[String] { value = "Node 2" },
      childRoot3)

    new VBox {
      vgrow = javafx.scene.layout.Priority.ALWAYS
      hgrow = javafx.scene.layout.Priority.ALWAYS
      spacing = 10
      margin = Insets(50, 0, 0, 50)
      content = List(
        new Text {
          text = "Ensemble TreeView"
          font = new Font("Verdana", 20)
        },
        new Text {
          text = "------------------------------------------------------"
          font = new Font("Verdana", 8)
          style = "-fx-font-weight: bold"
        },
        new TreeView[String] {
          maxWidth = 200
          maxHeight = 200
          showRoot = true
          root = rootItem
        })
    }
  }
}