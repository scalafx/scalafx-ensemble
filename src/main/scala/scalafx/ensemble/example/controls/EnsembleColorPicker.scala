package scalafx.ensemble.example.controls

import javafx.event.ActionEvent
import javafx.event.EventHandler
import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.scene.control.Button
import scalafx.scene.control.Button.sfxButton2jfx
import scalafx.scene.control.ColorPicker
import scalafx.scene.control.ColorPicker.sfxColorPicker2jfx
import scalafx.scene.control.Label
import scalafx.scene.control.Label.sfxLabel2jfx
import scalafx.scene.control.ToolBar
import scalafx.scene.layout.VBox
import scalafx.scene.paint.Color
import scalafx.scene.paint.Color.jfxColor2sfx
import scalafx.scene.paint.Color.sfxColor2jfx
import scalafx.scene.text.Font
import scalafx.scene.text.Text

class EnsembleColorPicker extends EnsembleExample {
  def getContent = {
    val rgbVal = (color: Color) => {
      "-fx-base: rgb(" + (color.getRed() * 255) + "," + (color.getGreen() * 255) + "," + (color.getBlue() * 255) + ");";
    }
    //Label for colorpicker
    val labelColor = new Label {
      text = "Colors"
      font = new Font("Verdana", 18)
      style = "-fx-font-weight:bold"
    }
    //Button for colorpicker
    val buttonColor = new Button {
      text = "Colored Control"
    }
    //Color Picker
    val colorPicker = new ColorPicker(Color.BLUE)
    colorPicker.onAction = new EventHandler[ActionEvent] {
      override def handle(ae: ActionEvent) {
        labelColor.setTextFill(colorPicker.getValue)
        buttonColor.setStyle(rgbVal(colorPicker.getValue))
      }
    }

    new VBox {
      vgrow = javafx.scene.layout.Priority.ALWAYS
      hgrow = javafx.scene.layout.Priority.ALWAYS
      spacing = 10
      margin = Insets(50, 0, 0, 50)
      content = List(
        new Text {
          text = "Ensemble ColorPicker"
          font = new Font("Verdana", 20)
        },
        new ToolBar {
          maxWidth = 300
          content = List(colorPicker)
        },
        labelColor,
        buttonColor)
    }
  }
}