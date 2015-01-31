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

package scalafx.ensemble.example.layout

import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.scene.control.Label
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.layout.{AnchorPane, BorderPane, VBox}
import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle

/** An example of  a BorderPane layout, with placement of children in the top,
  * left, center, right, and bottom positions.
  *
  * @see scalafx.scene.layout.BorderPane
  * @resource /scalafx/ensemble/images/icon-48x48.png
  */
class EnsembleBorderPane extends EnsembleExample {

  // @stage-property width = 440
  // @stage-property height = 300
  // @stage-property resizable = false

  def getContent = {
    // Top content using a rectangle
    val topRectangle = new Rectangle() {
      width = 400
      height = 20
      fill = Color.DarkSeaGreen
      stroke = Color.Black
    }

    // Left content using VBox
    val leftVBox = new VBox {
      spacing = 10
      children = List(Label("Left Hand"), Label("Choice One"), Label("Choice Two"), Label("Choice Three"))
    }

    // Center content using Anchor Pane
    val centerLabel = Label("We're in the center area.")
    val imageButton = new ImageView {
      image = new Image(this.getClass.getResourceAsStream("/scalafx/ensemble/images/icon-48x48.png"))
    }
    AnchorPane.setTopAnchor(centerLabel, 10.0)
    AnchorPane.setTopAnchor(imageButton, 40.0)
    AnchorPane.setLeftAnchor(centerLabel, 80.0)
    AnchorPane.setLeftAnchor(imageButton, 80.0)
    val centerAnchorPane = new AnchorPane {
      children = List(centerLabel, imageButton)
    }

    // Right content using VBox
    val rightVBox = new VBox {
      spacing = 10
      children = List(Label("Right Hand"), Label("Thing A"), Label("Thing B"), Label("Thing C"))
    }

    // Right content
    val bottomLabel = Label("I am a status message. I am at the bottom")

    new BorderPane {
      maxWidth = 400
      maxHeight = 300
      padding = Insets(20)
      top = topRectangle
      left = leftVBox
      center = centerAnchorPane
      right = rightVBox
      bottom = bottomLabel
    }
  }
}