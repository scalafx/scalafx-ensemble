package scalafx.ensemble.example.text

import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.scene.control.TextField
import scalafx.scene.layout.VBox
import scalafx.scene.text.Font
import scalafx.scene.text.Text

class EnsembleTextField extends EnsembleExample {
  def getContent = {
    new VBox {
      vgrow = javafx.scene.layout.Priority.ALWAYS
      hgrow = javafx.scene.layout.Priority.ALWAYS
      spacing = 10
      margin = Insets(50, 0, 0, 50)
      content = List(
        new Text {
          text = "Ensemble Textfields"
          font = new Font("Verdana", 20)
        }, new TextField {
          maxWidth = 200
          maxHeight = 50
          promptText = "Hi! I am Scalafx Textfield"
        }, new TextField {
          maxWidth = 200
          maxHeight = 50
          promptText = "So Sad! I am disabled"
          disable = true
        })
    }
  }
}