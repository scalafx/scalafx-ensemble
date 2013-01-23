package scalafx.ensemble.example.controls

import scalafx.Includes._
import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.scene.control.Tab
//import scalafx.scene.control.Tab.sfxTab2jfx
import scalafx.scene.control.TabPane
import scalafx.scene.control.TabPane.TabClosingPolicy
import scalafx.scene.layout.VBox
import scalafx.scene.text.Font
import scalafx.scene.text.Text
import scalafx.geometry.Side

class EnsembleTab extends EnsembleExample {
  def getContent = {
    new VBox {
      vgrow = javafx.scene.layout.Priority.ALWAYS
      hgrow = javafx.scene.layout.Priority.ALWAYS
      spacing = 10
      margin = Insets(50, 0, 0, 50)
      content = List(
        new Text {
          text = "Ensemble Tabs"
          font = new Font("Verdana", 20)
        },
        new TabPane {
          maxWidth = 400
          maxHeight = 150
          tabs = Seq(new Tab { text = "Tab1 - NonClosable"; closable = false }, new Tab { text = "Tab2 - NonClosable"; closable = false }, new Tab { text = "Tab3 - Closable"; closable = true })
        },
        new Text {
          text = "Ensemble Tabs - Non Closable"
          font = new Font("Verdana", 20)
        },
        new TabPane {
          maxWidth = 400
          maxHeight = 150
          tabs = Seq(new Tab { text = "Tab 1" }, new Tab { text = "Tab 2" }, new Tab { text = "Tab 3" })
          tabClosingPolicy = TabClosingPolicy.UNAVAILABLE
          side = Side.TOP
        })
    }
  }
}