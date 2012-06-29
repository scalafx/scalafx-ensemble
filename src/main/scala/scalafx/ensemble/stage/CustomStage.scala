package scalafx.ensemble.stage

import scalafx.scene.Scene
import scalafx.scene.layout.BorderPane
import scalafx.scene.layout.VBox
import scalafx.scene.control.ToolBar
import scalafx.scene.image.ImageView
import scalafx.scene.image.Image
import scalafx.geometry.Insets
import scalafx.scene.layout.Region
import scalafx.scene.control.Button
import scalafx.scene.control.SplitPane
import scalafx.scene.text.Text
import scalafx.scene.control.Label
import scalafx.scene.layout.HBox
import scalafx.scene.control.Accordion
import scalafx.scene.control.TitledPane
import scalafx.scene.shape.Arc
import scalafx.scene.paint.Color

class CustomStage {
	def getStage = {
		new BorderPane {
			center = new HBox {
				content = List(new Accordion {
					vgrow = javafx.scene.layout.Priority.ALWAYS
					hgrow = javafx.scene.layout.Priority.ALWAYS
					panes = List(
						new TitledPane {
							expanded = true
							content = new HBox {
								content = List(
									new Label {
										text = "Accordion"
									},
									new Label {
										text = "Accordion"
									}
									)
							}
						},
						new TitledPane {
							expanded = true
							content = new HBox {
								content = List(
									new Label {
										text = "Accordion"
									},
									new Label {
										text = "Accordion"
									}
									)
							}
						})
				})
				styleClass = List("right-sidebar")
			}
		}
	}
}