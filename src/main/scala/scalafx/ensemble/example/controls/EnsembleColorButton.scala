package scalafx.ensemble.example.controls

import scalafx.Includes.jfxPriority2sfx
import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.scene.control.Button
import scalafx.scene.layout.VBox
import scalafx.scene.text.Font
import scalafx.scene.text.Text
import scalafx.scene.layout.Priority

class EnsembleColorButton extends EnsembleExample {
  def getContent = {
    new VBox {
      vgrow = Priority.ALWAYS
      hgrow = Priority.ALWAYS
      spacing = 10
      margin = Insets(50, 0, 0, 50)
      content = List(
        new Text {
          text = "Ensemble Color Buttons"
          font = new Font("Verdana", 20)
        },
        new Button {
          maxWidth = 200
          maxHeight = 150
          text = "Color Button 1"
          style = "-fx-base: red"
        },
        new Button {
          maxWidth = 200
          maxHeight = 150
          text = "Color Button 2"
          style = "-fx-base: green "
        },
        new Button {
          maxWidth = 200
          maxHeight = 150
          text = "Color Button 3"
          style = "-fx-base: Yellow"
        },
        new Button {
          maxWidth = 200
          maxHeight = 150
          text = "Color Button 4"
          style = "-fx-base: Orange"
        })
    }
  }
}