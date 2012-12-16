package scalafx.ensemble.example.text

import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.scene.control.Label
import scalafx.scene.image.Image
import scalafx.scene.image.Image.sfxImage2jfx
import scalafx.scene.image.ImageView
import scalafx.scene.layout.VBox
import scalafx.scene.paint.Color
import scalafx.scene.paint.Color.sfxColor2jfx
import scalafx.scene.text.Font
import javafx.scene.control.ContentDisplay

class EnsembleSimpleLabel extends EnsembleExample {
  def getContent = {
    new VBox {
      vgrow = javafx.scene.layout.Priority.ALWAYS
      hgrow = javafx.scene.layout.Priority.ALWAYS
      spacing = 10
      margin = Insets(50, 0, 0, 50)
      style = "-fx-padding: 8px"
      content = List(
        new Label {
          text = "Ensemble Simple Label"
          font = new Font("Verdana", 20)
        },
        new Label {
          text = "A simple label with a graphic on the left."
          graphic = new ImageView {
            image = new Image(this.getClass.getResourceAsStream("/scalafx/ensemble/images/icon-48x48.png"))
          }
          contentDisplay = ContentDisplay.LEFT
        })
    }
  }
}