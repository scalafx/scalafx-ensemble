package scalafx.ensemble.example

import scalafx.ensemble.commons.EnsembleExample
import scalafx.scene.control.TextField
import scalafx.scene.layout.VBox
import scalafx.scene.text.Font
import scalafx.scene.text.Text
import scalafx.scene.control.Label

class EnsembleTextField extends EnsembleExample {
  def getContent = {
    new VBox {
      vgrow = javafx.scene.layout.Priority.ALWAYS
      hgrow = javafx.scene.layout.Priority.ALWAYS
      style = "-fx-padding: 8px"
      content = List(
        new Text {
          text = "Samples"
          font = new Font("Sans-serif", 30)
          style = "-fx-font-weight: bold;"
        }, new TextField)
    }
  }
}

class EnsembleLabel extends EnsembleExample{
	def getContent = {
	  new VBox {
      vgrow = javafx.scene.layout.Priority.ALWAYS
      hgrow = javafx.scene.layout.Priority.ALWAYS
      style = "-fx-padding: 8px"
      content = List(
        new Label {
          text = "This is Label for sample display"
          font = new Font("Sans-serif", 13)
          style = "-fx-font-weight: bold;"
        })
    }
	}
}

