package scalafx.ensemble.example.charts

import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.scene.chart.BarChart
import scalafx.scene.chart.BarChart.sfxBarChart2jfx
import scalafx.scene.chart.CategoryAxis
import scalafx.scene.chart.NumberAxis
import scalafx.scene.chart.XYChart
import scalafx.scene.control.Label
import scalafx.scene.layout.VBox
import scalafx.scene.text.Font

class EnsembleImageBarChart extends EnsembleExample {
  def getContent = {
    new VBox {
      vgrow = javafx.scene.layout.Priority.ALWAYS
      hgrow = javafx.scene.layout.Priority.ALWAYS
      spacing = 10
      margin = Insets(50, 0, 0, 50)
      content = List(
        new Label {
          text = "Ensemble Image Bar Chart"
          font = new Font("Verdana", 20)
        },
        createBarChart)
    }
  }

  val createBarChart = {
    //Style to Image Bar
    val imageBarChartCss = this.getClass.getResource("ImageBarChart.css").toExternalForm()

    val bcSeries1 = new XYChart.Series[String, Number]()
    bcSeries1.setName("Sales Per Product")
    // create sample data
    bcSeries1.getData().add(XYChart.Data[String, Number]("SUV", 120))
    bcSeries1.getData().add(XYChart.Data[String, Number]("Sedan", 50))
    bcSeries1.getData().add(XYChart.Data[String, Number]("Truck", 180))
    bcSeries1.getData().add(XYChart.Data[String, Number]("Van", 20))

    //Bar Chart
    val barChart = BarChart(new CategoryAxis(), new NumberAxis())
    barChart.setLegendVisible(false)
    barChart.getStylesheets().add(imageBarChartCss)
    barChart.getData().add(bcSeries1)
    barChart
  }
}