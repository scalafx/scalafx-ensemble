/*
 * Copyright (c) 2012-2015, ScalaFX Ensemble Project
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

import javafx.scene.{chart => jfxsc}
import javafx.scene.{layout => jfxsl}
import javafx.scene.{shape => jfxss}
import javafx.{scene => jfxs}
import scala.collection.JavaConversions.asScalaIterator
import scala.collection.mutable
import scalafx.Includes._
import scalafx.animation.FadeTransition
import scalafx.application.JFXApp
import scalafx.collections.ObservableBuffer
import scalafx.event.ActionEvent
import scalafx.scene.chart.{XYChart, Axis, NumberAxis}
import scalafx.scene.control.{Label, Tooltip}
import scalafx.scene.layout.{GridPane, Region}
import scalafx.scene.shape.{Line, LineTo, MoveTo, Path}
import scalafx.scene.{Node, Scene}
import scalafx.ensemble.commons.EnsembleExample


/**
 * A custom candlestick chart.
 *
 * @see javafx.scene.chart.Axis
 * @see javafx.scene.chart.Chart
 * @see javafx.scene.chart.NumberAxis
 * @see javafx.scene.chart.XYChart
 *
 * @resource /scalafx/ensemble/example/charts/AdvCandleStickChartSample.css
 */
class EnsembleAdvCandleStickChart extends EnsembleExample {

  case class CandleStick(day: Int, open: Double, close: Double, high: Double, low: Double, average: Double)

  def getContent = {

   val data = Array[CandleStick](
    CandleStick(1, 25, 20, 32, 16, 20),
    CandleStick(2, 26, 30, 33, 22, 25),
    CandleStick(3, 30, 38, 40, 20, 32),
    CandleStick(4, 24, 30, 34, 22, 30),
    CandleStick(5, 26, 36, 40, 24, 32),
    CandleStick(6, 28, 38, 45, 25, 34),
    CandleStick(7, 36, 30, 44, 28, 39),
    CandleStick(8, 30, 18, 36, 16, 31),
    CandleStick(9, 40, 50, 52, 36, 41),
    CandleStick(10, 30, 34, 38, 28, 36),
    CandleStick(11, 24, 12, 30, 8, 32.4),
    CandleStick(12, 28, 40, 46, 25, 31.6),
    CandleStick(13, 28, 18, 36, 14, 32.6),
    CandleStick(14, 38, 30, 40, 26, 30.6),
    CandleStick(15, 28, 33, 40, 28, 30.6),
    CandleStick(16, 25, 10, 32, 6, 30.1),
    CandleStick(17, 26, 30, 42, 18, 27.3),
    CandleStick(18, 20, 18, 30, 10, 21.9),
    CandleStick(19, 20, 10, 30, 5, 21.9),
    CandleStick(20, 26, 16, 32, 10, 17.9),
    CandleStick(21, 38, 40, 44, 32, 18.9),
    CandleStick(22, 26, 40, 41, 12, 18.9),
    CandleStick(23, 30, 18, 34, 10, 18.9),
    CandleStick(24, 12, 23, 26, 12, 18.2),
    CandleStick(25, 30, 40, 45, 16, 18.9),
    CandleStick(26, 25, 35, 38, 20, 21.4),
    CandleStick(27, 24, 12, 30, 8, 19.6),
    CandleStick(28, 23, 44, 46, 15, 22.2),
    CandleStick(29, 28, 18, 30, 12, 23),
    CandleStick(30, 28, 18, 30, 12, 23.2),
    CandleStick(31, 28, 18, 30, 12, 22)
  )

   createChart(data)

  }



  protected def createChart( data : Array[CandleStick]): CandleStickChart = {
      //Style Sheet loaded from external
    val css = this.getClass.getResource("AdvCandleStickChartSample.css").toExternalForm

    val xAxis = new NumberAxis("Day", 0, 32, 1) {
      minorTickCount = 0
    }
    val yAxis = NumberAxis("Price")

    val seriesData = data.map {d => XYChart.Data[Number, Number](d.day, d.open, d)}

    val series = XYChart.Series[Number, Number](ObservableBuffer(seriesData.toSeq))

    new CandleStickChart(xAxis, yAxis) {
      title = "Custom Candle Stick Chart"
      data = ObservableBuffer(series)
      getStylesheets += css
    }
  }


  /**
   * A candlestick chart is a style of bar-chart used primarily to describe price movements of a security, derivative,
   * or currency over time.
   *
   * The Data Y value is used for the opening price and then the close, high and low values are stored in the Data's
   * extra value property using a CandleStick object.
   */
  class CandleStickChart(xa: Axis[Number], ya: Axis[Number])
    extends jfxsc.XYChart[Number, Number](xa, ya) {

    setAnimated(false)
    xAxis.animated = false
    yAxis.animated = false

    /**
     * Construct a new CandleStickChart with the given axis and data.
     *
     * @param xAxis The x axis to use
     * @param yAxis The y axis to use
     * @param data The data to use, this is the actual list used so any changes to it will be reflected in the chart
     */
    def this(xAxis: Axis[Number], yAxis: Axis[Number], data: ObservableBuffer[jfxsc.XYChart.Series[Number, Number]]) {
      this(xAxis, yAxis)
      setData(data)
    }

    def title: String = getTitle

    def title_=(t: String) {
      setTitle(t)
    }

    def data: ObservableBuffer[jfxsc.XYChart.Series[Number, Number]] = getData

    def data_=(d: ObservableBuffer[jfxsc.XYChart.Series[Number, Number]]) {
      setData(d)
    }

    def plotChildren = getPlotChildren
    def xAxis = getXAxis
    def yAxis = getYAxis

    /** Called to update and layout the content for the plot */
    override protected def layoutPlotChildren() {
      if (data == null) {
        return
      }

      for (series <- data) {
        val seriesPath: Option[Path] = series.node() match {
          case path: jfxss.Path => Some(path)
          case _                => None
        }
        seriesPath.foreach(_.elements.clear())

        for (item <- getDisplayedDataIterator(series)) {
          item.extraValue() match {
            case dayValues: CandleStick =>
              val x = xAxis.displayPosition(dayValues.day)

              item.node() match {
                case candle: Candle =>
                  val yOpen = yAxis.displayPosition(dayValues.open)
                  val yClose = yAxis.displayPosition(dayValues.close)
                  val yHigh = yAxis.displayPosition(dayValues.high)
                  val yLow = yAxis.displayPosition(dayValues.low)
                  val candleWidth = xAxis match {
                    case xa: jfxsc.NumberAxis => xa.displayPosition(xa.tickUnit()) * 0.90
                    case _                    => -1
                  }
                  candle.update(yClose - yOpen, yHigh - yOpen, yLow - yOpen, candleWidth)
                  candle.updateTooltip(item.YValue().doubleValue, dayValues.close, dayValues.high, dayValues.low)
                  candle.layoutX = x
                  candle.layoutY = yOpen
                case _              =>
              }

              seriesPath.foreach {
                p =>
                  val yAverage = yAxis.displayPosition(dayValues.average)
                  if (p.elements.isEmpty) {
                    p.elements += MoveTo(x, yAverage)
                  } else {
                    p.elements += LineTo(x, yAverage)
                  }
              }
            case _                      =>
          }
        }
      }
    }

    override protected def dataItemChanged(item: jfxsc.XYChart.Data[Number, Number]) {}

    override protected def dataItemAdded(series: jfxsc.XYChart.Series[Number, Number],
                                         itemIndex: Int, item: jfxsc.XYChart.Data[Number, Number]) {
      val candle = Candle(getData.indexOf(series), item, itemIndex)
      if (shouldAnimate) {
        candle.opacity = 0
        plotChildren += candle
        new FadeTransition(500 ms, candle) {
          toValue = 1
        }.play()
      } else {
        plotChildren += candle
      }
      if (series.node() != null) {
        series.node().toFront()
      }
    }

    override protected def dataItemRemoved(item: jfxsc.XYChart.Data[Number, Number], series: jfxsc.XYChart.Series[Number, Number]) {
      val candle = item.node()
      if (shouldAnimate) {
        new FadeTransition(500 ms, candle) {
          toValue = 0
          onFinished = (_: ActionEvent) => plotChildren -= candle

        }.play()
      }
      else {
        plotChildren -= candle
      }
    }

    override protected def seriesAdded(series: jfxsc.XYChart.Series[Number, Number], seriesIndex: Int) {
      for (j <- 0 until series.data().size) {
        val item = series.data()(j)
        val candle = Candle(seriesIndex, item, j)
        if (shouldAnimate) {
          candle.opacity = 0
          plotChildren += candle
          val ft = new FadeTransition(500 ms, candle) {
            toValue = 1
          }
          ft.play()
        }
        else {
          plotChildren += candle
        }
      }
      val seriesPath = new Path {
        styleClass = Seq("candlestick-average-line", "series" + seriesIndex)
      }
      series.node = seriesPath
      plotChildren += seriesPath
    }

    override protected def seriesRemoved(series: jfxsc.XYChart.Series[Number, Number]) {
      for (d <- series.getData) {
        val candle = d.node()
        if (shouldAnimate) {
          new FadeTransition(500 ms, candle) {
            toValue = 0
            onFinished = (_: ActionEvent) =>  plotChildren -= candle
          }.play()
        }
        else {
          plotChildren -= candle
        }
      }
    }


    /**
     * This is called when the range has been invalidated and we need to update it. If the axis are auto
     * ranging then we compile a list of all data that the given axis has to plot and call invalidateRange() on the
     * axis passing it that data.
     */
    override protected def updateAxisRange() {

      if (xAxis.isAutoRanging) {
        val xData = for (series <- data; seriesData <- series.data()) yield seriesData.XValue()
        xAxis.invalidateRange(xData)
      }

      if (yAxis.isAutoRanging) {
        val yData = mutable.ListBuffer.empty[Number]
        for (series <- data; seriesData <- series.data()) {
          seriesData.extraValue() match {
            case extras: CandleStick =>
              yData += extras.high
              yData += extras.low
            case _                   =>
              yData += seriesData.YValue()
          }
        }

        yAxis.invalidateRange(yData)
      }
    }
  }

  private object Candle {
    /**
     * Create a new Candle node to represent a single data item
     *
     * @param seriesIndex The index of the series the data item is in
     * @param item        The data item to create node for
     * @param itemIndex   The index of the data item in the series
     * @return New candle node to represent the give data item
     */
    def apply(seriesIndex: Int, item: XYChart.Data[_, _], itemIndex: Int): Node = {
      var candle = item.node()
      candle match {
        case c: Candle =>
          c.setSeriesAndDataStyleClasses("series" + seriesIndex, "data" + itemIndex)
        case _         =>
          candle = new Candle("series" + seriesIndex, "data" + itemIndex)
          item.node = candle
      }
      candle
    }

  }

  /** Candle node used for drawing a candle */
  private class Candle(private var seriesStyleClass: String,
                       private var dataStyleClass: String) extends jfxs.Group {

    private val highLowLine: Line = new Line
    private val bar: Region = new Region
    private var openAboveClose: Boolean = true
    private val tooltip: Tooltip = new Tooltip

    def styleClass = getStyleClass

    setAutoSizeChildren(false)
    getChildren.addAll(highLowLine, bar)
    updateStyleClasses()
    tooltip.graphic = new TooltipContent()
    Tooltip.install(bar, tooltip)

    def setSeriesAndDataStyleClasses(seriesStyleClass: String, dataStyleClass: String) {
      this.seriesStyleClass = seriesStyleClass
      this.dataStyleClass = dataStyleClass
      updateStyleClasses()
    }

    def update(closeOffset: Double, highOffset: Double, lowOffset: Double, candleWidth: Double) {
      openAboveClose = closeOffset > 0
      updateStyleClasses()
      highLowLine.startY = highOffset
      highLowLine.endY = lowOffset
      val cw = if (candleWidth == -1) {
        // FIXME: It should be possible to access this method without delegate, it is not the same as setPrefWidth
        bar.delegate.prefWidth(-1)
      } else
        candleWidth
      if (openAboveClose) {
        bar.resizeRelocate(-cw / 2, 0, cw, closeOffset)
      }
      else {
        bar.resizeRelocate(-cw / 2, closeOffset, cw, closeOffset * -1)
      }
    }

    def updateTooltip(open: Double, close: Double, high: Double, low: Double) {
      val tooltipContent: TooltipContent = tooltip.graphic().asInstanceOf[TooltipContent]
      tooltipContent.update(open, close, high, low)
    }

    private def updateStyleClasses() {
      val closeVsOpen = if (openAboveClose) "open-above-close" else "close-above-open"

      styleClass = Seq("candlestick-candle", seriesStyleClass, dataStyleClass)
      highLowLine.styleClass = Seq("candlestick-line", seriesStyleClass, dataStyleClass, closeVsOpen)
      bar.styleClass = Seq("candlestick-bar", seriesStyleClass, dataStyleClass, closeVsOpen)
    }

  }

  private class TooltipContent extends jfxsl.GridPane {
    private val openValue = new Label()
    private val closeValue = new Label()
    private val highValue = new Label()
    private val lowValue = new Label()

    val open = new Label("OPEN:") {styleClass += "candlestick-tooltip-label"}
    val close = new Label("CLOSE:") {styleClass += "candlestick-tooltip-label"}
    val high = new Label("HIGH:") {styleClass += "candlestick-tooltip-label"}
    val low = new Label("LOW:") {styleClass += "candlestick-tooltip-label"}

    GridPane.setConstraints(open, 0, 0)
    GridPane.setConstraints(openValue, 1, 0)
    GridPane.setConstraints(close, 0, 1)
    GridPane.setConstraints(closeValue, 1, 1)
    GridPane.setConstraints(high, 0, 2)
    GridPane.setConstraints(highValue, 1, 2)
    GridPane.setConstraints(low, 0, 3)
    GridPane.setConstraints(lowValue, 1, 3)
    getChildren.addAll(open, openValue, close, closeValue, high, highValue, low, lowValue)

    def update(open: Double, close: Double, high: Double, low: Double) {
      openValue.text = open.toString
      closeValue.text = close.toString
      highValue.text = high.toString
      lowValue.text = low.toString
    }
  }

}


