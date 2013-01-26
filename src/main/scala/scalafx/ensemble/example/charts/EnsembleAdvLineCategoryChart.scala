package scalafx.ensemble.example.charts

import scalafx.Includes._
import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.scene.chart.LineChart
//import scalafx.scene.chart.LineChart.sfxLineChart2jfx
import scalafx.scene.chart.NumberAxis
import scalafx.scene.chart.CategoryAxis
import scalafx.scene.chart.XYChart
import scalafx.scene.control.Label
import scalafx.scene.layout.VBox
import scalafx.scene.text.Font

class EnsembleAdvLineCategoryChart extends EnsembleExample {
  def getContent = {
    new VBox {
      vgrow = javafx.scene.layout.Priority.ALWAYS
      hgrow = javafx.scene.layout.Priority.ALWAYS
      spacing = 10
      margin = Insets(50, 0, 0, 50)
      content = List(
        new Label {
          text = "Ensemble Advanced Line Category Chart"
          font = new Font("Verdana", 20)
        },
        createLineChart)
    }
  }

  val createLineChart = {
    val xAxis = new CategoryAxis()
    xAxis.setLabel("X Axis")
    val yAxis = new NumberAxis()
    yAxis.setLabel("Y Axis")

    // add starting data
    val bcSeries1 = new XYChart.Series[String, Number]()
    bcSeries1.setName("Data Series 1")
    // create sample data
    bcSeries1.getData().add(XYChart.Data[String, Number]("Alpha", 50d))
    bcSeries1.getData().add(XYChart.Data[String, Number]("Beta", 80d))
    bcSeries1.getData().add(XYChart.Data[String, Number]("RC1", 90d))
    bcSeries1.getData().add(XYChart.Data[String, Number]("RC2", 30d))
    bcSeries1.getData().add(XYChart.Data[String, Number]("1.0", 120d))
    bcSeries1.getData().add(XYChart.Data[String, Number]("1.1", 20d))

    // setup chart
    val lineChart = LineChart[String, Number](xAxis, yAxis)
    lineChart.setTitle("LineChart with Category Axis")
    lineChart.getData.add(bcSeries1)
    lineChart
  }
}