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

import scalafx.Includes._
import scalafx.event.ActionEvent
import scalafx.event.EventHandler
import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.scene.control.Button
import scalafx.scene.control.Label
import scalafx.scene.control.ScrollPane
import scalafx.scene.layout.VBox
import scalafx.scene.text.Font
import scalafx.scene.web.HTMLEditor
import scalafx.scene.layout.Priority

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
      vgrow = Priority.ALWAYS
      hgrow = Priority.ALWAYS
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
          onAction = (ae: ActionEvent) => {
              htmlLabel.setText(htmlEditor.getHtmlText())
          }
        },
        scrollPane)
    }
  }
}