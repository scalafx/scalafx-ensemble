package scalafx.ensemble.example.controls

import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.scene.Node
import scalafx.scene.control.Button
import scalafx.scene.control.Label
import scalafx.scene.control.Pagination
import scalafx.scene.image.Image
import scalafx.scene.image.ImageView
import scalafx.scene.layout.VBox
import scalafx.scene.text.Font
import scalafx.scene.text.Text
import scalafx.scene.control.Control

class EnsemblePagination extends EnsembleExample {
  def getContent = {
    //Images to load pages
    val images = new Array[Image](7)
    for (i <- 0 until 7) {
      images(i) = new Image(this.getClass.getResourceAsStream("/scalafx/ensemble/images/animals-200x200/animal" + (i + 1) + ".jpg"))
    }
    //Pagination with 7 pages and index starts at zero
    val pagination = new Pagination(7, 0)
    val indexToNode = (index: Int) => {
      val vbox = new VBox() {
        content = List(new ImageView(images(index)),new Label("PAGE " + (index + 1)))
      }.asInstanceOf[Node]
      vbox
    }
    
    //    pagination.pageFactory = indexToNode
    /*pagination.setPageFactory(new Callback[java.lang.Integer, javafx.scene.Node]() {
      override def call(index: java.lang.Integer) {
        new VBox() {
          content = List(
            new ImageView(images(index)),
            new Label("PAGE " + (index + 1)))
          println("Inside Pagination " + index)
        }.asInstanceOf[javafx.scene.Node]
      }
    })
*/
    
    //TODO pagination incomplete
    new VBox {
      vgrow = javafx.scene.layout.Priority.ALWAYS
      hgrow = javafx.scene.layout.Priority.ALWAYS
      spacing = 10
      margin = Insets(50, 0, 0, 50)
      content = List(
        new Text {
          text = "Ensemble Pagination"
          font = new Font("Verdana", 20)
        },
        pagination,
        new Button {
          maxWidth = Control.USE_PREF_SIZE
          maxHeight = Control.USE_PREF_SIZE
          text = "Toggle Pagination Button"
        })
    }
  }
}