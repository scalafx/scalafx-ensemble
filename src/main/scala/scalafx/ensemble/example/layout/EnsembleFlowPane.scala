package scalafx.ensemble.example.layout

import scalafx.Includes._
import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.scene.control.Button
//import scalafx.scene.control.Button.sfxButton2jfx
import scalafx.scene.control.Label
//import scalafx.scene.control.Label.sfxLabel2jfx
import scalafx.scene.image.Image
//import scalafx.scene.image.Image.sfxImage2jfx
import scalafx.scene.image.ImageView
//import scalafx.scene.image.ImageView.sfxImageView2jfx
import scalafx.scene.layout.FlowPane
import scalafx.scene.layout.VBox
import scalafx.scene.text.Font
//import scalafx.scene.text.Font.sfxFont2jfx

class EnsembleFlowPane extends EnsembleExample {
  def getContent = {
    val items = 5
    val label = new Array[Label](items)
    val button = new Array[Button](items)
    val imagevw = new Array[ImageView](items)
    val images = new Image(this.getClass.getResourceAsStream("/scalafx/ensemble/images/icon-48x48.png"))

    new VBox {
      vgrow = javafx.scene.layout.Priority.ALWAYS
      hgrow = javafx.scene.layout.Priority.ALWAYS
      spacing = 10
      margin = Insets(50, 0, 0, 50)
      content = List(
        new Label {
          text = "Ensemble Flow Pane"
          font = new Font("Verdana", 20)
        },
        new FlowPane(2, 4) {
          maxWidth = 200
          prefWrapLength = 200
          for (i <- 1 until items) {
            label(i) = new Label { text = "I am in flow pane" }
            button(i) = new Button { text = "I am in flow pane" }
            imagevw(i) = new ImageView { image = images }
            children.addAll(label(i), button(i), imagevw(i))
          }
        })
    }
  }
}