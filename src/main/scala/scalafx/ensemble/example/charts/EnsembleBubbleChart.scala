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

package scalafx.ensemble.example.charts

import scalafx.Includes._
import scalafx.ensemble.commons.EnsembleExample
import scalafx.scene.chart.BubbleChart
import scalafx.scene.chart.NumberAxis
import scalafx.scene.chart.XYChart

/** A chart that plots bubbles for a series of data points. Bubbles are plotted
  * according to three numeric parameters: value on x axis, value on y axis,
  * and radius of the bubble.
  *
  * @see scalafx.scene.chart.BubbleChart
  * @see scalafx.scene.chart.Chart
  * @see scalafx.scene.chart.Axis
  * @see scalafx.scene.chart.NumberAxis
  */
class EnsembleBubbleChart extends EnsembleExample {
  def getContent = {
    val xAxis = NumberAxis("X", 0d, 140d, 20d)
    val yAxis = NumberAxis("Y", 0d, 140d, 20d)

    // Helper function to convert a tuple to `XYChart.Data`
    val toChartData = (t: (Double, Double, Double)) => XYChart.Data[Number, Number](t._1, t._2, t._3)

    val series1 = new XYChart.Series[Number, Number] {
      name = "Series 1"
      data = Seq(
        (30d, 40d, 10d),
        (60d, 20d, 13d),
        (10d, 90d, 7d),
        (100d, 40d, 10d),
        (50d, 23d, 5d)).map(toChartData)
    }

    val series2 = new XYChart.Series[Number, Number] {
      name = "Series 2"
      data = Seq(
        (13d, 100d, 7d),
        (20d, 80d, 13d),
        (100d, 60d, 10d),
        (30d, 40d, 6d)).map(toChartData)
    }

    val series3 = new XYChart.Series[Number, Number] {
      name = "Series 3"
      data = Seq(
        (17d, 55d, 7d),
        (67d, 98d, 13d),
        (45d, 23d, 10d),
        (89d, 87d, 6d),
        (59d, 34d, 12d)).map(toChartData)
    }

    new BubbleChart(xAxis, yAxis) {
      data() ++= Seq(series1, series2, series3)
    }
  }
}
