/*
 * Copyright (c) 2012, ScalaFX Ensemble Project
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the ScalaFX Project nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE SCALAFX PROJECT OR ITS CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED
 * AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package scalafx.ensemble.example.web

import javafx.beans.value.ObservableValue
import scalafx.Includes._
import scalafx.event.ActionEvent
import scalafx.event.EventHandler
import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.scene.control.Button
import scalafx.scene.control.Label
import scalafx.scene.control.TextField
import scalafx.scene.layout.HBox
import scalafx.scene.layout.VBox
import scalafx.scene.text.Font
import scalafx.scene.web.WebView
import scalafx.scene.layout.Priority

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
      vgrow = Priority.ALWAYS
      hgrow = Priority.ALWAYS
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