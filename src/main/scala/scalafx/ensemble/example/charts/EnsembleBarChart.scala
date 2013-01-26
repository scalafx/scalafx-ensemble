package scalafx.ensemble.example.charts

import scalafx.Includes._
import scalafx.collections.ObservableBuffer
import scalafx.collections.ObservableBuffer.observableBuffer2ObservableList
import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.scene.chart.BarChart
//import scalafx.scene.chart.BarChart.sfxBarChart2jfx
import scalafx.scene.chart.CategoryAxis
//import scalafx.scene.chart.CategoryAxis.sfxCategoryAxis2jfx
import scalafx.scene.chart.NumberAxis
import scalafx.scene.chart.XYChart
import scalafx.scene.control.Label
import scalafx.scene.layout.VBox
import scalafx.scene.text.Font

class EnsembleBarChart extends EnsembleExample {
  def getContent = {
    new VBox {
      vgrow = javafx.scene.layout.Priority.ALWAYS
      hgrow = javafx.scene.layout.Priority.ALWAYS
      spacing = 10
      margin = Insets(50, 0, 0, 50)
      content = List(
        new Label {
          text = "Ensemble Bar Chart"
          font = new Font("Verdana", 20)
        },
        createBarChart)
    }
  }

  val createBarChart = {
    val years = new Array[String](3)
    years(0) = "2007"
    years(1) = "2008"
    years(2) = "2009"

    val xAxis = new CategoryAxis()
    xAxis.setCategories(ObservableBuffer[String](years.toSeq))

    val yAxis = NumberAxis("Units Sold", 0.0d, 3000.0d, 1000.0d)

    val bcSeries1 = new XYChart.Series[String, Number]()
    bcSeries1.setName("Apples")
    // create sample data
    bcSeries1.getData().add(XYChart.Data[String, Number](years(0), 567d))
    bcSeries1.getData().add(XYChart.Data[String, Number](years(1), 1292d))
    bcSeries1.getData().add(XYChart.Data[String, Number](years(2), 1292d))

    val bcSeries2 = new XYChart.Series[String, Number]()
    bcSeries2.setName("Lemons")
    // create sample data
    bcSeries2.getData().add(XYChart.Data[String, Number](years(0), 956))
    bcSeries2.getData().add(XYChart.Data[String, Number](years(1), 1665))
    bcSeries2.getData().add(XYChart.Data[String, Number](years(2), 2559))

    val bcSeries3 = new XYChart.Series[String, Number]()
    bcSeries3.setName("Oranges")
    // create sample data
    bcSeries3.getData().add(XYChart.Data[String, Number](years(0), 1154))
    bcSeries3.getData().add(XYChart.Data[String, Number](years(1), 1927))
    bcSeries3.getData().add(XYChart.Data[String, Number](years(2), 2774))

    //Bar Chart
    val barChart = BarChart[String, Number](xAxis, yAxis)
    barChart.getData.add(bcSeries1)
    barChart.getData.add(bcSeries2)
    barChart.getData.add(bcSeries3)
    barChart.setCategoryGap(25.0d)
    barChart
  }
}