package scalafx.ensemble.example.controls

import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.scene.control.ProgressIndicator
import scalafx.scene.layout.GridPane
import scalafx.scene.layout.GridPane.sfxGridPane2jfx
import scalafx.scene.layout.VBox
import scalafx.scene.text.Font
import scalafx.scene.text.Text

class EnsembleProgressIndicator extends EnsembleExample {
  def getContent = {
    // Progress Indicators 1, 2, 3, 4
    val p1 = new ProgressIndicator {
      prefWidth = 50
      prefHeight = 50
    }
    val p2 = new ProgressIndicator {
      prefWidth = 50
      prefHeight = 50
      progress = 0.25F
    }
    val p3 = new ProgressIndicator {
      prefWidth = 50
      prefHeight = 50
      progress = 0.50F
    }
    val p4 = new ProgressIndicator {
      prefWidth = 50
      prefHeight = 50
      progress = 1.0F
    }
    //Add all progress indicators in grid pane
    val gridPane = new GridPane
    gridPane.add(p1, 1, 0)
    gridPane.add(p2, 0, 1)
    gridPane.add(p3, 1, 1)
    gridPane.add(p4, 2, 1)

    gridPane.setHgap(20)
    gridPane.setVgap(20)

    new VBox {
      vgrow = javafx.scene.layout.Priority.ALWAYS
      hgrow = javafx.scene.layout.Priority.ALWAYS
      spacing = 10
      margin = Insets(50, 0, 0, 50)
      content = List(
        new Text {
          text = "Ensemble Progress Indicator"
          font = new Font("Verdana", 20)
        },
        new Text {
          text = "---------------------------------------------------------------------------"
          font = new Font("Verdana", 8)
          style = "-fx-font-weight: bold"

        }, gridPane)
    }
  }
}