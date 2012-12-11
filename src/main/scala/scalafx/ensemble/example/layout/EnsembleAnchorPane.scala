package scalafx.ensemble.example.layout

import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.scene.control.Button
import scalafx.scene.control.Label
import scalafx.scene.image.Image
import scalafx.scene.image.Image.sfxImage2jfx
import scalafx.scene.image.ImageView
import scalafx.scene.layout.AnchorPane
import scalafx.scene.layout.VBox
import scalafx.scene.text.Font
import scalafx.scene.text.Font.sfxFont2jfx

class EnsembleAnchorPane extends EnsembleExample {
  def getContent = {
    val button = new Button {
      maxWidth = 110
      maxHeight = 70
      text = "Submit"
    }
    val imageButton = new ImageView {
      image = new Image(this.getClass.getResourceAsStream("/scalafx/ensemble/images/icon-48x48.png"))
    }

    AnchorPane.setTopAnchor(button, 2.0)
    AnchorPane.setTopAnchor(imageButton, 40.0)
    AnchorPane.setLeftAnchor(button, 20.0)
    AnchorPane.setLeftAnchor(imageButton, 20.0)

    new VBox {
      vgrow = javafx.scene.layout.Priority.ALWAYS
      hgrow = javafx.scene.layout.Priority.ALWAYS
      spacing = 10
      margin = Insets(50, 0, 0, 50)
      content = List(
        new Label {
          text = "Ensemble Anchor Pane"
          font = new Font("Verdana", 20)
        },
        new AnchorPane {
          content = List(button, imageButton)
        })
    }
  }
}