package scalafx.ensemble.example.shapes

import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.scene.control.Label
import scalafx.scene.layout.VBox
import scalafx.scene.paint.Color
import scalafx.scene.paint.Color.sfxColor2jfx
import scalafx.scene.paint.Paint
import scalafx.scene.paint.Paint.sfxPaint2jfx
import scalafx.scene.shape.Polyline
import scalafx.scene.shape.Polyline.sfxPolyline2jfx
import scalafx.scene.text.Font

class EnsemblePolyline extends EnsembleExample {
  def getContent = {
    //Set polyline fill color using literal
    val polyObj = (poly: Polyline, color: Paint) => {
      poly.setFill(color)
      poly.setStroke(Color.BLUE)
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
          text = "Ensemble Polylines"
          font = new Font("Verdana", 20)
        }, polyObj(Polyline(45, 10, 10, 80, 45, 80), Color.WHITE),
        polyObj(Polyline(135, 15, 160, 30, 160, 60, 135, 110, 110, 30), Color.WHITE))

    }
  }
}