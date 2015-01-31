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

import scalafx.Includes._
import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Orientation
import scalafx.scene.control.ScrollBar
import scalafx.scene.layout.Pane
import scalafx.scene.paint.Color
import scalafx.scene.shape.{Circle, Rectangle}


class EnsembleScrollBar extends EnsembleExample {

  // @stage-property resizable = false

  private var xScrollValue = 0d
  private var yScrollValue = 15d
  private val xBarWidth = 393
  private val xBarHeight = 15
  private val yBarWidth = 15
  private val yBarHeight = 393
  private val circleRadius = 90

  val bg = Rectangle(xBarWidth + yBarWidth, xBarHeight + yBarHeight, Color.rgb(90, 90, 90))
  val box = new Rectangle {
    width = 100
    height = 100
    fill = Color.rgb(150, 150, 150)
    translateX = 147
    translateY = 147
  }

  // Moveable circle
  val circle = new Circle {
    centerX = 45
    centerY = 45
    radius = circleRadius
    fill = Color.rgb(90, 210, 210)
    opacity = 0.4
    relocate(0, 15)
  }

  // Horizontal ScrollBar
  val xScrollBar = new ScrollBar {
    minWidth = -1
    minHeight = -1
    prefWidth = xBarWidth
    prefHeight = xBarHeight
    maxWidth = xBarWidth
    maxHeight = xBarHeight
    visibleAmount = 50
    max = xBarWidth - (2 * circleRadius)
    unitIncrement = 20.0
    value.onChange((_, _, _) => {
      xScrollValue = value()
      circle.relocate(xScrollValue, yScrollValue)
    })
  }

  // Vertical ScrollBar
  val yScrollBar = new ScrollBar {
    minWidth = -1
    minHeight = -1
    prefWidth = yBarWidth
    prefHeight = yBarHeight
    maxWidth = yBarWidth
    maxHeight = yBarHeight
    unitIncrement = 20.0
    visibleAmount = 50
    max = yBarHeight - (2 * circleRadius)
    value.onChange((_, _, _) => {
      yScrollValue = value() + xBarHeight
      circle.relocate(xScrollValue, yScrollValue)
    })
    translateX = yBarHeight
    translateY = yBarWidth
    orientation = Orientation.VERTICAL
  }


  def getContent = new Pane {
    prefWidth = xBarWidth + yBarWidth
    prefHeight = xBarHeight + yBarHeight
    children ++= Seq(bg, box, circle, xScrollBar, yScrollBar)
  }
}