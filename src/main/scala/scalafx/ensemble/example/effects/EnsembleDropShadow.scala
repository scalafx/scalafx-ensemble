package scalafx.ensemble.example.effects

import scalafx.Includes._
import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.scene.control.Label
import scalafx.scene.image.Image
//import scalafx.scene.image.Image.sfxImage2jfx
import scalafx.scene.image.ImageView
import scalafx.scene.layout.VBox
import scalafx.scene.text.Font
import scalafx.scene.control.ContentDisplay
import scalafx.scene.effect.Effect
import scalafx.scene.effect.DropShadow
import scalafx.scene.paint.Color

class EnsembleDropShadow extends EnsembleExample {
  def getContent = {
    new VBox {
      vgrow = javafx.scene.layout.Priority.ALWAYS
      hgrow = javafx.scene.layout.Priority.ALWAYS
      spacing = 10
      margin = Insets(50, 0, 0, 50)
      style = "-fx-padding: 8px"
      content = List(
        new Label {
          text = "Ensemble Drop Shadow 1"
          font = new Font("Verdana", 20)
          effect = new DropShadow(12,Color.BLUE){
            offsetX = 60
            offsetY = 15
          }
        },
        new Label {
          text = "Ensemble Drop Shadow 2"
          font = new Font("Verdana", 20)
          effect = new DropShadow()
        })
    }
  }
}