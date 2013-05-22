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

import scalafx.collections.ObservableBuffer
import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.scene.chart.BarChart
import scalafx.scene.chart.CategoryAxis
import scalafx.scene.chart.NumberAxis
import scalafx.scene.chart.XYChart
import scalafx.scene.control.Label
import scalafx.scene.layout.VBox
import scalafx.scene.text.Font
import scalafx.scene.layout.Priority

class EnsembleBarChart extends EnsembleExample {
  def getContent = {
    new VBox {
      vgrow = Priority.ALWAYS
      hgrow = Priority.ALWAYS
      spacing = 10
      margin = Insets(50, 0, 0, 50)
      content = List(
        new Label {
          text = "Ensemble Bar Chart"
          font = new Font("Verdana", 20)
        },
        barChart)
    }
  }

  lazy val barChart = {
    val years = Array("2007", "2008", "2009")

    val xAxis = new CategoryAxis()
    xAxis.setCategories(ObservableBuffer(years.toSeq))

    val yAxis = NumberAxis("Units Sold", 0.0d, 3000.0d, 1000.0d)

    val bcSeries1 = new XYChart.Series[String, Number]()
    bcSeries1.setName("Apples")
    // create sample data
    val sampleData1 = Seq(567d, 1292d, 1292d)
    sampleData1.zip(years).foreach { case (data, year) =>
      bcSeries1.getData().add(XYChart.Data[String, Number](year, data))
    }

    val bcSeries2 = new XYChart.Series[String, Number]()
    bcSeries2.setName("Lemons")
    // create sample data
    val sampleData2 = Seq(956, 1665, 2559)
    sampleData2.zip(years).foreach { case (data, year) =>
      bcSeries2.getData().add(XYChart.Data[String, Number](year, data))
    }

    val bcSeries3 = new XYChart.Series[String, Number]()
    bcSeries3.setName("Oranges")
    // create sample data
    val sampleData3 = Seq(1154, 1927, 2774)
    sampleData3.zip(years).foreach { case (data, year) =>
      bcSeries3.getData().add(XYChart.Data[String, Number](year, data))
    }

    //Bar Chart
    val barChart = BarChart[String, Number](xAxis, yAxis)
    val chartData = barChart.getData
    Seq(bcSeries1, bcSeries2, bcSeries3).foreach {series =>
      chartData.add(series)
    }
    barChart.setCategoryGap(25.0d)
    barChart

  }
}
