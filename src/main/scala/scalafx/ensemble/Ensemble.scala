package scalafx.ensemble

import javafx.geometry.Pos
import javafx.geometry.VPos
import javafx.stage.StageStyle
import scalafx.application.JFXApp
import scalafx.scene.Scene._
import scalafx.scene.control.Accordion._
import scalafx.scene.control.Labeled._
import scalafx.scene.control.TitledPane._
import scalafx.scene.control.Label
import scalafx.scene.image.Image
import scalafx.scene.image.ImageView
import scalafx.scene.layout.Pane._
import scalafx.scene.layout.BorderPane
import scalafx.scene.layout.HBox
import scalafx.scene.layout.VBox
import scalafx.scene.shape.Circle._
import scalafx.scene.text.Font
import scalafx.scene.text.Text
import scalafx.scene.Scene
import scalafx.stage.Stage._
import scalafx.stage.Stage
import scalafx.geometry.Insets
import javafx.scene.layout.Priority
import scalafx.scene.layout.Region
import scalafx.scene.control.Button
import scalafx.scene.control.SplitPane
import scalafx.scene.control.TabPane
import scalafx.scene.control.Tab
import scalafx.scene.control.TextField
import scalafx.scene.control.TitledPane
import scalafx.scene.shape.Arc
import javafx.scene.paint.Color
import scalafx.scene.control.Accordion
import scalafx.scene.control.ListView
import scalafx.scene.control.ToolBar
import scalafx.scene.layout.GridPane

object Ensemble extends JFXApp {

  val tabs = new TabPane {
    hgrow = javafx.scene.layout.Priority.ALWAYS
  } += (new Tab {
    text = "tab"
    content = new TextField {
      text = "text"
    }
  }) += (new Tab {
    text = "tab"
    content = new TextField {
      text = "text"
    }
  }) += (new Tab {
    text = "tab"
    content = new TextField {
      text = "text"
    }
  })

  stage = new Stage {
    scene = new Scene(1200, 768) {
      content = new BorderPane {
        top = new VBox {
          vgrow = javafx.scene.layout.Priority.ALWAYS
          hgrow = javafx.scene.layout.Priority.ALWAYS
          minWidth = 1200
          content = List(new ToolBar {
            minHeight = 76
            prefHeight = 76
            maxHeight = 76
            content = List(
              new ImageView {
                image = new Image(this.getClass.getResourceAsStream("images/logo.png"))
                margin = Insets(0, 0, 0, 10)
              },
              new Region {
                minWidth = 200
              },
              new Button {
                minWidth = 120
                minHeight = 66
                id = "newButton"
              })
            minWidth = 1200
            id = "mainToolBar"
          }, new ToolBar {
            id = "page-toolbar"
            minHeight = 29
            maxWidth = Double.MaxValue
            content = List(new Label {
              text = "toolbar"
            },
              new Button {
                text = "toolbar"
                styleClass = List("button")
              }, new Button {
                text = "toolbar"
                styleClass = List("button")
              }, new Button {
                text = "toolbar"
                styleClass = List("button")
              })
          })
        }
        center = new BorderPane {
          minHeight = 768
          center = new SplitPane {
            maxWidth = java.lang.Double.MAX_VALUE
            maxHeight = java.lang.Double.MAX_VALUE
            dividerPositions = 0
            id = "page-splitpane"
            items.addAll(new Accordion {
              hgrow = javafx.scene.layout.Priority.ALWAYS
              minWidth = 200
              panes = List(
                new TitledPane {
                  content = new ListView {
                  }
                })
            }, tabs)
          }
        }
        styleClass.add("application")
      }
    }
    scene.get.getStylesheets.add(this.getClass.getResource("ensemble.css").toExternalForm)
  }
  stage.initStyle(StageStyle.UNDECORATED)
}