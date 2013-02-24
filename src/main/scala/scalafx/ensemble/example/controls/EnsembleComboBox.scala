package scalafx.ensemble.example.controls

import scalafx.collections.ObservableBuffer
import scalafx.collections.ObservableBuffer.observableBuffer2ObservableList
import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.scene.control.ComboBox
import scalafx.scene.layout.Priority
import scalafx.scene.layout.VBox
import scalafx.scene.text.Font
import scalafx.scene.text.Text

class EnsembleComboBox extends EnsembleExample {
  def getContent = {
    new VBox {
      vgrow = Priority.ALWAYS
      hgrow = Priority.ALWAYS
      spacing = 10
      margin = Insets(50, 0, 0, 50)
      content = List(
        new Text {
          text = "Ensemble ComboBox"
          font = new Font("Verdana", 20)
        },
        new ComboBox[String] {
          maxWidth = 200
          maxHeight = 50
          promptText = "Choose a fruit..."
          val seq = List("Apple", "Orange", "Mango", "Banana").toSeq
          items = ObservableBuffer[String](seq)
        },
        new ComboBox[String] {
          maxWidth = 200
          maxHeight = 50
          promptText = "Edit or Choose a fruit..."
          editable = true
          val seq = List("Apple", "Orange", "Mango", "Banana").toSeq
          items = ObservableBuffer[String](seq)
        })
    }
  }
}