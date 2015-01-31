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

package scalafx.ensemble.example.controls

import scalafx.Includes._
import scalafx.ensemble.commons.EnsembleExample
import scalafx.event.ActionEvent
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.control.{Button, Label, Pagination}
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.layout.{Priority, Region, VBox}

/** A sample that demonstrates pagination
  *
  * @see scalafx.scene.control.Pagination
  * @resource /scalafx/ensemble/images/animals-200x200/animal1.jpg
  * @resource /scalafx/ensemble/images/animals-200x200/animal2.jpg
  * @resource /scalafx/ensemble/images/animals-200x200/animal3.jpg
  * @resource /scalafx/ensemble/images/animals-200x200/animal4.jpg
  * @resource /scalafx/ensemble/images/animals-200x200/animal5.jpg
  * @resource /scalafx/ensemble/images/animals-200x200/animal6.jpg
  * @resource /scalafx/ensemble/images/animals-200x200/animal7.jpg
  * @resource /scalafx/ensemble/images/animals-200x200/animal8.jpg
  */
class EnsemblePagination extends EnsembleExample {

  def getContent = {

    // Images to load pages
    val images = for (i <- 0 until 7) yield {
      val ipStream = this.getClass.getResourceAsStream("/scalafx/ensemble/images/animals-200x200/animal" + (i + 1) + ".jpg")
      new Image(ipStream)
    }

    // Factory function for creating page content
    val createAnimalPage = (index: Int) => new VBox() {
      children = List(new ImageView(images(index)), new Label("PAGE " + (index + 1)))
      alignment = Pos.Center
    }

    // Pagination with 7 pages and index starts at zero
    val pagination = new Pagination(7, 0) {
      pageFactory = createAnimalPage
    }

    new VBox {
      vgrow = Priority.Always
      hgrow = Priority.Always
      alignment = Pos.Center
      spacing = 10
      padding = Insets(20)
      children = List(
        pagination,
        new Button {
          maxWidth = Region.USE_PREF_SIZE
          maxHeight = Region.USE_PREF_SIZE
          text = "Toggle Pagination Button"
          onAction = (ae: ActionEvent) => {
            if (pagination.styleClass.contains(Pagination.STYLE_CLASS_BULLET)) {
              pagination.styleClass -= Pagination.STYLE_CLASS_BULLET
            } else {
              pagination.styleClass += Pagination.STYLE_CLASS_BULLET
            }
          }
        })
    }
  }
}