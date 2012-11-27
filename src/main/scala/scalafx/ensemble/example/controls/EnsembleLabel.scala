package scalafx.ensemble.example.controls

import scalafx.ensemble.commons.EnsembleExample
import scalafx.scene.layout.VBox
import scalafx.scene.control.Label
import scalafx.scene.text.Font

class EnsembleLabel extends EnsembleExample {
  def getContent = {
    new VBox {
      vgrow = javafx.scene.layout.Priority.ALWAYS
      hgrow = javafx.scene.layout.Priority.ALWAYS
      style = "-fx-padding: 8px"
      content = List(
        new Label {
          text = "This is Label for sample display"
          font = new Font("Sans-serif", 13)
          style = "-fx-font-weight: bold;-fx-font-color: blue;"
        })
    }
  }
}