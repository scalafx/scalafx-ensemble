package scalafx.ensemble.example.text

import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.scene.control.Label
import scalafx.scene.layout.VBox
import scalafx.scene.text.Font

class EnsembleInsetText extends EnsembleExample {
  def getContent = {
    //StyleSheets are added
    val insetTextCss = this.getClass.getResource("/scalafx/ensemble/css/InsetText.css").toExternalForm()
    new VBox {
      vgrow = javafx.scene.layout.Priority.ALWAYS
      hgrow = javafx.scene.layout.Priority.ALWAYS
      spacing = 10
      margin = Insets(50, 0, 0, 50)
      style = "-fx-padding: 8px"
      content = List(
        new Label {
          text = "Ensemble Inset Text"
          font = new Font("Verdana", 20)
        },
        new Label {
          text = "Label styled as a bar"
          id = "label1"
          stylesheets=List(insetTextCss)
        })
    }
  }
}