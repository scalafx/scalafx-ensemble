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

import scalafx.collections.ObservableBuffer
import scalafx.ensemble.commons.EnsembleExample
import scalafx.scene.chart.LineChart
import scalafx.scene.chart.NumberAxis
import scalafx.scene.chart.XYChart

/** A chart in which lines connect a series of data points. Useful for viewing
  * data trends over time.
  *
  * @see scalafx.scene.chart.LineChart
  * @see scalafx.scene.chart.Chart
  * @see scalafx.scene.chart.Axis
  * @see scalafx.scene.chart.NumberAxis
  * @related charts/AreaChart
  * @related charts/ScatterChart
  */
class EnsembleLineChart extends EnsembleExample {

  def getContent = {

    val xAxis = NumberAxis("Values for X-Axis", 0, 3, 1)
    val yAxis = NumberAxis("Values for Y-Axis", 0, 3, 1)

    // Helper function to convert a tuple to `XYChart.Data`
    val toChartData = (xy: (Double, Double)) => XYChart.Data[Number, Number](xy._1, xy._2)

    val series1 = new XYChart.Series[Number, Number] {
      name = "Series 1"
      data = Seq(
        (0.0, 1.0),
        (1.2, 1.4),
        (2.2, 1.9),
        (2.7, 2.3),
        (2.9, 0.5)).map(toChartData)
    }

    val series2 = new XYChart.Series[Number, Number] {
      name = "Series 2"
      data = Seq(
        (0.0, 1.6),
        (0.8, 0.4),
        (1.4, 2.9),
        (2.1, 1.3),
        (2.6, 0.9)).map(toChartData)
    }

    new LineChart[Number, Number](xAxis, yAxis, ObservableBuffer(series1, series2))
  }
}