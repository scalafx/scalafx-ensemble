package scalafx.ensemble.example.charts

import scalafx.collections.ObservableBuffer
import scalafx.collections.ObservableBuffer.observableBuffer2ObservableList
import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.scene.chart.BarChart
import scalafx.scene.chart.BarChart.sfxBarChart2jfx
import scalafx.scene.chart.CategoryAxis
import scalafx.scene.chart.CategoryAxis.sfxCategoryAxis2jfx
import scalafx.scene.chart.NumberAxis
import scalafx.scene.chart.NumberAxis.sfxNumberAxis2jfx
import scalafx.scene.chart.XYChart
import scalafx.scene.control.Label
import scalafx.scene.layout.VBox
import scalafx.scene.text.Font

class EnsembleAdvHorizontalBarChart extends EnsembleExample {
  def getContent = {
    new VBox {
      vgrow = javafx.scene.layout.Priority.ALWAYS
      hgrow = javafx.scene.layout.Priority.ALWAYS
      spacing = 10
      margin = Insets(50, 0, 0, 50)
      content = List(
        new Label {
          text = "Ensemble Horizontal Bar Chart"
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

    val xAxis = new NumberAxis()
    xAxis.setTickLabelFormatter(NumberAxis.DefaultFormatter(xAxis, "$", null))
    xAxis.setLabel("Price")

    val yAxis = new CategoryAxis()
    yAxis.setLabel("Year")
    yAxis.setCategories(ObservableBuffer[String](years.toSeq))

    // add starting data
    val series1 = new XYChart.Series[Number, String]()
    series1.setName("Data Series 1")
    // create sample data
    series1.getData().add(XYChart.Data[Number, String](567, years(0)))
    series1.getData().add(XYChart.Data[Number, String](1292, years(1)))
    series1.getData().add(XYChart.Data[Number, String](2180, years(2)))

    val series2 = new XYChart.Series[Number, String]()
    series2.setName("Data Series 2")
    // create sample data
    series2.getData().add(XYChart.Data[Number, String](956, years(0)))
    series2.getData().add(XYChart.Data[Number, String](1665, years(1)))
    series2.getData().add(XYChart.Data[Number, String](2450, years(2)))

    val series3 = new XYChart.Series[Number, String]()
    series3.setName("Data Series 3")
    // create sample data
    series3.getData().add(XYChart.Data[Number, String](800, years(0)))
    series3.getData().add(XYChart.Data[Number, String](1000, years(1)))
    series3.getData().add(XYChart.Data[Number, String](2500, years(2)))

    val series4 = new XYChart.Series[Number, String]()
    series4.setName("Data Series 4")
    // create sample data
    series4.getData().add(XYChart.Data[Number, String](786, years(0)))
    series4.getData().add(XYChart.Data[Number, String](2100, years(1)))
    series4.getData().add(XYChart.Data[Number, String](450, years(2)))

    // setup chart
    val bc = BarChart[Number, String](xAxis, yAxis)
    bc.barGap = 5
    bc.categoryGap = 12
    bc.setTitle("Horizontal Bar Chart Example")
    bc.getData().add(series1)
    bc.getData().add(series2)
    bc.getData().add(series3)
    bc.getData().add(series4)
    bc
  }
}