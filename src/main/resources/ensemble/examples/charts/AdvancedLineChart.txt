package scalafx.ensemble.example.charts

import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.scene.chart.LineChart
import scalafx.scene.chart.LineChart.sfxLineChart2jfx
import scalafx.scene.chart.NumberAxis
import scalafx.scene.chart.NumberAxis.sfxNumberAxis2jfx
import scalafx.scene.chart.XYChart
import scalafx.scene.control.Label
import scalafx.scene.layout.VBox
import scalafx.scene.text.Font

class EnsembleAdvancedLineChart extends EnsembleExample {
  def getContent = {
    new VBox {
      vgrow = javafx.scene.layout.Priority.ALWAYS
      hgrow = javafx.scene.layout.Priority.ALWAYS
      spacing = 10
      margin = Insets(50, 0, 0, 50)
      content = List(
        new Label {
          text = "Ensemble Advanced Line Chart"
          font = new Font("Verdana", 20)
        },
        createLineChart)
    }
  }

  val createLineChart = {
    val xAxis = new NumberAxis()
    xAxis.setLabel("X Axis")

    val yAxis = new NumberAxis()
    yAxis.setLabel("Y Axis")

    val bcSeries1 = new XYChart.Series[Number, Number]()
    bcSeries1.setName("Data Series 1")
    // create sample data
    bcSeries1.getData().add(XYChart.Data[Number, Number](20d, 50d))
    bcSeries1.getData().add(XYChart.Data[Number, Number](40d, 80d))
    bcSeries1.getData().add(XYChart.Data[Number, Number](50d, 90d))
    bcSeries1.getData().add(XYChart.Data[Number, Number](70d, 30d))
    bcSeries1.getData().add(XYChart.Data[Number, Number](170d, 122d))

    // setup Line chart
    val lineChart = LineChart[Number, Number](xAxis, yAxis)
    lineChart.setTitle("Basic LineChart")
    lineChart.getData.add(bcSeries1)
    lineChart
  }
}