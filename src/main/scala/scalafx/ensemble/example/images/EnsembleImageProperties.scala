package scalafx.ensemble.example.images

import scalafx.Includes._
import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.geometry.Rectangle2D
//import scalafx.geometry.Rectangle2D.sfxRectangle2D2jfx
import scalafx.scene.control.Label
import scalafx.scene.image.Image
import scalafx.scene.image.ImageView
//import scalafx.scene.image.ImageView.sfxImageView2jfx
import scalafx.scene.layout.HBox
import scalafx.scene.layout.VBox
import scalafx.scene.text.Font

class EnsembleImageProperties extends EnsembleExample {
  def getContent = {
    //we can set image properties directly during creation
    val url = this.getClass.getResource("/scalafx/ensemble/images/sanfran.jpg").toExternalForm()
    val sample1 = new ImageView(new Image(url, 30, 70, false, true))

    val sample2 = new ImageView(new Image(url))
    //image can be resized to preferred width
    sample2.setFitWidth(200)
    sample2.setPreserveRatio(true)

    val sample3 = new ImageView(new Image(url))
    //image can be resized to preferred height
    sample3.setFitHeight(20);
    sample3.setPreserveRatio(true);

    val sample4 = new ImageView(new Image(url))
    //one can resize image without preserving ratio between height and width
    sample4.setFitWidth(40)
    sample4.setFitHeight(80)
    sample4.setPreserveRatio(false)
    sample4.setSmooth(true) //the usage of the better filter

    val sample5 = new ImageView(new Image(url))
    sample5.setFitHeight(60)
    sample5.setPreserveRatio(true)
    //viewport is used for displaying the part of image
    val rectangle2D = new Rectangle2D(50, 200, 120, 60);
    sample5.setViewport(rectangle2D)

    new VBox {
      vgrow = javafx.scene.layout.Priority.ALWAYS
      hgrow = javafx.scene.layout.Priority.ALWAYS
      spacing = 10
      margin = Insets(50, 0, 0, 50)
      content = List(
        new Label {
          text = "Ensemble Image Properties"
          font = new Font("Verdana", 20)
        },
        new HBox {
          spacing = 5
          content = List(sample1, sample3, sample4, sample5)
        },
        sample2)
    }
  }
}