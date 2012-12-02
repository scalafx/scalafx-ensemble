package scalafx.ensemble.example.controls

import javafx.scene.control.ColorPicker
import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.scene.Node
import scalafx.scene.control.ToolBar
import scalafx.scene.layout.VBox
import scalafx.scene.paint.Color
import scalafx.scene.paint.Color.sfxColor2jfx
import scalafx.scene.text.Font
import scalafx.scene.text.Text

class EnsembleColorPicker extends EnsembleExample {
  def getContent = {
    new VBox {
      vgrow = javafx.scene.layout.Priority.ALWAYS
      hgrow = javafx.scene.layout.Priority.ALWAYS
      spacing = 10
      margin = Insets(50, 0, 0, 50)
      
      //Color Picker
      val colorPicker= new ColorPicker(Color.BLUE)
      
      content = List(
        new Text {
          text = "Ensemble ColorPicker"
          font = new Font("Verdana", 20)
        },
        new ToolBar{
          content = List(colorPicker.asInstanceOf[Node])
        })
    }
  }
}