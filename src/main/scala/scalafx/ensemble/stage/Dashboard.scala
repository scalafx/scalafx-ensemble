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
import scalafx.scene.control.ScrollPane
import scalafx.scene.layout.TilePane
import scalafx.scene.text.Font
import javafx.geometry.Orientation
import javafx.geometry.Pos
import scalafx.scene.layout.FlowPane
import javafx.scene.text.TextAlignment

class Dashboard {
	def getPage = {
		new VBox {
			vgrow = javafx.scene.layout.Priority.ALWAYS
			hgrow = javafx.scene.layout.Priority.ALWAYS
			style = "-fx-padding: 8px"
			content = List(
				new Text {
					text = "Samples"
					font = new Font("Sans-serif", 30)
					style = "-fx-font-weight: bold;"
				},
				new Text {
					text = "Controls"
					font = new Font("Sans-serif", 20)
				}, new FlowPane {
					hgap = 4
					vgap = 4
					padding = Insets(5, 0, 5, 0)
					prefWrapLength = 400
					content = List(
						new VBox {
							content = List(new ImageView {
								image = new Image(this.getClass.getResourceAsStream("../images/CalendarTextFieldSample.png"))
							}, new Label {
								text = "TextField"
							})
						}, new VBox {
							content = List(new ImageView {
								image = new Image(this.getClass.getResourceAsStream("../images/CalendarTextFieldSample.png"))
							}, new Label {
								text = "Password"
							})
						},
						new VBox {
							alignment = Pos.CENTER
							content = List(new ImageView {
								image = new Image(this.getClass.getResourceAsStream("../images/CalendarTextFieldSample.png"))
							}, new Label {
								text = "TextField"
							})
						})
				})
		}
	}
}
