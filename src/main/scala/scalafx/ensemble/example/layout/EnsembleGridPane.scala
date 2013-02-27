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

package scalafx.ensemble.example.layout

import scalafx.Includes._
import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.HPos
import scalafx.geometry.Insets
import scalafx.geometry.VPos
import scalafx.scene.control.Label
import scalafx.scene.control.Separator
import scalafx.scene.image.Image
import scalafx.scene.image.ImageView
import scalafx.scene.layout.ColumnConstraints
import scalafx.scene.layout.GridPane
import scalafx.scene.layout.RowConstraints
import scalafx.scene.layout.VBox
import scalafx.scene.text.Font
import scalafx.scene.layout.Priority

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
      vgrow = Priority.ALWAYS
      hgrow = Priority.ALWAYS
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