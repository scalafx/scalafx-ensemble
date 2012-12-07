package scalafx.ensemble.example.layout

import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.scene.control.Label
import scalafx.scene.layout.AnchorPane
import scalafx.scene.layout.VBox
import scalafx.scene.text.Font
import scalafx.scene.text.Font.sfxFont2jfx

class EnsembleAnchorPane extends EnsembleExample {
  def getContent = {
    new VBox {
      vgrow = javafx.scene.layout.Priority.ALWAYS
      hgrow = javafx.scene.layout.Priority.ALWAYS
      spacing = 10
      margin = Insets(50, 0, 0, 50)
      content = List(
        new Label {
          text = "Ensemble Anchor Pane"
          font = new Font("Verdana", 20)
        },
        new AnchorPane {
          maxWidth = 200
          maxHeight = 500
          content = List(
            new Label {
              text = "Ensemble Button 1"
            })
        })
    }
  }
}