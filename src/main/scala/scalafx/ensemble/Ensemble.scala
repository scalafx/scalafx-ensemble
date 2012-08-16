/*
 * Copyright (c) 2012, ScalaFX Ensemble Project
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

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.layout.Priority
import scalafx.application.JFXApp
import scalafx.ensemble.stage.Page
import scalafx.geometry.Insets
import scalafx.scene.Scene._
import scalafx.scene.Scene
import scalafx.scene.control.Accordion._
import scalafx.scene.control.Button
import scalafx.scene.control.Labeled._
import scalafx.scene.control.SplitPane
import scalafx.scene.control.Tab
import scalafx.scene.control.TabPane
import scalafx.scene.control.TextField
import scalafx.scene.control.TitledPane._
import scalafx.scene.control.ToolBar
import scalafx.scene.control.TreeItem
import scalafx.scene.control.TreeView
import scalafx.scene.image.Image
import scalafx.scene.image.ImageView
import scalafx.scene.layout.BorderPane
import scalafx.scene.layout.Pane._
import scalafx.scene.layout.Region
import scalafx.scene.layout.VBox
import scalafx.scene.shape.Circle._
import scalafx.stage.Stage._
import scalafx.stage.Stage
import javafx.scene.input.MouseEvent
import scalafx.event.EventType

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

	val cflCrls = new TreeItem[String]("Colorful Circles")
	// TODO should respond to a mouse clicked event and show up
	// something.
	/// cflCrls.addEventHandler(MouseEvent.MouseClicked, null)

	val rootTreeItem = new TreeItem[String]("ScalaFX Ensemble") {
		expanded = true
	}

	val sfxControl = new TreeItem[String]("Controls")

	val controls = List(new TreeItem[String]("TextField"), new TreeItem[String]("Password"))
	controls.foreach((control) => {
		sfxControl.getChildren.add(control)
		control.addEventHandler(new EventType[MouseEvent](), new EventHandler[MouseEvent] {
			def handle(event: MouseEvent) {
				println("mouse clicked -> " + event.getSource.toString)
			}
		})
	})

	rootTreeItem.getChildren.addAll(sfxControl)

	val controlsView = new TreeView[String] {
		hgrow = javafx.scene.layout.Priority.ALWAYS
		minWidth = 200
		root = rootTreeItem
		id = "page-tree"
	}

//	controlsView.onMouseClicked = new EventHandler[MouseEvent] {
//		def handle(event: MouseEvent) {
//			println("mouse clicked --> " + event.getSource)
//		}
//	}

	val centerStage = new Page().getPage

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
					})
				}
				center = new BorderPane {
					minHeight = 768
					center = new SplitPane {
						maxWidth = java.lang.Double.MAX_VALUE
						maxHeight = java.lang.Double.MAX_VALUE
						dividerPositions = 0
						id = "page-splitpane"
						items.addAll(controlsView, centerStage)
					}
				}
				styleClass.add("application")
			}
		}
		scene.get.getStylesheets.add(this.getClass.getResource("ensemble.css").toExternalForm)
	}
	// stage.initStyle(StageStyle.UNDECORATED)
}
