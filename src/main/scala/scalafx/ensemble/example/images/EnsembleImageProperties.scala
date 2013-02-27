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

package scalafx.ensemble.example.images

import scalafx.Includes._
import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.geometry.Rectangle2D
import scalafx.scene.control.Label
import scalafx.scene.image.Image
import scalafx.scene.image.ImageView
import scalafx.scene.layout.HBox
import scalafx.scene.layout.VBox
import scalafx.scene.text.Font
import scalafx.scene.layout.Priority

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
      vgrow = Priority.ALWAYS
      hgrow = Priority.ALWAYS
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