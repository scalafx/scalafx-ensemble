package scalafx.ensemble.example.controls

import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.scene.control.Label
import scalafx.scene.layout.VBox
import scalafx.scene.paint.Color
import scalafx.scene.paint.Color.sfxColor2jfx
import scalafx.scene.text.Font
import scalafx.scene.text.Font.sfxFont2jfx

class EnsembleLabel extends EnsembleExample {
  def getContent = {
    new VBox {
      vgrow = javafx.scene.layout.Priority.ALWAYS
      hgrow = javafx.scene.layout.Priority.ALWAYS
      spacing = 10
      margin = Insets(50, 0, 0, 50)
      style = "-fx-padding: 8px"
      content = List(
        new Label {
          text = "Ensemble Label"
          font = new Font("Verdana", 20)
        },
        new Label {
          text = "Ensemble Label with styles"
          font = new Font("Verdana", 20)
          textFill = Color.BLUE
        },
        new Label {
          text = "Ensemble Label with styles"
          font = new Font("Verdana", 20)
          style = "-fx-font-weight: bold"
          textFill = Color.RED
        })
    }
  }
}