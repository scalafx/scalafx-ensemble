package scalafx.ensemble.example.controls

import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.scene.Node
import scalafx.scene.Node.sfxNode2jfx
import scalafx.scene.control.Button
import scalafx.scene.image.Image
import scalafx.scene.image.Image.sfxImage2jfx
import scalafx.scene.image.ImageView
import scalafx.scene.layout.VBox

class EnsembleGraphicButton extends EnsembleExample {
  def getContent = {
    new VBox {
      vgrow = javafx.scene.layout.Priority.ALWAYS
      hgrow = javafx.scene.layout.Priority.ALWAYS
      spacing = 10
      margin = Insets(50, 0, 0, 50)
      content = List(
        new Button {
          maxWidth = 170
          maxHeight = 50
          text = "Graphic Button 1"
          graphic = new ImageView {
            image = new Image(this.getClass.getResourceAsStream("/scalafx/ensemble/images/icon-48x48.png"))
            margin = Insets(0, 0, 0, 10)
          }.asInstanceOf[Node]
        })
    }
  }
}