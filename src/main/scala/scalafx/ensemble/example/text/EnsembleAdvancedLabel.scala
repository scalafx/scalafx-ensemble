package scalafx.ensemble.example.text

import javafx.scene.control.ContentDisplay
import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.scene.control.Label
import scalafx.scene.image.Image
import scalafx.scene.image.ImageView
import scalafx.scene.layout.VBox
import scalafx.scene.paint.Color.sfxColor2jfx
import scalafx.scene.text.Font
import scalafx.scene.text.Font.sfxFont2jfx

class EnsembleAdvancedLabel extends EnsembleExample {
  def getContent = {
    //Image for Advanced labels
    val ICON_48 = new Image(this.getClass.getResourceAsStream("/scalafx/ensemble/images/icon-48x48.png"))
    new VBox {
      vgrow = javafx.scene.layout.Priority.ALWAYS
      hgrow = javafx.scene.layout.Priority.ALWAYS
      spacing = 10
      margin = Insets(50, 0, 0, 50)
      style = "-fx-padding: 8px"
      content = List(
        new Label {
          text = "Ensemble Advanced Labels"
          font = new Font("Verdana", 20)
        },
        new Label {
          text = "Image above"
          graphic = new ImageView(ICON_48);
          contentDisplay = ContentDisplay.TOP
        },
        new Label {
          text = "Image on the right"
          graphic = new ImageView(ICON_48);
          contentDisplay = ContentDisplay.RIGHT
        },
        new Label {
          text = "Image below"
          graphic = new ImageView(ICON_48);
          contentDisplay = ContentDisplay.BOTTOM
        },
        new Label {
          text = "Image on the left"
          graphic = new ImageView(ICON_48);
          contentDisplay = ContentDisplay.LEFT
        },
        new Label {
          text = "Image centered"
          graphic = new ImageView(ICON_48);
          contentDisplay = ContentDisplay.CENTER
        })
    }
  }
}