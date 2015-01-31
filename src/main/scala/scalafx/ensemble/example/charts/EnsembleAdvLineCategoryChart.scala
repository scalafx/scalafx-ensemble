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
import scalafx.scene.chart.CategoryAxis
import scalafx.scene.chart.LineChart
import scalafx.scene.chart.NumberAxis
import scalafx.scene.chart.XYChart

/** A line chart demonstrating a CategoryAxis.
  *
  * @see scalafx.scene.chart.CategoryAxis
  * @see scalafx.scene.chart.Chart
  * @see scalafx.scene.chart.NumberAxis
  * @see scalafx.scene.chart.LineChart
  * @see scalafx.scene.chart.XYChart
  */
class EnsembleAdvLineCategoryChart extends EnsembleExample {
  def getContent = {
    // add starting data
    val series = new XYChart.Series[String, Number] {
      name = "Data Series 1"
      // create sample data
      data = Seq(
        XYChart.Data[String, Number]("Alpha", 50d),
        XYChart.Data[String, Number]("Beta", 80d),
        XYChart.Data[String, Number]("RC1", 90d),
        XYChart.Data[String, Number]("RC2", 30d),
        XYChart.Data[String, Number]("1.0", 120d),
        XYChart.Data[String, Number]("1.1", 20d)
      )
    }

    // setup chart
    new LineChart[String, Number](CategoryAxis("X Axis"), NumberAxis("Y Axis")) {
      title = "LineChart with Category Axis"
      data() += series
    }
  }
}