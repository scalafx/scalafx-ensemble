package scalafx.ensemble.example.layout

import scalafx.Includes._
import scalafx.scene.control.ContentDisplay
import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.scene.Node
//import scalafx.scene.Node.sfxNode2jfx
import scalafx.scene.control.Label
import scalafx.scene.image.Image
//import scalafx.scene.image.Image.sfxImage2jfx
import scalafx.scene.image.ImageView
import scalafx.scene.layout.StackPane
import scalafx.scene.layout.VBox
import scalafx.scene.paint.Color
//import scalafx.scene.paint.Color.sfxColor2jfx
import scalafx.scene.shape.Rectangle
//import scalafx.scene.shape.Rectangle.sfxRectangle2jfx
import scalafx.scene.text.Font
//import scalafx.scene.text.Font.sfxFont2jfx

class EnsembleStackPane extends EnsembleExample {
  def getContent = {
    // function literal to get Rectangle object
    val rectObj = (rect: Rectangle) => { rect.setStroke(Color.BURLYWOOD); rect }

    new VBox {
      vgrow = javafx.scene.layout.Priority.ALWAYS
      hgrow = javafx.scene.layout.Priority.ALWAYS
      spacing = 10
      margin = Insets(50, 0, 0, 50)
      content = List(
        new Label {
          text = "Ensemble Stack Pane"
          font = new Font("Verdana", 20)
        },
        new StackPane {
          maxWidth = 200
          content = List(
            rectObj(Rectangle(200, 50, Color.BISQUE)), new Label {
              text = "I am in stack pane"
              contentDisplay = ContentDisplay.LEFT
              graphic = new ImageView {
                image = new Image(this.getClass.getResourceAsStream("/scalafx/ensemble/images/icon-48x48.png"))
              }.asInstanceOf[Node]
            })
        })
    }
  }
}