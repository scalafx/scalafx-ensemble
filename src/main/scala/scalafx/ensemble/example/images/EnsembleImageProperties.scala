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

package scalafx.ensemble.example.images

import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.{Insets, Rectangle2D}
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.layout.{HBox, Priority, VBox}

/** A sample that demonstrates how to resize images and use the Viewport property.
  *
  * @related images/ImageCreation
  * @see scalafx.scene.image.Image
  * @see scalafx.scene.image.ImageView
  * @resource /scalafx/ensemble/images/sanfran.jpg
  */
class EnsembleImageProperties extends EnsembleExample {

  def getContent = {
    // We can set image properties directly during creation
    val url = this.getClass.getResource("/scalafx/ensemble/images/sanfran.jpg").toExternalForm
    val sample1 = new ImageView(
      new Image(url, requestedWidth = 30, requestedHeight = 70, preserveRatio = false, smooth = true))

    val sample2 = new ImageView(new Image(url)) {
      // Image can be resized to preferred width
      fitWidth = 200
      preserveRatio = true
    }

    val sample3 = new ImageView(new Image(url)) {
      //image can be resized to preferred height
      fitHeight = 20
      preserveRatio = true
    }

    val sample4 = new ImageView(new Image(url)) {
      // One can resize image without preserving ratio between height and width
      fitWidth = 40
      fitHeight = 80
      preserveRatio = false
      // The usage of the better filter
      smooth = true
    }

    val sample5 = new ImageView(new Image(url)) {
      fitHeight = 60
      preserveRatio = true
      // Viewport is used for displaying the part of image
      viewport = new Rectangle2D(50, 200, width = 120, height = 60)
    }

    new VBox {
      vgrow = Priority.Always
      hgrow = Priority.Always
      spacing = 10
      padding = Insets(20)
      children = List(
        new HBox {
          spacing = 5
          children = List(sample1, sample3, sample4, sample5)
        },
        sample2
      )
    }
  }
}