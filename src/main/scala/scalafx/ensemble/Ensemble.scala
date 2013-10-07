/*
 * Copyright (c) 2013, ScalaFX Ensemble Project
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

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.ensemble.commons.PageDisplayer
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.control._
import scalafx.scene.image.Image
import scalafx.scene.image.ImageView
import scalafx.scene.layout.BorderPane
import scalafx.scene.layout.Priority
import scalafx.scene.layout.Region
import scalafx.scene.layout.VBox
import scalafx.stage.Screen

object Ensemble extends JFXApp {
  var centerStage = PageDisplayer.choosePage("dashBoard")
  val rootTreeItem = new TreeItem[String]("ScalaFX Ensemble") {
    expanded = true
  }

  val sfxControl = EnsembleTree.create().getTree.reverse
  sfxControl.foreach(x => {
    rootTreeItem.getChildren.add(x)
  })

  val screen = Screen.primary
  val controlsView = new TreeView[String]() {
    minWidth = 200
    maxWidth = 200
    editable = true
    root = rootTreeItem
    id = "page-tree"
  }
  controlsView.selectionModel().selectionMode = SelectionMode.SINGLE
  controlsView.selectionModel().selectedItem.onChange {
    (_, _, selItem) => {
      val str = (selItem.isLeaf, Option(selItem.getParent)) match {
        case (true, Some(parent)) => parent.getValue.toLowerCase + " > " + selItem.getValue
        case (false, Some(_))     => "dashBoard - " + selItem.getValue
        case (_, _)               => "dashBoard"
      }
      centerStage = PageDisplayer.choosePage(str)
      pageViewHolder.items.remove(1)
      pageViewHolder.items.add(1, centerStage)
    }
  }

  val scrollPane = new ScrollPane {
    minWidth = 200
    maxWidth = 200
    fitToWidth = true
    fitToHeight = true
    id = "page-tree"
    content = controlsView
  }
  val pageViewHolder = new SplitPane {
    dividerPositions = 0
    id = "page-splitpane"
    items.addAll(scrollPane, centerStage)
  }

  stage = new PrimaryStage {
    scene = new Scene(1020, 700) {
      root = new BorderPane {
        top = new VBox {
          vgrow = Priority.ALWAYS
          hgrow = Priority.ALWAYS
          content = List(new ToolBar {
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
          })
        }
        center = new BorderPane {
          center = pageViewHolder
        }
        styleClass.add("application")
      }
    }
    scene.get.getStylesheets.add(
      this.getClass.getResource("/scalafx/ensemble/css/ensemble.css").toExternalForm)
  }
  stage.title = "ScalaFX Ensemble"
}