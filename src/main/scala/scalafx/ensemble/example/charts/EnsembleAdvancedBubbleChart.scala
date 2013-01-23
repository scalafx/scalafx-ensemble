package scalafx.ensemble.example.charts

import java.lang.{ Math => JMath }

import scalafx.Includes._
import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.scene.chart.BubbleChart
//import scalafx.scene.chart.BubbleChart.sfxBubbleChart2jfx
import scalafx.scene.chart.NumberAxis
//import scalafx.scene.chart.NumberAxis.sfxNumberAxis2jfx
import scalafx.scene.chart.XYChart
import scalafx.scene.control.Label
import scalafx.scene.layout.VBox
import scalafx.scene.text.Font

class EnsembleAdvancedBubbleChart extends EnsembleExample {
  def getContent = {
    new VBox {
      vgrow = javafx.scene.layout.Priority.ALWAYS
      hgrow = javafx.scene.layout.Priority.ALWAYS
      spacing = 10
      margin = Insets(50, 0, 0, 50)
      content = List(
        new Label {
          text = "Ensemble Advanced Bubble Chart"
          font = new Font("Verdana", 20)
        },
        createBubbleChart)
    }
  }

  val createBubbleChart = {
    val xAxis = new NumberAxis()
    xAxis.setLabel("X Axis")
    val yAxis = new NumberAxis()
    yAxis.setLabel("Y Axis")

    val bcSeries1 = new XYChart.Series[Number, Number]()
    bcSeries1.setName("Data Series 1")
    // create sample data
    for (i <- 1 to 20) {
      bcSeries1.getData().add(XYChart.Data[Number, Number]((JMath.random() * 100).asInstanceOf[Number], (JMath.random() * 100).asInstanceOf[Number]))
    }

    val bcSeries2 = new XYChart.Series[Number, Number]()
    bcSeries2.setName("Data Series 2")
    for (i <- 1 to 20) {
      bcSeries2.getData().add(XYChart.Data[Number, Number]((JMath.random() * 100).asInstanceOf[Number], (JMath.random() * 100).asInstanceOf[Number]))
    }

    //Bubble Chart
    val bubbleChart = BubbleChart[Number, Number](xAxis, yAxis)
    bubbleChart.setTitle("Advanced BubbleChart")
    bubbleChart.getData.add(bcSeries1)
    bubbleChart.getData.add(bcSeries2)
    bubbleChart

  }
}
