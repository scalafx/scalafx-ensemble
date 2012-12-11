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
import scalafx.scene.shape.CubicCurve
import scalafx.scene.shape.CubicCurve.sfxCubicCurve2jfx
import scalafx.scene.text.Font
import scalafx.scene.text.Font.sfxFont2jfx

class EnsembleCubicCurve extends EnsembleExample {
  def getContent = {
    new VBox {
      vgrow = javafx.scene.layout.Priority.ALWAYS
      hgrow = javafx.scene.layout.Priority.ALWAYS
      spacing = 10
      margin = Insets(50, 0, 0, 50)
      content = List(
        new Label {
          text = "Ensemble Cubic Curve"
          font = new Font("Verdana", 20)
        },
        new CubicCurve {
          controlX1 = 30.0
          controlX2 = 150.0
          controlY1 = 10.0
          controlY2 = 80.0
          startX = 0
          startY = 45
          endX = 180
          endY = 45
          stroke = Color.BURLYWOOD
          fill = Color.ORANGE
          strokeWidth = 2d
        },
        new CubicCurve {
          controlX1 = 230.0
          controlX2 = 350.0
          controlY1 = 100.0
          controlY2 = 180.0
          startX = 0
          startY = 90
          endX = 120
          endY = 55
          stroke = Color.DARKORANGE
          fill = Color.WHITE
          strokeWidth = 5d
        })
    }
  }
}