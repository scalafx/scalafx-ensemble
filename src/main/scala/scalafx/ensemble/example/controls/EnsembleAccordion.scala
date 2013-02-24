package scalafx.ensemble.example.controls

import scalafx.Includes._
import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.scene.control.Accordion
import scalafx.scene.control.Button
import scalafx.scene.control.CheckBox
import scalafx.scene.control.Label
import scalafx.scene.control.TextField
import scalafx.scene.control.TitledPane
import scalafx.scene.layout.VBox
import scalafx.scene.text.Font
//import scalafx.scene.text.Font.sfxFont2jfx

class EnsembleAccordion extends EnsembleExample {
  def getContent = {
    new VBox {
      vgrow = scalafx.scene.layout.Priority.ALWAYS
      hgrow = scalafx.scene.layout.Priority.ALWAYS
      spacing = 10
      margin = Insets(50, 0, 0, 50)
      content = List(
        new Label {
          text = "Ensemble Accordion"
          font = new Font("Verdana", 20)
        },
        new Accordion {
          maxWidth = 200
          maxHeight = 500
          panes = List(
            new TitledPane {
              text = "Ensemble Button 1"
              content = new Button {
                text = "Button 1"
              }
            },
            new TitledPane {
              text = "Ensemble TextField 1"
              content = new TextField {
                promptText = "Hi! Scalafx Ensemble!"
              }
            }, new TitledPane {
              text = "Ensemble CheckBox 1"
              content = new CheckBox {
                text = "CheckBox 1"
              }
            })
        })
    }
  }
}