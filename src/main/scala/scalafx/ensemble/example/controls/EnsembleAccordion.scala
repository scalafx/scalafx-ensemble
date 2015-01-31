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

package scalafx.ensemble.example.controls

import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.scene.control.{Accordion, Button, CheckBox, TextField, TitledPane}
import scalafx.scene.layout.StackPane

/** An example of an accordion control. You can use accordion controls to define
  * individual panes and  display them one at a time.
  *
  * @see scalafx.scene.control.Accordion
  * @related controls/ToggleButton
  * @related controls/ToolBar
  */
class EnsembleAccordion extends EnsembleExample {

  // @stage-property resizable = false
  // @stage-property width = 200
  // @stage-property height = 200

  def getContent = new StackPane {
    padding = Insets(10)
    children = new Accordion {
      maxWidth = 150
      maxHeight = 150
      panes = List(
        new TitledPane {
          text = "Ensemble Button 1"
          children = new Button("Button 1")
        },
        new TitledPane {
          text = "Ensemble TextField 1"
          children = new TextField {
            promptText = "Hi! Scalafx Ensemble!"
          }
        }, new TitledPane {
          text = "Ensemble CheckBox 1"
          children = new CheckBox {
            text = "CheckBox 1"
          }
        })
    }
  }
}