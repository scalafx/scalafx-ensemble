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

package scalafx.ensemble.example.layout

import scalafx.Includes._
import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.{HPos, Insets, Pos}
import scalafx.scene.control.{Button, Label, Separator, TextField}
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.layout.{ColumnConstraints, GridPane, Priority, RowConstraints, VBox}

/**
 * An example of a GridPane layout. There is more than one approach to using a
 * GridPane. First, the code can specify which rows and/or columns should
 * contain the content. Second, the code can alter the constraints of the
 * rows and/or columns themselves, either by specifying the preferred minimum
 * or  maximum heights or widths, or by specifying the percentage of the
 * GridPane that belongs to certain rows or columns.
 *
 * @see scalafx.scene.layout.GridPane
 * @resource /scalafx/ensemble/images/icon-48x48.png
 */
class EnsembleGridPane extends EnsembleExample {

  def getContent = {
    // grid1 places the children by specifying the rows and columns in GridPane.setConstraints()
    val grid1Caption = new Label {
      text = "The example below shows GridPane content placement by specifying rows and columns:"
      wrapText = true
    }

    val label1 = new Label("Name:") {
      style = "-fx-font-weight:bold"
      alignmentInParent = Pos.BaselineRight
    }
    GridPane.setConstraints(label1, 0, 0, 1, 1)

    val label11 = new Label("John Q. Public") {
      alignmentInParent = Pos.BaselineLeft
    }
    GridPane.setConstraints(label11, 1, 0, 2, 1)

    val label21 = new Label("Address:") {
      style = "-fx-font-weight:bold"
      alignmentInParent = Pos.BaselineRight
    }
    GridPane.setConstraints(label21, 0, 1, 1, 1)

    val label22 = new Label("12345 Main Street, Some City, CA") {
      alignmentInParent = Pos.BaselineLeft
    }
    GridPane.setConstraints(label22, 1, 1, 5, 1)

    val grid1 = new GridPane {
      hgap = 4
      vgap = 6
      margin = Insets(18)
      children ++= Seq(label1, label11, label21, label22)
    }

    // grid2 places the child by influencing the rows and columns themselves
    // via GridRowInfo and GridColumnInfo. This grid uses the preferred
    // width/height and max/min width/height.
    val grid2Caption = new Label {
      text = "The example below shows GridPane content placement by influencing the rows and columns themselves."
      wrapText = true
    }

    val category = new Label {
      text = "Category:"
      style = "-fx-font-weight:bold"
      alignmentInParent = Pos.BaselineRight
    }
    GridPane.setConstraints(category, 0, 0)

    val categoryValue = new Label {
      text = "Wines"
      alignmentInParent = Pos.BaselineLeft
    }
    GridPane.setConstraints(categoryValue, 1, 0)

    val company = new Label {
      text = "Company:"
      style = "-fx-font-weight:bold"
      alignmentInParent = Pos.BaselineRight
    }
    GridPane.setConstraints(company, 0, 1)

    val companyValue = new Label {
      text = "Acme Winery"
      alignmentInParent = Pos.BaselineLeft
    }
    GridPane.setConstraints(companyValue, 1, 1)

    val rating = new Label {
      text = "Rating:"
      style = "-fx-font-weight:bold"
      alignmentInParent = Pos.BaselineRight
    }
    GridPane.setConstraints(rating, 0, 2)

    val ratingValue = new Label {
      text = "Excellent"
      alignmentInParent = Pos.BaselineLeft
    }
    GridPane.setConstraints(ratingValue, 1, 2)

    val imageView = new ImageView {
      image = new Image(this.getClass.getResourceAsStream("/scalafx/ensemble/images/icon-48x48.png"))
      alignmentInParent = Pos.Center
    }
    GridPane.setConstraints(imageView, 2, 1)

    val rowInfo = new RowConstraints(minHeight = 50, prefHeight = 50, maxHeight = 50)
    val colInfo = new ColumnConstraints(minWidth = 140, prefWidth = 140, maxWidth = 140)

    val grid2 = new GridPane {
      padding = Insets(18)
      for (i <- 0 until 2) {
        rowConstraints.add(rowInfo)
        columnConstraints.add(colInfo)
      }
      children ++= Seq(category, categoryValue, company, companyValue, imageView, rating, ratingValue)
    }

    // grid3 places the child by influencing the rows and columns
    // via GridRowInfo and GridColumnInfo. This grid uses the percentages
    val grid3Caption = new Label {
      text = "The example below shows GridPane content placement by " +
        "influencing row and column percentages.  " +
        "Also, grid lines are made visible in this example.  " +
        "The lines can be helpful in debugging."
      wrapText = true
    }

    val grid3 = new GridPane {
      padding = Insets(18)
      gridLinesVisible = true
      children ++= Seq(grid3Caption)
      val rowConstr50Perc = new RowConstraints {percentHeight = 50}
      val colConstr25Perc = new ColumnConstraints {percentWidth = 25}
      val colConstr50Perc = new ColumnConstraints {percentWidth = 50}
      // 2*50 percent
      rowConstraints ++= Seq(rowConstr50Perc, rowConstr50Perc)
      // 25 percent
      columnConstraints += colConstr25Perc
      // 50 percent
      columnConstraints += colConstr50Perc
      // 25 percent
      columnConstraints += colConstr25Perc
    }

    val condLabel = new Label(" Member Name:")
    GridPane.setHalignment(condLabel, HPos.Right)
    GridPane.setConstraints(condLabel, 0, 0)
    val condValue = new Label("MyName")
    GridPane.setMargin(condValue, Insets(0, 0, 0, 10))
    GridPane.setConstraints(condValue, 1, 0)

    val acctLabel = new Label("Member Number:")
    GridPane.setHalignment(acctLabel, HPos.Right)
    GridPane.setConstraints(acctLabel, 0, 1)
    val textBox = new TextField {text = "Your number"}
    GridPane.setMargin(textBox, Insets(10, 10, 10, 10))
    GridPane.setConstraints(textBox, 1, 1)

    val button = new Button("Help")
    GridPane.setConstraints(button, 2, 1)
    GridPane.setMargin(button, Insets(10, 10, 10, 10))
    GridPane.setHalignment(button, HPos.Center)

    GridPane.setConstraints(condValue, 1, 0)
    grid3.children ++= Seq(condLabel, condValue, button, acctLabel, textBox)

    new VBox {
      vgrow = Priority.Always
      hgrow = Priority.Always
      spacing = 10
      padding = Insets(20)
      children = List(
        new VBox {children = List(grid1Caption, grid1)},
        new Separator(),
        new VBox {children = List(grid2Caption, grid2)},
        new Separator(),
        new VBox {children = List(grid3Caption, grid3)}
      )
    }
  }
}