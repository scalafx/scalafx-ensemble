package scalafx.ensemble.example.shapes

import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.scene.control.Label
import scalafx.scene.layout.VBox
import scalafx.scene.paint.Color
import scalafx.scene.paint.Color.sfxColor2jfx
import scalafx.scene.shape.Rectangle
import scalafx.scene.shape.Rectangle.sfxRectangle2jfx
import scalafx.scene.text.Font
import scalafx.scene.text.Font.sfxFont2jfx
import scalafx.scene.paint.Paint

class EnsembleRectangle extends EnsembleExample {
  def getContent = {
    // function literal to get Rectangle object
    val rectObj = (rect: Rectangle) => { rect.setStroke(Color.BURLYWOOD); rect }
    val rectArcObj = (height:Int,width:Int) => {
      val rect = Rectangle(100, 100, Color.WHITE)
      rect.setStroke(Color.GREEN)
      rect.setArcWidth(width)
      rect.setArcHeight(height)
      rect
    }
    val fillArc=(height:Int,width:Int,color:Paint) => {
      val rect = Rectangle(100, 100,color)
      rect.setStroke(Color.BLACK)
      rect.setArcWidth(width)
      rect.setArcHeight(height)
      rect
    }

    new VBox {
      vgrow = javafx.scene.layout.Priority.ALWAYS
      hgrow = javafx.scene.layout.Priority.ALWAYS
      spacing = 10
      margin = Insets(50, 0, 0, 50)
      content = List(
        new Label {
          text = "Ensemble Rectangles"
          font = new Font("Verdana", 20)
        },
        Rectangle(100, 100, Color.RED),
        rectObj(Rectangle(100, 100, Color.WHITE)),
        rectArcObj(20,20),
        fillArc(20,20,Color.BLUE))
    }
  }
}