package scalafx.ensemble.example.images

import scalafx.Includes._
import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.scene.control.Label
import scalafx.scene.image.Image
//import scalafx.scene.image.Image.sfxImage2jfx
import scalafx.scene.image.ImageView
import scalafx.scene.layout.VBox
import scalafx.scene.text.Font

class EnsembleImageCreation extends EnsembleExample {
  def getContent = {
    new VBox {
      vgrow = javafx.scene.layout.Priority.ALWAYS
      hgrow = javafx.scene.layout.Priority.ALWAYS
      spacing = 10
      margin = Insets(50, 0, 0, 50)
      content = List(
        new Label {
          text = "Ensemble Image Creation"
          font = new Font("Verdana", 20)
        },
        new ImageView {
          image = new Image(this.getClass.getResourceAsStream("/scalafx/ensemble/images/icon-48x48.png"))
        },
        new ImageView {
          image = new Image("http://www.scala-lang.org/sites/default/files/newsflash_logo.png")
          fitHeight = 300
          fitWidth = 700
          preserveRatio = true
          smooth = true
        })
    }
  }
}