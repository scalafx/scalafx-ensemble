package scalafx.ensemble.example.layout

import javafx.geometry.Orientation
import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.scene.Node
import scalafx.scene.Node.sfxNode2jfx
import scalafx.scene.control.Button
import scalafx.scene.control.Button.sfxButton2jfx
import scalafx.scene.control.Label
import scalafx.scene.image.Image
import scalafx.scene.image.Image.sfxImage2jfx
import scalafx.scene.image.ImageView
import scalafx.scene.layout.TilePane
import scalafx.scene.layout.VBox
import scalafx.scene.text.Font
import scalafx.scene.text.Font.sfxFont2jfx

class EnsembleTilePane extends EnsembleExample {
  def getContent = {
    //Image for buttons
    val pic = new Image(this.getClass.getResourceAsStream("/scalafx/ensemble/images/icon-48x48.png"))
    //Button Array
    val buttons = new Array[Button](18)

    // Tiles for example
    val tiles = new TilePane {
      hgap = 3
      vgap = 3
      prefColumns = 3
      prefTileWidth = 140
      prefTileHeight = 70
      orientation = Orientation.HORIZONTAL
      for (i <- 0 to 17) {
        buttons(i) = new Button {
          text = "Button " + i
          graphic = new ImageView {
            image = pic
          }.asInstanceOf[Node]
        }
        children.addAll(buttons(i))
      }
    }

    new VBox {
      vgrow = javafx.scene.layout.Priority.ALWAYS
      hgrow = javafx.scene.layout.Priority.ALWAYS
      spacing = 10
      margin = Insets(50, 0, 0, 50)
      content = List(
        new Label {
          text = "Ensemble Tile Pane"
          font = new Font("Verdana", 20)
        },
        tiles)
    }
  }
}