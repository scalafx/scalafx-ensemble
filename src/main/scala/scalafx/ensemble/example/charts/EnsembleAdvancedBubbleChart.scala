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

package scalafx.ensemble.example.charts

import java.lang.{ Math => JMath }

import scalafx.Includes._
import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.scene.chart.BubbleChart
//import scalafx.scene.chart.BubbleChart.sfxBubbleChart2jfx
import scalafx.scene.chart.NumberAxis
//import scalafx.scene.chart.NumberAxis.sfxNumberAxis2jfx
import scalafx.scene.chart.XYChart
import scalafx.scene.control.Label
import scalafx.scene.layout.VBox
import scalafx.scene.text.Font

class EnsembleAdvancedBubbleChart extends EnsembleExample {
  def getContent = {
    new VBox {
      vgrow = scalafx.scene.layout.Priority.ALWAYS
      hgrow = scalafx.scene.layout.Priority.ALWAYS
      spacing = 10
      margin = Insets(50, 0, 0, 50)
      content = List(
        new Label {
          text = "Ensemble Advanced Bubble Chart"
          font = new Font("Verdana", 20)
        },
        createBubbleChart)
    }
  }

  val createBubbleChart = {
    val xAxis = new NumberAxis()
    xAxis.setLabel("X Axis")
    val yAxis = new NumberAxis()
    yAxis.setLabel("Y Axis")

    val bcSeries1 = new XYChart.Series[Number, Number]()
    bcSeries1.setName("Data Series 1")
    // create sample data
    for (i <- 1 to 20) {
      bcSeries1.getData().add(XYChart.Data[Number, Number]((JMath.random() * 100).asInstanceOf[Number], (JMath.random() * 100).asInstanceOf[Number]))
    }

    val bcSeries2 = new XYChart.Series[Number, Number]()
    bcSeries2.setName("Data Series 2")
    for (i <- 1 to 20) {
      bcSeries2.getData().add(XYChart.Data[Number, Number]((JMath.random() * 100).asInstanceOf[Number], (JMath.random() * 100).asInstanceOf[Number]))
    }

    //Bubble Chart
    val bubbleChart = BubbleChart[Number, Number](xAxis, yAxis)
    bubbleChart.setTitle("Advanced BubbleChart")
    bubbleChart.getData.add(bcSeries1)
    bubbleChart.getData.add(bcSeries2)
    bubbleChart

  }
}
