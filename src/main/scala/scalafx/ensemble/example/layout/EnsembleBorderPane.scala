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

package scalafx.ensemble.example.layout

import scalafx.Includes._
import scalafx.geometry.Pos
import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.scene.control.Button
import scalafx.scene.control.Label
import scalafx.scene.image.Image
import scalafx.scene.image.ImageView
import scalafx.scene.layout.AnchorPane
import scalafx.scene.layout.BorderPane
import scalafx.scene.layout.HBox
import scalafx.scene.layout.VBox
import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle
import scalafx.scene.text.Font
import scalafx.scene.layout.Priority

class EnsembleBorderPane extends EnsembleExample {
  def getContent = {
    // Top Stage using a rectangle
    val rect = Rectangle(400, 20, Color.DARKSEAGREEN)
    rect.setStroke(Color.BLACK)

    // Left Stage using VBox
    val lVbox = new VBox {
      spacing = 10
      content = List(new Label { text = "Left Hand" }, new Label { text = "Choice One" },
        new Label { text = "Choice Two" }, new Label { text = "Choice Three" })
    }

    //Center Stage using Anchor Pane(Button and ImageView)
    val centerBtn = new Button {
      maxWidth = 110
      maxHeight = 70
      text = "Center"
    }
    val imageButton = new ImageView {
      image = new Image(this.getClass.getResourceAsStream("/scalafx/ensemble/images/icon-48x48.png"))
    }
    AnchorPane.setTopAnchor(centerBtn, 10.0)
    AnchorPane.setTopAnchor(imageButton, 40.0)
    AnchorPane.setLeftAnchor(centerBtn, 80.0)
    AnchorPane.setLeftAnchor(imageButton, 80.0)
    val centerAnchorPane = new AnchorPane {
      content = List(centerBtn, imageButton)
    }

    // Right Stage using VBox
    val rVbox = new VBox {
      spacing = 10
      content = List(new Label { text = "Right Hand" }, new Label { text = "Thing A" },
        new Label { text = "Thing B" }, new Label { text = "Thing C" })
    }

    // Right Stage using VBox
    val bHbox = new HBox {
      alignment = Pos.BASELINE_CENTER
      content = List(new Label { text = "I am a status message. I am at the bottom" })
    }

    new VBox {
      vgrow = Priority.ALWAYS
      hgrow = Priority.ALWAYS
      spacing = 10
      margin = Insets(50, 0, 0, 50)
      content = List(
        new Label {
          text = "Ensemble Border Pane"
          font = new Font("Verdana", 20)
        },
        new BorderPane {
          maxWidth = 400
          maxHeight = 500
          top = rect
          left = lVbox
          center = centerAnchorPane
          right = rVbox
          bottom = bHbox
        })

    }
  }
}