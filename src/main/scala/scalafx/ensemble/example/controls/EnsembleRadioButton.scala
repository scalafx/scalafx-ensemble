package scalafx.ensemble.example.controls

import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.scene.control.RadioButton
import scalafx.scene.control.ToggleGroup
import scalafx.scene.layout.VBox
import scalafx.scene.text.Font
import scalafx.scene.text.Text

class EnsembleRadioButton extends EnsembleExample {
  //Radio Button Toggle Group 
  val tog = new ToggleGroup

  def getContent = {
    new VBox {
      vgrow = javafx.scene.layout.Priority.ALWAYS
      hgrow = javafx.scene.layout.Priority.ALWAYS
      spacing = 10
      margin = Insets(50, 0, 0, 50)
      content = List(
        new Text {
          text = "Ensemble Radio Buttons"
          font = new Font("Verdana", 20)
        },
        new Text {
          text = "-----------------------------------------------------------------"
          font = new Font("Verdana", 8)
          style = "-fx-font-weight: bold"

        },
        new RadioButton {
          maxWidth = 200
          maxHeight = 50
          text = "Hi"
          toggleGroup = tog
        },
        new RadioButton {
          maxWidth = 200
          maxHeight = 50
          text = "Good bye"
          selected = true
          toggleGroup = tog
        },
        new RadioButton {
          maxWidth = 200
          maxHeight = 50
          text = "Disabled!!"
          disable = true
        })
    }
  }
}