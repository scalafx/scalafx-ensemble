package scalafx.ensemble.example.controls

import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.scene.control.ProgressBar
import scalafx.scene.layout.VBox
import scalafx.scene.text.Font
import scalafx.scene.text.Text

class EnsembleProgressBar extends EnsembleExample {
  def getContent = {
    new VBox {
      vgrow = javafx.scene.layout.Priority.ALWAYS
      hgrow = javafx.scene.layout.Priority.ALWAYS
      spacing = 10
      margin = Insets(50, 0, 0, 50)
      content = List(
        new Text {
          text = "Ensemble Progress Bars"
          font = new Font("Verdana", 20)
        },
        new Text {
          text = "--------------------------------------------------------------"
          font = new Font("Verdana", 8)
          style = "-fx-font-weight: bold"

        }, new ProgressBar {
          maxWidth = 100
        }, new ProgressBar {
          maxWidth = 200
        }, new ProgressBar {
          maxWidth = 300
        }, new ProgressBar {
          maxWidth = 100
          progress = 0.25
        }, new ProgressBar {
          maxWidth = 200
          progress = 0.50
        }, new ProgressBar {
          maxWidth = 300
          progress = 1
        })
    }
  }
}