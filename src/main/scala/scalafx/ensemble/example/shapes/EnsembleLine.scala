package scalafx.ensemble.example.shapes

import scalafx.Includes._
import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.scene.control.Label
import scalafx.scene.layout.VBox
import scalafx.scene.paint.Color
//import scalafx.scene.paint.Color.sfxColor2jfx
import scalafx.scene.shape.Arc
//import scalafx.scene.shape.Arc.sfxArc2jfx
import scalafx.scene.shape.ArcType
//import scalafx.scene.shape.ArcType.sfxArcType2jfx
import scalafx.scene.text.Font
//import scalafx.scene.text.Font.sfxFont2jfx
import scalafx.scene.shape.Line

class EnsembleLine extends EnsembleExample {
  def getContent = {
    new VBox {
      vgrow = javafx.scene.layout.Priority.ALWAYS
      hgrow = javafx.scene.layout.Priority.ALWAYS
      spacing = 10
      margin = Insets(50, 0, 0, 50)
      content = List(
        new Label {
          text = "Ensemble Line"
          font = new Font("Verdana", 20)
        },
        new Line {
          startX = 320
          startY = 120
          endX = 20
          endY = 180
          fill = Color.BLACK
          stroke = Color.GREEN
          strokeWidth = 1d
        },
        new Line {
          startX = 320
          startY = 120
          endX = 20
          endY = 180
          fill = Color.WHITE
          stroke = Color.ORANGE
          strokeWidth = 10d
        })
    }
  }
}