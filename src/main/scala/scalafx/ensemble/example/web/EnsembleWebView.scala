package scalafx.ensemble.example.web

import javafx.beans.value.ObservableValue
import scalafx.Includes._
import scalafx.event.ActionEvent
import scalafx.event.EventHandler
import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.scene.control.Button
//import scalafx.scene.control.Button.sfxButton2jfx
import scalafx.scene.control.Label
import scalafx.scene.control.TextField
//import scalafx.scene.control.TextField.sfxTextField2jfx
import scalafx.scene.layout.HBox
import scalafx.scene.layout.VBox
import scalafx.scene.text.Font
//import scalafx.scene.text.Font.sfxFont2jfx
import scalafx.scene.web.WebView
//import scalafx.scene.web.WebView.sfxWebView2jfx

class EnsembleWebView extends EnsembleExample {
  def getContent = {
    //Default URL to load at first
    val DEFAULT_URL = "http://www.scala-lang.org/";
    //Webview control
    val webView = new WebView {
      maxWidth = 1000
      maxHeight = 500
    }
    val webEngine = webView.getEngine()
    webEngine.load(DEFAULT_URL)

    val goButton = new Button {
      text = "Go"
      defaultButton = true
    }

    val textUrl = new TextField {
      text = DEFAULT_URL
      prefWidth = 250
    }
    // Function literal matching textfield contains http or not
    val validUrl = (url: String) => {
      url match {
        case url if url.contains("http://") => url
        case _ => "http://" + textUrl.getText()
      }
    }

    val loadAction = (ae: ActionEvent) => {
        webEngine.load(validUrl(textUrl.getText()))
    }
    //On action is set for textfield if Enter key is pressed
    textUrl onAction = loadAction
    goButton onAction = loadAction

    /*//TextField is given horizontal priority
    HBox.setHgrow(textUrl, Priority.ALWAYS)*/

    //WebEngine Location Property is added with changeListener for textfield value changes
    webEngine.locationProperty.addListener(
      (observable: ObservableValue[_], oldValue: Any, newValue: Any) => {
        val newTextVal = newValue.asInstanceOf[String]
        textUrl.setText(newTextVal)
      }
    )

    new VBox {
      vgrow = javafx.scene.layout.Priority.ALWAYS
      hgrow = javafx.scene.layout.Priority.ALWAYS
      spacing = 10
      margin = Insets(50, 0, 0, 50)
      content = List(
        new Label {
          text = "Ensemble Web View"
          font = new Font("Verdana", 20)
        },
        new HBox {
          spacing = 3
          content = List(
            textUrl,
            goButton)
        },
        webView)
    }
  }
}