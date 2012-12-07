package scalafx.ensemble.example.controls

import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.scene.control.Button
import scalafx.scene.control.Label
import scalafx.scene.control.Slider
import scalafx.scene.control.ToolBar
import scalafx.scene.layout.VBox
import scalafx.scene.text.Font
import scalafx.scene.text.Font.sfxFont2jfx

class EnsembleStyledToolBar extends EnsembleExample {
  def getContent = {
    // ToolBar Standard
    val stdToolBar = new ToolBar {
      maxWidth = 350
      id = "standard"
      content = List(
        new Button {
          text = "Button 1"
        }, new Button {
          text = "Button 2"
        }, new Slider {

        })
    }
    //ToolBar Blue
    val blueToolBar = new ToolBar {
      maxWidth = 350
      id = "blue"
      style = "-fx-base: dodgerblue"
      content = List(
        new Button {
          text = "Button 1"
        }, new Button {
          text = "Button 2"
        }, new Slider {

        })
    }
    //ToolBar Dark
    val darkToolBar = new ToolBar {
      maxWidth = 350
      id = "dark"
      style = "-fx-base: #333333"
      content = List(
        new Button {
          text = "Button 1"
        }, new Button {
          text = "Button 2"
        }, new Slider {

        })
    }

    new VBox {
      vgrow = javafx.scene.layout.Priority.ALWAYS
      hgrow = javafx.scene.layout.Priority.ALWAYS
      spacing = 10
      margin = Insets(50, 0, 0, 50)
      content = List(
        new Label {
          text = "Ensemble ToolBar"
          font = new Font("Verdana", 20)
        }, stdToolBar, darkToolBar, blueToolBar)
    }
  }
}
