/*
 * Copyright (c) 2012-2015, ScalaFX Ensemble Project
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

import scalafx.Includes._
import scalafx.ensemble.commons.EnsembleExample
import scalafx.event.ActionEvent
import scalafx.geometry.Insets
import scalafx.scene.control.{Button, TextField}
import scalafx.scene.layout.{BorderPane, HBox, Priority}
import scalafx.scene.web.WebView

class EnsembleWebView extends EnsembleExample {
  // @stage-property width = 1020
  // @stage-property height = 700

  def getContent = {
    // Default URL to load at first
    val defaultURL = "http://www.scala-lang.org/"
    val locationField = new TextField {
      text = defaultURL
      hgrow = Priority.Always
    }
    val goButton = new Button {
      text = "Go"
      defaultButton = true
    }
    val webView = new WebView {
      // Update location field is page is redirected
      engine.location.onChange((_, _, newValue) => locationField.setText(newValue))
      // Load default page
      engine.load(defaultURL)
    }

    def validUrl(url: String) = if (url.startsWith("http://")) url else "http://" + locationField.text()

    val loadAction = (ae: ActionEvent) => webView.engine.load(validUrl(locationField.text()))
    goButton.onAction = loadAction
    locationField.onAction = loadAction

    new BorderPane {
      padding = Insets(5)
      top = new HBox {
        spacing = 5
        margin = Insets(top = 0, right = 0, bottom = 5, left = 0)
        children = List(locationField, goButton)
      }
      center = webView
    }
  }
}