package scalafx.ensemble.example.layout

import javafx.geometry.Pos
import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.scene.control.CheckBox
import scalafx.scene.control.Label
import scalafx.scene.layout.VBox
import scalafx.scene.text.Font
import scalafx.scene.text.Font.sfxFont2jfx

class EnsembleVBox extends EnsembleExample {
  def getContent = {
    //Checkboxes arranged vertically using VBox
    val label = new Label {
      text = "Select your vehicle make:"
      style = "-fx-font-weight: bold"
    }
    val checkBox1 = new CheckBox {
      text = "Ford"
    }
    val checkBox2 = new CheckBox {
      text = "Hyundai"
    }
    val checkBox3 = new CheckBox {
      text = "Toyoto"
    }

    new VBox {
      vgrow = javafx.scene.layout.Priority.ALWAYS
      hgrow = javafx.scene.layout.Priority.ALWAYS
      spacing = 10
      margin = Insets(50, 0, 0, 50)
      content = List(
        new Label {
          text = "Ensemble VBox"
          font = new Font("Verdana", 20)
        },
        new VBox {
          maxWidth = 300
          maxHeight = 300
          spacing = 5
          innerAlignment = Pos.TOP_LEFT
          content = List(label, checkBox1, checkBox2, checkBox3)
        })
    }
  }
}