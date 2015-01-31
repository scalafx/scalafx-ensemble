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
import scalafx.scene.chart.BarChart
import scalafx.scene.chart.CategoryAxis
import scalafx.scene.chart.NumberAxis
import scalafx.scene.chart.XYChart

/** A bar chart that uses CSS to display stacks of car images to indicate data values
  * for categories.
  *
  * @see scalafx.scene.chart.BarChart
  * @see scalafx.scene.chart.Chart
  * @see scalafx.scene.chart.CategoryAxis
  * @see scalafx.scene.chart.NumberAxis
  * @resource ImageBarChart.css
  * @resource sedan-s.png
  * @resource suv-s.png
  * @resource truck-s.png
  * @resource van-s.png
  */
class EnsembleImageBarChart extends EnsembleExample {

  def getContent = new BarChart(new CategoryAxis(), new NumberAxis()) {
    legendVisible = false
    stylesheets += this.getClass.getResource("ImageBarChart.css").toExternalForm
    data = new XYChart.Series[String, Number]() {
      name = "Sales Per Product"
      data = Seq(
        XYChart.Data[String, Number]("SUV", 120),
        XYChart.Data[String, Number]("Sedan", 50),
        XYChart.Data[String, Number]("Truck", 180),
        XYChart.Data[String, Number]("Van", 20)
      )
    }
  }
}