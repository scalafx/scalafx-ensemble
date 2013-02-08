package scalafx.ensemble.example.layout

import scalafx.Includes._
import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.scene.control.Button
import scalafx.scene.control.Label
import scalafx.scene.control.TextField
import scalafx.scene.layout.HBox
import scalafx.scene.layout.VBox
import scalafx.scene.text.Font
//import scalafx.scene.text.Font.sfxFont2jfx
import scalafx.geometry.Pos

class EnsembleHBox extends EnsembleExample {
  def getContent = {

    val label = new Label {
      text = "Text:"
      style = "-fx-font-weight:bold"
    }
    val textField = new TextField {
      promptText = "Type something..."
    }
    val button = new Button {
      maxWidth = 110
      maxHeight = 70
      text = "Search..."
    }

    new VBox {
      vgrow = javafx.scene.layout.Priority.ALWAYS
      hgrow = javafx.scene.layout.Priority.ALWAYS
      spacing = 10
      margin = Insets(50, 0, 0, 50)
      content = List(
        new Label {
          text = "Ensemble HBox"
          font = new Font("Verdana", 20)
        },
        new HBox {
          maxWidth = 300
          maxHeight = 300
          spacing = 5
          alignment = Pos.BOTTOM_LEFT
          content = List(label, textField, button)
        })
    }
  }
}