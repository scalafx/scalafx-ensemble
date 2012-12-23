package scalafx.ensemble.example.controls

import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.scene.Node
import scalafx.scene.control.Hyperlink
import scalafx.scene.control.Label
import scalafx.scene.image.Image
import scalafx.scene.image.Image.sfxImage2jfx
import scalafx.scene.image.ImageView
import scalafx.scene.layout.VBox
import scalafx.scene.text.Font

class EnsembleHyperlink extends EnsembleExample {
  def getContent = {
    new VBox {
      vgrow = javafx.scene.layout.Priority.ALWAYS
      hgrow = javafx.scene.layout.Priority.ALWAYS
      spacing = 10
      margin = Insets(50, 0, 0, 50)
      content = List(
        new Label {
          text = "Ensemble Hyperlink"
          font = new Font("Verdana", 20)
        },
        new Hyperlink {
          maxWidth = 170
          maxHeight = 50
          text = "Hyber link 1"
        },
        new Hyperlink {
          maxWidth = 170
          maxHeight = 50
          text = "Hyber link 2"
          graphic = new ImageView {
            image = new Image(this.getClass.getResourceAsStream("/scalafx/ensemble/images/icon-48x48.png"))
            margin = Insets(0, 0, 0, 10)
          }.asInstanceOf[Node]
        })
    }
  }
}