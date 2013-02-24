package scalafx.ensemble.example.charts

import javafx.event.EventHandler
import javafx.scene.chart.{ PieChart => jfxPC }
import javafx.scene.input.MouseEvent
import scalafx.collections.ObservableBuffer
import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.geometry.Insets.sfxInsets2jfx
import scalafx.scene.chart.PieChart
import scalafx.scene.chart.PieChart.sfxPieChart2jfx
import scalafx.scene.control.Label
import scalafx.scene.layout.VBox
import scalafx.scene.text.Font

class EnsembleDrilldownPieChart extends EnsembleExample {
  def getContent = {
    new VBox {
      vgrow = javafx.scene.layout.Priority.ALWAYS
      hgrow = javafx.scene.layout.Priority.ALWAYS
      spacing = 10
      margin = Insets(50, 0, 0, 50)
      content = List(
        new Label {
          text = "Ensemble Drilldown Pie Chart"
          font = new Font("Verdana", 20)
        },
        createPieChart)
    }
  }

  val createPieChart = {
    //Drilldown Pie Chart sytle css
    val drilldownPieChartCss = this.getClass.getResource("DrilldownChart.css").toExternalForm()

    val data1 = new jfxPC.Data("A", 20)
    val data2 = new jfxPC.Data("B", 30)
    val data3 = new jfxPC.Data("C", 10)
    val data4 = new jfxPC.Data("D", 40)

    val pieChartDataSeq = List(data1, data2, data3, data4).toSeq
    val observPieChartData = ObservableBuffer[jfxPC.Data](pieChartDataSeq)
    val pieChart = new PieChart {
      data = observPieChartData
      title = "DrillDown Pie Chart"
    }
    pieChart.getStylesheets().add(drilldownPieChartCss)

    drillDownData(pieChart, data1, "a")
    drillDownData(pieChart, data2, "b")
    drillDownData(pieChart, data3, "c")
    drillDownData(pieChart, data4, "d")

    pieChart
  }

  def drillDownData = (pie: PieChart, pieData: jfxPC.Data, labelPrefix: String) => {
    pieData.getNode().setOnMouseClicked(new EventHandler[MouseEvent]() {
      override def handle(me: MouseEvent) {
        val dataLabel1 = new jfxPC.Data(labelPrefix + "-1", 7)
        val dataLabel2 = new jfxPC.Data(labelPrefix + "-2", 2)
        val dataLabel3 = new jfxPC.Data(labelPrefix + "-3", 5)
        val dataLabel4 = new jfxPC.Data(labelPrefix + "-4", 3)
        val dataLabel5 = new jfxPC.Data(labelPrefix + "-5", 2)

        val pieChartDataSeq = List(dataLabel1, dataLabel2, dataLabel4, dataLabel5).toSeq
        val observPieChartData = ObservableBuffer[jfxPC.Data](pieChartDataSeq)
        pie.data = observPieChartData
      }
    })
  }
  
}
