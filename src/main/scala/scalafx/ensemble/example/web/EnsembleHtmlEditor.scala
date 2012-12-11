package scalafx.ensemble.example.web

import javafx.event.ActionEvent
import javafx.event.EventHandler
import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.scene.control.Button
import scalafx.scene.control.Label
import scalafx.scene.control.Label.sfxLabel2jfx
import scalafx.scene.control.ScrollPane
import scalafx.scene.layout.VBox
import scalafx.scene.text.Font
import scalafx.scene.text.Font.sfxFont2jfx
import scalafx.scene.web.HTMLEditor
import scalafx.scene.web.HTMLEditor.sfxHTMLEditor2jfx

class EnsembleHtmlEditor extends EnsembleExample {
  def getContent = {
    // Initial Text in the html editor
    val initialText = """<html><body>Lorem ipsum dolor sit amet, consectetur adipiscing elit.

            Nam tortor felis, pulvinar in scelerisque cursus, pulvinar at ante. Nulla consequat 

            congue lectus in sodales. Nullam eu est a felis ornare bibendum et nec tellus. 

            Vivamus non metus tempus augue auctor ornare. Duis pulvinar justo ac purus adipiscing 

            pulvinar. Integer congue faucibus dapibus. Integer id nisl ut elit aliquam sagittis 

            gravida eu dolor. Etiam sit amet ipsum sem.</body></html>"""

    val htmlEditor = new HTMLEditor {
      maxWidth = 400
      maxHeight = 250
      htmlText = initialText
    }

    val htmlLabel = new Label {
      maxWidth = 300
      wrapText = true
    }

    val scrollPane = new ScrollPane {
      content = htmlLabel
      maxWidth = 400
      maxHeight = 350
    }

    new VBox {
      vgrow = javafx.scene.layout.Priority.ALWAYS
      hgrow = javafx.scene.layout.Priority.ALWAYS
      spacing = 10
      margin = Insets(50, 0, 0, 50)
      content = List(
        new Label {
          text = "Ensemble HTML Editor"
          font = new Font("Verdana", 20)
        },
        htmlEditor,
        new Button {
          text = "Display Html below"
          onAction = new EventHandler[ActionEvent] {
            def handle(ae: ActionEvent) {
              htmlLabel.setText(htmlEditor.getHtmlText())
            }
          }
        },
        scrollPane)
    }
  }
}