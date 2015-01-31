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

package scalafx.ensemble.example.shapes

import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.scene.layout.HBox
import scalafx.scene.paint.Color
import scalafx.scene.shape.{Arc, ArcType}

class EnsembleArc extends EnsembleExample {
  def getContent = new HBox {
    spacing = 50
    padding = Insets(20)
    children = List(
      new Arc {
        centerX = 200
        centerY = 200
        radiusX = 100
        radiusY = 100
        length = 100
        startAngle = 130
        fill = Color.Green
        stroke = Color.Black
        `type` = ArcType.Open
      },
      new Arc {
        centerX = 200
        centerY = 200
        radiusX = 100
        radiusY = 100
        length = 40
        startAngle = 150
        fill = Color.White
        stroke = Color.Black
        `type` = ArcType.Round
      },
      new Arc {
        centerX = 200
        centerY = 200
        radiusX = 100
        radiusY = 100
        length = 80
        startAngle = 130
        fill = Color.Blue
        stroke = Color.Azure
        `type` = ArcType.Chord
      },
      new Arc {
        centerX = 200
        centerY = 200
        radiusX = 100
        radiusY = 100
        length = 80
        startAngle = 45
        fill = null
        stroke = Color.Coral
        strokeWidth = 3
        `type` = ArcType.Open
      }
    )
  }
}