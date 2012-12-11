package scalafx.ensemble.example.shapes

import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.scene.control.Label
import scalafx.scene.layout.VBox
import scalafx.scene.paint.Color
import scalafx.scene.paint.Color.sfxColor2jfx
import scalafx.scene.shape.Arc
import scalafx.scene.shape.Arc.sfxArc2jfx
import scalafx.scene.shape.ArcType
import scalafx.scene.shape.ArcType.sfxArcType2jfx
import scalafx.scene.text.Font
import scalafx.scene.text.Font.sfxFont2jfx

class EnsembleArc extends EnsembleExample {
  def getContent = {
    new VBox {
      vgrow = javafx.scene.layout.Priority.ALWAYS
      hgrow = javafx.scene.layout.Priority.ALWAYS
      spacing = 10
      margin = Insets(50, 0, 0, 50)
      content = List(
        new Label {
          text = "Ensemble Arcs"
          font = new Font("Verdana", 20)
        },
        new Arc {
          centerX = 200
          centerY = 200
          radiusX = 100
          radiusY = 100
          length = 100
          startAngle = 130
          fill = Color.GREEN
          stroke = Color.BLACK
          `type` = ArcType.OPEN
        },
        new Arc {
          centerX = 200
          centerY = 200
          radiusX = 100
          radiusY = 100
          length = 40
          startAngle = 150
          fill = Color.WHITE
          stroke = Color.BLACK
          `type` = ArcType.ROUND
        },
        new Arc {
          centerX = 200
          centerY = 200
          radiusX = 100
          radiusY = 100
          length = 80
          startAngle = 130
          fill = Color.BLUE
          stroke = Color.AZURE
          `type` = ArcType.CHORD
        })
    }
  }
}