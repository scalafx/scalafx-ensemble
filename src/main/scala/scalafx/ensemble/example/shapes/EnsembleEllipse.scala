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
import scalafx.scene.shape.Ellipse

class EnsembleEllipse extends EnsembleExample {
  def getContent = {
    new VBox {
      vgrow = javafx.scene.layout.Priority.ALWAYS
      hgrow = javafx.scene.layout.Priority.ALWAYS
      spacing = 10
      margin = Insets(50, 0, 0, 50)
      content = List(
        new Label {
          text = "Ensemble Ellipses"
          font = new Font("Verdana", 20)
        },
        new Ellipse{
          centerX = 40
          centerY = 45
          radiusX = 30
          radiusY = 45
          stroke = Color.BLACK
          fill = Color.BLUE
        },
        new Ellipse{
          centerX = 140
          centerY = 145
          radiusX = 60
          radiusY = 75
          stroke = Color.BLACK
          fill = Color.WHITE
        })
    }
  }
}