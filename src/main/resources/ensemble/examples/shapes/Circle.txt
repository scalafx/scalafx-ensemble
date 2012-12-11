package scalafx.ensemble.example.shapes

import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.scene.control.Label
import scalafx.scene.layout.VBox
import scalafx.scene.paint.Color
import scalafx.scene.paint.Color.sfxColor2jfx
import scalafx.scene.paint.Paint
import scalafx.scene.paint.Paint.sfxPaint2jfx
import scalafx.scene.shape.Circle
import scalafx.scene.shape.Circle.sfxCircle2jfx
import scalafx.scene.text.Font
import scalafx.scene.text.Font.sfxFont2jfx

class EnsembleCircle extends EnsembleExample {
  def getContent = {
    // function literal to get Circle object
    val circObj = (circ: Circle) => { 
      circ.setStroke(Color.BURLYWOOD)
      circ.setFill(Color.WHITE)
      circ }

    val fillCirc = (color: Paint) => {
      val circ = Circle(200, 200, 50)
      circ.setStroke(Color.BLACK)
      circ.setFill(color)
      circ
    }

    new VBox {
      vgrow = javafx.scene.layout.Priority.ALWAYS
      hgrow = javafx.scene.layout.Priority.ALWAYS
      spacing = 10
      margin = Insets(50, 0, 0, 50)
      content = List(
        new Label {
          text = "Ensemble Circles"
          font = new Font("Verdana", 20)
        },
        circObj(Circle(100, 100, 50)),
        fillCirc(Color.GREEN))
    }
  }
}