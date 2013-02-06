package scalafx.ensemble.example.controls

import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.scene.control.CheckBox
import scalafx.scene.layout.Priority
import scalafx.scene.layout.VBox
import scalafx.scene.text.Font
import scalafx.scene.text.Text

class EnsembleCheckBox extends EnsembleExample {
  def getContent = {
    new VBox {
      vgrow = Priority.ALWAYS
      hgrow = Priority.ALWAYS
      spacing = 10
      margin = Insets(50, 0, 0, 50)
      content = List(
        new Text {
          text = "Ensemble CheckBoxes"
          font = new Font("Verdana", 20)
        },
        new Text {
          text = "-----------------------------------------------------------"
          font = new Font("Verdana", 8)
          style = "-fx-font-weight: bold"

        },
        new CheckBox {
          maxWidth = 200
          maxHeight = 50
          text = "CheckBox 1"
        },
        new CheckBox {
          maxWidth = 200
          maxHeight = 50
          text = "CheckBox 2"
          selected = true
        },
        new CheckBox {
          maxWidth = 200
          maxHeight = 50
          text = "CheckBox 3"
          disable = true
        })
    }
  }
}