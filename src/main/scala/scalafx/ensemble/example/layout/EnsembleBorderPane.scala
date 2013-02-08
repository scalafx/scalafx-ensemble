package scalafx.ensemble.example.layout

import scalafx.Includes._
import scalafx.geometry.Pos
import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.scene.control.Button
import scalafx.scene.control.Label
import scalafx.scene.image.Image
//import scalafx.scene.image.Image.sfxImage2jfx
import scalafx.scene.image.ImageView
import scalafx.scene.layout.AnchorPane
import scalafx.scene.layout.BorderPane
import scalafx.scene.layout.HBox
import scalafx.scene.layout.VBox
import scalafx.scene.paint.Color
//import scalafx.scene.paint.Color.sfxColor2jfx
import scalafx.scene.shape.Rectangle
//import scalafx.scene.shape.Rectangle.sfxRectangle2jfx
import scalafx.scene.text.Font
//import scalafx.scene.text.Font.sfxFont2jfx

class EnsembleBorderPane extends EnsembleExample {
  def getContent = {
    // Top Stage using a rectangle
    val rect = Rectangle(400, 20, Color.DARKSEAGREEN)
    rect.setStroke(Color.BLACK)

    // Left Stage using VBox
    val lVbox = new VBox {
      spacing = 10
      content = List(new Label { text = "Left Hand" }, new Label { text = "Choice One" },
        new Label { text = "Choice Two" }, new Label { text = "Choice Three" })
    }

    //Center Stage using Anchor Pane(Button and ImageView)
    val centerBtn = new Button {
      maxWidth = 110
      maxHeight = 70
      text = "Center"
    }
    val imageButton = new ImageView {
      image = new Image(this.getClass.getResourceAsStream("/scalafx/ensemble/images/icon-48x48.png"))
    }
    AnchorPane.setTopAnchor(centerBtn, 10.0)
    AnchorPane.setTopAnchor(imageButton, 40.0)
    AnchorPane.setLeftAnchor(centerBtn, 80.0)
    AnchorPane.setLeftAnchor(imageButton, 80.0)
    val centerAnchorPane = new AnchorPane {
      content = List(centerBtn, imageButton)
    }

    // Right Stage using VBox
    val rVbox = new VBox {
      spacing = 10
      content = List(new Label { text = "Right Hand" }, new Label { text = "Thing A" },
        new Label { text = "Thing B" }, new Label { text = "Thing C" })
    }

    // Right Stage using VBox
    val bHbox = new HBox {
      alignment = Pos.BASELINE_CENTER
      content = List(new Label { text = "I am a status message. I am at the bottom" })
    }

    new VBox {
      vgrow = javafx.scene.layout.Priority.ALWAYS
      hgrow = javafx.scene.layout.Priority.ALWAYS
      spacing = 10
      margin = Insets(50, 0, 0, 50)
      content = List(
        new Label {
          text = "Ensemble Border Pane"
          font = new Font("Verdana", 20)
        },
        new BorderPane {
          maxWidth = 400
          maxHeight = 500
          top = rect
          left = lVbox
          center = centerAnchorPane
          right = rVbox
          bottom = bHbox
        })

    }
  }
}