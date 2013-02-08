package scalafx.ensemble.example.controls

import scalafx.collections.ObservableBuffer
import scalafx.ensemble.commons.EnsembleExample
import scalafx.scene.control.ListView
import scalafx.scene.layout.Priority
import scalafx.scene.layout.VBox
import scalafx.scene.text.Font
import scalafx.scene.text.Text
import scalafx.geometry.Insets

class EnsembleSimpleListView extends EnsembleExample {
  def getContent = {
    val seq = List("Row 1", "Row 2", "Long Row 3", "Row 4", "Row 5", "Row 6", "Row 7", "Row 8", "Row 9", "Row 10", "Row 11", "Row 12", "Row 13", "Row 14", "Row 15", "Row 16", "Row 17", "Row 18", "Row 19", "Row 20").toSeq
    val listView = new ListView[String] {
      maxWidth = 300
      items = ObservableBuffer[String](seq)
    }
    new VBox {
      vgrow = Priority.ALWAYS
      hgrow = Priority.ALWAYS
      spacing = 10
      margin = Insets(50, 0, 0, 50)
      content = List(
        new Text {
          text = "Ensemble Simple ListView"
          font = new Font("Verdana", 20)
        },
        listView)
    }
  }
}