package scalafx.ensemble.example.effects

import scalafx.Includes._
import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.scene.control.Label
import scalafx.scene.effect.InnerShadow
import scalafx.scene.layout.VBox
import scalafx.scene.paint.Color
//import scalafx.scene.paint.Color.sfxColor2jfx
import scalafx.scene.text.Font

class EnsembleInnerShadow extends EnsembleExample {
  def getContent = {
    new VBox {
      vgrow = javafx.scene.layout.Priority.ALWAYS
      hgrow = javafx.scene.layout.Priority.ALWAYS
      spacing = 10
      margin = Insets(50, 0, 0, 50)
      style = "-fx-padding: 8px"
      content = List(
        new Label {
          text = "Ensemble Inner Shadow 1"
          font = new Font("Arial Black", 30)
          textFill = Color.web("#BBBBBB")
          effect = new InnerShadow() {
            offsetX = 3
            offsetY = 3
            radius = 5d
          }
        },
        new Label {
          text = "Ensemble Inner Shadow 2"
          font = new Font("Arial Black", 30)
          textFill = Color.web("#BBBBBB")
          effect = new InnerShadow()
        })
    }
  }
}