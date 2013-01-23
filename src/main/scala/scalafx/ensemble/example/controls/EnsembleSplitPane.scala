package scalafx.ensemble.example.controls

import scalafx.Includes._
import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
//import scalafx.scene.Node.sfxNode2jfx
import scalafx.scene.control.SplitPane
//import scalafx.scene.control.SplitPane.sfxSplitPane2jfx
import scalafx.scene.layout.Region
import scalafx.scene.layout.VBox
import scalafx.scene.text.Font
import scalafx.scene.text.Text
import scalafx.scene.control.Button

class EnsembleSplitPane extends EnsembleExample {
  def getContent = {
    
    //Style Sheet loaded from external 
    val hiddenSplitPaneCss = this.getClass.getResource("/scalafx/ensemble/css/HiddenSplitPane.css").toExternalForm()
    // Region that will be used in the split pane
    val reg1 = new Region {
      styleClass = List("rounded")
    }
    val reg2 = new Region {
      styleClass = List("rounded")
    }
    val reg3 = new Region {
      styleClass = List("rounded")
    }
    //SplitPane
    val splitPane = new SplitPane()
    splitPane.setDividerPositions(0.20,0.80)
    splitPane.items.addAll(reg1, reg2, reg3)
    splitPane.setId("hiddenSplitter")
    splitPane.getStylesheets().add(hiddenSplitPaneCss)

    new VBox {
      vgrow = javafx.scene.layout.Priority.ALWAYS
      hgrow = javafx.scene.layout.Priority.ALWAYS
      spacing = 10
      margin = Insets(50, 0, 0, 50)
      content = List(
        new Text {
          text = "Ensemble Hidden Split Pane"
          font = new Font("Verdana", 20)
        },
        new Text {
          text = "------------------------------------------------------------------------"
          font = new Font("Verdana", 8)
          style = "-fx-font-weight: bold"

        }, splitPane)
    }
  }
}