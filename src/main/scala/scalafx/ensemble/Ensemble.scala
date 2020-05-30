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

package scalafx.ensemble

import org.scalafx.extras.{onFXAndWait, showException}
import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.ensemble.commons.PageDisplayer
import scalafx.geometry.Insets
import scalafx.scene.control._
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.layout._
import scalafx.scene.{Node, Scene}

/** The main ScalaFX Ensemble application object. */
object Ensemble extends JFXApp {

  private val Title = "ScalaFX Ensemble"

  setupUncaughtExceptionHandling()

  //
  // Example selection tree
  //
  private var centerPane: Node = PageDisplayer.choosePage("dashBoard")
  private val rootTreeItem: TreeItem[String] = new TreeItem[String](Title) {
    expanded = true
    children = EnsembleTree.create().getTree
  }

  private val controlsView: TreeView[String] = new TreeView[String]() {
    minWidth = 200
    maxWidth = 200
    editable = true
    root = rootTreeItem
    id = "page-tree"
  }
  controlsView.selectionModel().selectionMode = SelectionMode.Single
  controlsView.selectionModel().selectedItem.onChange {
    (_, _, newItem) => {
      val pageCode = (newItem.isLeaf, Option(newItem.getParent)) match {
        case (true, Some(parent)) => parent.getValue.toLowerCase + " > " + newItem.getValue
        case (false, Some(_)) => "dashBoard - " + newItem.getValue
        case (_, _) => "dashBoard"
      }
      centerPane = PageDisplayer.choosePage(pageCode)
      splitPane.items.remove(1)
      splitPane.items.add(1, centerPane)
    }
  }

  private val scrollPane: ScrollPane = new ScrollPane {
    minWidth = 200
    maxWidth = 200
    fitToWidth = true
    fitToHeight = true
    id = "page-tree"
    content = controlsView
  }

  lazy val splitPane: SplitPane = new SplitPane {
    dividerPositions = 0
    id = "page-splitpane"
    items.addAll(scrollPane, centerPane)
  }

  //
  // Layout the main stage
  //
  stage = new PrimaryStage {
    title = "ScalaFX Ensemble"
    icons += new Image("/scalafx/ensemble/images/ScalaFX-icon-64x64.png")
    scene = new Scene(1020, 700) {
      stylesheets += this.getClass.getResource("/scalafx/ensemble/css/ensemble.css").toExternalForm
      root = new BorderPane {
        top = new VBox {
          vgrow = Priority.Always
          hgrow = Priority.Always
          children = new ToolBar {
            prefHeight = 76
            maxHeight = 76
            id = "mainToolBar"
            content = List(
              new ImageView {
                image = new Image(
                  this.getClass.getResourceAsStream("/scalafx/ensemble/images/logo.png"))
                margin = Insets(0, 0, 0, 10)
              },
              new Region {
                minWidth = 300
              },
              new Button {
                minWidth = 120
                minHeight = 66
                id = "newButton"
              })
          }
        }
        center = new BorderPane {
          center = splitPane
        }
        styleClass += "application"
      }
    }
  }

  def setupUncaughtExceptionHandling(): Unit = {
    Thread.setDefaultUncaughtExceptionHandler(
      (t: Thread, e: Throwable) => {
        showException(Title, s"Unhandled exception on thread ${t.getName}.", e, stage)
      }
    )


    onFXAndWait {
      Thread.currentThread().setUncaughtExceptionHandler(
        (t: Thread, e: Throwable) => {
          showException(Title, s"Unhandled exception on thread ${t.getName}.", e, stage)
        }
      )
    }
  }
}