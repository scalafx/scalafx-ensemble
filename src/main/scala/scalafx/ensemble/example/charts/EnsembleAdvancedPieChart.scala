package scalafx.ensemble.example.charts

import javafx.scene.chart.{ PieChart => jfxPC }
import scalafx.collections.ObservableBuffer
import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.geometry.Insets.sfxInsets2jfx
import scalafx.scene.chart.PieChart
import scalafx.scene.control.Label
import scalafx.scene.layout.VBox
import scalafx.scene.text.Font

class EnsembleAdvancedPieChart extends EnsembleExample {
  def getContent = {
    new VBox {
      vgrow = scalafx.scene.layout.Priority.ALWAYS
      hgrow = scalafx.scene.layout.Priority.ALWAYS
      spacing = 10
      margin = Insets(50, 0, 0, 50)
      content = List(
        new Label {
          text = "Ensemble Advanced Pie Chart"
          font = new Font("Verdana", 20)
        },
        createPieChart)
    }
  }

  val createPieChart = {

    val data1 = new jfxPC.Data("Sun", 20)
    val data2 = new jfxPC.Data("IBM", 12)
    val data3 = new jfxPC.Data("HP", 25)
    val data4 = new jfxPC.Data("Dell", 22)
    val data5 = new jfxPC.Data("Apple", 30)

    val pieChartDataSeq = List(data1, data2, data3, data4, data5).toSeq
    val observPieChartData = ObservableBuffer[jfxPC.Data](pieChartDataSeq)
    val pieChart = new PieChart {
      data = observPieChartData
      id = "Advanced Pie Chart"
      title = "Pie Chart Sample"
    }
    pieChart
  }
}