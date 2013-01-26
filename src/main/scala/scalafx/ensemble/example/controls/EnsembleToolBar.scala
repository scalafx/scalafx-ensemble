package scalafx.ensemble.example.controls

import scalafx.Includes._
import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.scene.control.Button
import scalafx.scene.control.Label
import scalafx.scene.control.ToolBar
import scalafx.scene.layout.VBox
import scalafx.scene.text.Font
//import scalafx.scene.text.Font.sfxFont2jfx

class EnsembleToolBar extends EnsembleExample {
  def getContent = {
    new VBox {
      vgrow = javafx.scene.layout.Priority.ALWAYS
      hgrow = javafx.scene.layout.Priority.ALWAYS
      spacing = 10
      margin = Insets(50, 0, 0, 50)
      content = List(
        new Label {
          text = "Ensemble ToolBar"
          font = new Font("Verdana", 20)
        },
        new ToolBar {
          maxWidth = 200
          content = List(
            new Button {
              text = "Home"
            }, new Button {
              text = "Options"
            }, new Button {
              text = "Help"
            })
        })
    }
  }
}