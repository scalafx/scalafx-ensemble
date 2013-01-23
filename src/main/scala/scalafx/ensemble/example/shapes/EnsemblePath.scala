package scalafx.ensemble.example.shapes

import scalafx.Includes._
import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.scene.control.Label
import scalafx.scene.layout.VBox
import scalafx.scene.paint.Color
import scalafx.scene.shape.ArcTo
//import scalafx.scene.shape.ArcTo.sfxArcTo2jfx
import scalafx.scene.shape.ClosePath
//import scalafx.scene.shape.ClosePath.sfxClosePath2jfx
import scalafx.scene.shape.CubicCurveTo
//import scalafx.scene.shape.CubicCurveTo.sfxCubicCurveTo2jfx
import scalafx.scene.shape.HLineTo
//import scalafx.scene.shape.HLineTo.sfxHLineTo2jfx
import scalafx.scene.shape.LineTo
//import scalafx.scene.shape.LineTo.sfxLineTo2jfx
import scalafx.scene.shape.MoveTo
//import scalafx.scene.shape.MoveTo.sfxMoveTo2jfx
import scalafx.scene.shape.Path
import scalafx.scene.shape.QuadCurveTo
//import scalafx.scene.shape.QuadCurveTo.sfxQuadCurveTo2jfx
import scalafx.scene.shape.VLineTo
//import scalafx.scene.shape.VLineTo.sfxVLineTo2jfx
import scalafx.scene.text.Font

class EnsemblePath extends EnsembleExample {
  def getContent = {
    new VBox {
      vgrow = javafx.scene.layout.Priority.ALWAYS
      hgrow = javafx.scene.layout.Priority.ALWAYS
      spacing = 10
      margin = Insets(50, 0, 0, 50)
      content = List(
        new Label {
          text = "Ensemble Path"
          font = new Font("Verdana", 20)
        },
        new Path {
          elements = List(MoveTo(35, 35), HLineTo(75), VLineTo(75), LineTo(35, 75), new ClosePath())
          fill = Color.ORANGE
          stroke = Color.BLACK
          strokeWidth = 1d
        },
        new Path {
          elements = List(MoveTo(100, 55),
            CubicCurveTo(120, 20, 130, 80, 140, 45),
            QuadCurveTo(150, 0, 160, 45),
            ArcTo(20, 40, 0, 180, 45, true, true))
          fill = Color.AZURE
        })
    }
  }
}