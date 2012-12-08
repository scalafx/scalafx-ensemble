package scalafx.ensemble.example.layout

import javafx.geometry.HPos
import javafx.geometry.VPos
import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.scene.control.Label
import scalafx.scene.control.Label.sfxLabel2jfx
import scalafx.scene.control.Separator
import scalafx.scene.image.Image
import scalafx.scene.image.Image.sfxImage2jfx
import scalafx.scene.image.ImageView
import scalafx.scene.image.ImageView.sfxImageView2jfx
import scalafx.scene.layout.ColumnConstraints
import scalafx.scene.layout.ColumnConstraints.sfxColumnConstraints2jfx
import scalafx.scene.layout.GridPane
import scalafx.scene.layout.RowConstraints
import scalafx.scene.layout.RowConstraints.sfxRowConstraints2jfx
import scalafx.scene.layout.VBox
import scalafx.scene.text.Font
import scalafx.scene.text.Font.sfxFont2jfx

class EnsembleGridPane extends EnsembleExample {
  def getContent = {
    //grid1 places the children by specifying the rows and columns in GridPane.setContraints()
    val grid1Caption = new Label {
      text = "The example below shows GridPane content placement by specifying rows and columns:"
      wrapText = true
    }

    val label1 = new Label { text = "Name:" }
    GridPane.setConstraints(label1, 0, 0, 1, 1, HPos.RIGHT, VPos.TOP)

    val label11 = new Label { text = "John Q. Public" }
    GridPane.setConstraints(label11, 1, 0, 2, 1, HPos.LEFT, VPos.TOP)

    val label21 = new Label { text = "Address" }
    GridPane.setConstraints(label21, 0, 1, 1, 1, HPos.RIGHT, VPos.TOP)

    val label22 = new Label { text = "12345 Main Street, Some City, CA" }
    GridPane.setConstraints(label22, 1, 1, 5, 1, HPos.LEFT, VPos.TOP)

    val grid1 = new GridPane {
      hgap = 4
      vgap = 6
      margin = Insets(18, 18, 18, 18)
      children.addAll(label1, label11, label21, label22)
    }

    //grid2 places the child by influencing the rows and columns themselves via GridRowInfo and GridColumnInfo. This grid uses the preferred width/height and max/min width/height.
    val grid2Caption = new Label {
      text = "The example below shows GridPane content placement by influencing the rows and columns themselves."
      wrapText = true
    }

    val category = new Label {
      text = "Category:"
    }
    GridPane.setConstraints(category, 0, 0)

    val categoryValue = new Label {
      text = "Wines"
    }
    GridPane.setConstraints(categoryValue, 1, 0)

    val company = new Label {
      text = "Company:"
    }
    GridPane.setConstraints(company, 0, 1)

    val companyValue = new Label {
      text = "Acme Winery"
    }
    GridPane.setConstraints(companyValue, 1, 1)

    val rating = new Label {
      text = "Rating:"
    }
    GridPane.setConstraints(rating, 0, 2)

    val ratingValue = new Label {
      text = "Excellent"
    }
    GridPane.setConstraints(ratingValue, 1, 2)

    val imageView = new ImageView {
      image = new Image(this.getClass.getResourceAsStream("/scalafx/ensemble/images/icon-48x48.png"))
    }
    GridPane.setConstraints(imageView, 2, 1)

    val rowInfo = new RowConstraints(50, 50, 50)
    val colInfo = new ColumnConstraints(140, 140, 140)
    
    val grid2 = new GridPane {
      padding = Insets(18, 18, 18, 18)
      gridLinesVisible = true
      for (i <- 0 until 2) {
        rowConstraints.add(rowInfo)
        columnConstraints.add(colInfo)
      }
      children.addAll(category, categoryValue, company, companyValue, imageView, rating, ratingValue)
    }

    new VBox {
      vgrow = javafx.scene.layout.Priority.ALWAYS
      hgrow = javafx.scene.layout.Priority.ALWAYS
      spacing = 10
      margin = Insets(50, 0, 0, 50)
      content = List(
        new Label {
          text = "Ensemble Grid Pane"
          font = new Font("Verdana", 20)
        },
        new VBox {
          content = List(grid1Caption, grid1, new Separator {
            maxWidth = 600
          })
        },
        new VBox {
          content = List(grid2Caption, grid2, new Separator {
            maxWidth = 600
          })
        })
    }
  }
}