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

package scalafx.ensemble.example.controls

import javafx.beans.value.ObservableValue
import javafx.scene.control.{ ToggleButton => JfxToggleBtn }
import scalafx.Includes._
import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.scene.control.Label
import scalafx.scene.control.ToggleButton
import scalafx.scene.control.ToggleGroup
import scalafx.scene.layout.HBox
import scalafx.scene.layout.VBox
import scalafx.scene.text.Font
import scalafx.scene.text.Text
import scalafx.scene.layout.Priority

class EnsembleToggleButton extends EnsembleExample {
  def getContent = {
    new VBox {
      vgrow = Priority.ALWAYS
      hgrow = Priority.ALWAYS
      spacing = 10
      margin = Insets(50, 0, 0, 50)
      //Radio Button Toggle Group 
      val tog = new ToggleGroup
      tog.selectedToggle.addListener(
        (observable: ObservableValue[_], oldValue: Any, newValue: Any) => {
          toggleLabel.text = "You selected : " + newValue.asInstanceOf[JfxToggleBtn].getText()
        }
      )

      val toggleLabel = new Label {
        text = ""
        style = "-fx-font-weight: bold"
      }

      content = List(
        new Text {
          text = "Ensemble Toggle Buttons"
          font = new Font("Verdana", 20)
        },
        new HBox {
          spacing = 10
          content = List(
            new ToggleButton {
              maxWidth = 200
              maxHeight = 50
              text = "Hi"
              toggleGroup = tog
            }, new ToggleButton {
              maxWidth = 200
              maxHeight = 50
              text = "Scalafx"
              toggleGroup = tog
            }, new ToggleButton {
              maxWidth = 200
              maxHeight = 50
              text = "Ensemble"
              toggleGroup = tog
            })
        }, new HBox {
          spacing = 10
          content = List(toggleLabel)
        })
    }
  }
}