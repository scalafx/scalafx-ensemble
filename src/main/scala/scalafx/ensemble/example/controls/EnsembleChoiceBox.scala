package scalafx.ensemble.example.controls

import scalafx.collections.ObservableBuffer
import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.scene.control.ChoiceBox
import scalafx.scene.layout.VBox
import scalafx.scene.text.Font
import scalafx.scene.text.Text

class EnsembleChoiceBox extends EnsembleExample {
  def getContent = {
    new VBox {
      vgrow = javafx.scene.layout.Priority.ALWAYS
      hgrow = javafx.scene.layout.Priority.ALWAYS
      spacing = 10
      margin = Insets(50, 0, 0, 50)
      content = List(
        new Text {
          text = "Ensemble ChoiceBox"
          font = new Font("Verdana", 20)
        },
        new ChoiceBox[String] {
          maxWidth = 80
          maxHeight = 50
          val seq = List("Earth", "Sky", "Paradise").toSeq
          items = ObservableBuffer[String](seq)
        })
    }
  }
}