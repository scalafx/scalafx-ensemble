package scalafx.ensemble.example.controls

import javafx.scene.control.Pagination
import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.scene.control.Button
import scalafx.scene.layout.VBox
import scalafx.scene.text.Font
import scalafx.scene.text.Text

class EnsemblePagination extends EnsembleExample {
  def getContent = {
    val pagination = new Pagination(10, 0)
    new VBox {
      vgrow = javafx.scene.layout.Priority.ALWAYS
      hgrow = javafx.scene.layout.Priority.ALWAYS
      spacing = 10
      margin = Insets(50, 0, 0, 50)
      content = List(
        new Text {
          text = "Ensemble Pagination"
          font = new Font("Verdana", 20)
        },
        new Text {
          text = "-----------------------------------------------------------"
          font = new Font("Verdana", 8)
          style = "-fx-font-weight: bold"

        },
        new Button {
          maxWidth = 200
          maxHeight = 150
          text = "Toggle Pagination Button"
        })
    }
  }
}