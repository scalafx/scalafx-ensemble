package scalafx.ensemble.example.charts

import scalafx.Includes._
import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.scene.chart.BubbleChart
//import scalafx.scene.chart.BubbleChart.sfxBubbleChart2jfx
import scalafx.scene.chart.NumberAxis
import scalafx.scene.chart.XYChart
import scalafx.scene.control.Label
import scalafx.scene.layout.VBox
import scalafx.scene.text.Font

class EnsembleBubbleChart extends EnsembleExample {
  def getContent = {
    new VBox {
      vgrow = javafx.scene.layout.Priority.ALWAYS
      hgrow = javafx.scene.layout.Priority.ALWAYS
      spacing = 10
      margin = Insets(50, 0, 0, 50)
      content = List(
        new Label {
          text = "Ensemble Bubble Chart"
          font = new Font("Verdana", 20)
        },
        createBubbleChart)
    }
  }

  val createBubbleChart = {
    val xAxis = NumberAxis("X", 0d, 140d, 20d)
    val yAxis = NumberAxis("Y", 0d, 140d, 20d)

    val bcSeries1 = new XYChart.Series[Number, Number]()
    bcSeries1.setName("Series 1")
    // create sample data
    bcSeries1.getData().add(XYChart.Data[Number, Number](30d, 40d, "10d"))
    bcSeries1.getData().add(XYChart.Data[Number, Number](60d, 20d, "13d"))
    bcSeries1.getData().add(XYChart.Data[Number, Number](10d, 90d, "7d"))
    bcSeries1.getData().add(XYChart.Data[Number, Number](100d, 40d, "10d"))
    bcSeries1.getData().add(XYChart.Data[Number, Number](50d, 23d, "5d"))

    val bcSeries2 = new XYChart.Series[Number, Number]()
    bcSeries2.setName("Series 2")
    // create sample data
    bcSeries2.getData().add(XYChart.Data[Number, Number](13d, 100d, "7d"))
    bcSeries2.getData().add(XYChart.Data[Number, Number](20d, 80d, "13d"))
    bcSeries2.getData().add(XYChart.Data[Number, Number](100d, 60d, "10d"))
    bcSeries2.getData().add(XYChart.Data[Number, Number](30d, 40d, "6d"))
    bcSeries2.getData().add(XYChart.Data[Number, Number](50d, 20d, "12d"))

    val bcSeries3 = new XYChart.Series[Number, Number]()
    bcSeries3.setName("Series 3")
    // create sample data
    bcSeries3.getData().add(XYChart.Data[Number, Number](17d, 55d, "7d"))
    bcSeries3.getData().add(XYChart.Data[Number, Number](67d, 98d, "13d"))
    bcSeries3.getData().add(XYChart.Data[Number, Number](45d, 23d, "10d"))
    bcSeries3.getData().add(XYChart.Data[Number, Number](89d, 87d, "6d"))
    bcSeries3.getData().add(XYChart.Data[Number, Number](59d, 34d, "12d"))

    
    //Bubble Chart
    val bubbleChart = BubbleChart[Number, Number](xAxis, yAxis)
    bubbleChart.getData.add(bcSeries1)
    bubbleChart.getData.add(bcSeries2)
    bubbleChart.getData.add(bcSeries3)
    bubbleChart
  }
}
