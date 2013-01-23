package scalafx.ensemble.example.shapes

import scalafx.Includes._
import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.scene.control.Label
import scalafx.scene.layout.VBox
import scalafx.scene.paint.Color
//import scalafx.scene.paint.Color.sfxColor2jfx
import scalafx.scene.paint.Paint
//import scalafx.scene.paint.Paint.sfxPaint2jfx
import scalafx.scene.shape.Polygon
//import scalafx.scene.shape.Polygon.sfxPolygon2jfx
import scalafx.scene.text.Font

class EnsemblePolygon extends EnsembleExample {
  def getContent = {
    //Set polygon fill color using literal
    val polyObj = (poly: Polygon, color: Paint) => {
      poly.setFill(color)
      poly.setStroke(Color.BLACK)
      poly.setStrokeWidth(1d)
      poly
    }
    new VBox {
      vgrow = javafx.scene.layout.Priority.ALWAYS
      hgrow = javafx.scene.layout.Priority.ALWAYS
      spacing = 10
      margin = Insets(50, 0, 0, 50)
      content = List(
        new Label {
          text = "Ensemble Polygon"
          font = new Font("Verdana", 20)
        }, polyObj(Polygon(45, 10, 10, 80, 80, 80), Color.DARKCYAN),
        polyObj(Polygon(135, 15, 160, 30, 160, 60, 135, 75, 110, 60, 110, 30), Color.ORANGE))

    }
  }
}