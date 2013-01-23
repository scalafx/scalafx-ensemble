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
import scalafx.scene.shape.Circle
//import scalafx.scene.shape.Circle.sfxCircle2jfx
import scalafx.scene.shape.CubicCurve
//import scalafx.scene.shape.CubicCurve.sfxCubicCurve2jfx
import scalafx.scene.text.Font
//import scalafx.scene.text.Font.sfxFont2jfx
import scalafx.scene.shape.QuadCurve

class EnsembleQuadCurve extends EnsembleExample {
  def getContent = {
    new VBox {
      vgrow = javafx.scene.layout.Priority.ALWAYS
      hgrow = javafx.scene.layout.Priority.ALWAYS
      spacing = 10
      margin = Insets(50, 0, 0, 50)
      content = List(
        new Label {
          text = "Ensemble Quad Curve"
          font = new Font("Verdana", 20)
        },
        new QuadCurve {
          controlX = 50
          controlY = 10
          startX = 0
          startY = 45
          endX = 180
          endY = 45
          stroke = Color.RED
          fill = Color.ROSYBROWN
          strokeWidth = 2d
        },
        new QuadCurve {
          controlX = 150
          controlY = 100
          startX = 35
          startY = 145
          endX = 210
          endY = 145
          stroke = Color.BLUE
          fill = Color.WHITE
          strokeWidth = 2d
        })
    }
  }
}