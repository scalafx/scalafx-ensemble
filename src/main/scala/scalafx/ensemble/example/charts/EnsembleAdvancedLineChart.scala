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
import scalafx.scene.chart.LineChart
import scalafx.scene.chart.NumberAxis
import scalafx.scene.chart.XYChart

/** An advanced line chart.
  *
  * @see scalafx.scene.chart.LineChart
  * @see scalafx.scene.chart.Chart
  * @see scalafx.scene.chart.NumberAxis
  * @see scalafx.scene.chart.XYChart
  */
class EnsembleAdvancedLineChart extends EnsembleExample {

  def getContent = {

    // Create sample data. Here we use a collection of (x,y) pairs
    val xyData = Seq(
      20d -> 50d,
      40d -> 80d,
      50d -> 90d,
      70d -> 30d,
      170d -> 122d
    )

    // Prepare series
    val series = new XYChart.Series[Number, Number] {
      name = "Data Series 1"
      data() ++= xyData.map {
        case (x, y) => XYChart.Data[Number, Number](x, y)
      }
    }

    // setup Line chart
    new LineChart(NumberAxis("X Axis"), NumberAxis("Y Axis")) {
      title = "Basic LineChart"
      data() += series
    }
  }
}