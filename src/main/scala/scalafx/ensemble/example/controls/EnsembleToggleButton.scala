package scalafx.ensemble.example.controls

import javafx.beans.value.ChangeListener
import javafx.beans.value.ObservableValue
import javafx.scene.control.{ ToggleButton => JfxToggleBtn }
import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.scene.control.Label
import scalafx.scene.control.ToggleButton
import scalafx.scene.control.ToggleGroup
import scalafx.scene.layout.HBox
import scalafx.scene.layout.VBox
import scalafx.scene.text.Font
import scalafx.scene.text.Text

class EnsembleToggleButton extends EnsembleExample {
  def getContent = {
    new VBox {
      vgrow = javafx.scene.layout.Priority.ALWAYS
      hgrow = javafx.scene.layout.Priority.ALWAYS
      spacing = 10
      margin = Insets(50, 0, 0, 50)
      //Radio Button Toggle Group 
      val tog = new ToggleGroup
      tog.selectedToggle.addListener(new ChangeListener[Any] {
        def changed(observable: ObservableValue[_], oldValue: Any, newValue: Any) {
          toggleLabel.text = "You selected : " + newValue.asInstanceOf[JfxToggleBtn].getText()
        }
      })

      val toggleLabel = new Label {
        text = ""
        style = "-fx-base:red"
      }

      content = List(
        new Text {
          text = "Ensemble Toggle Buttons"
          font = new Font("Verdana", 20)
        },
        new HBox {
          spacing = 10
          content = List(
            new ToggleButton {
              maxWidth = 200
              maxHeight = 50
              text = "Hi"
              toggleGroup = tog
            }, new ToggleButton {
              maxWidth = 200
              maxHeight = 50
              text = "Scalafx"
              toggleGroup = tog
            }, new ToggleButton {
              maxWidth = 200
              maxHeight = 50
              text = "Ensemble"
              toggleGroup = tog
            })
        }, new HBox {
          spacing = 10
          content = List(toggleLabel)
        })
    }
  }
}