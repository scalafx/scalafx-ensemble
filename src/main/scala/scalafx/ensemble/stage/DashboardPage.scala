/*
 * Copyright (c) 2012-2021, ScalaFX Project
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

package scalafx.ensemble.stage

import scalafx.ensemble.EnsembleTree
import scalafx.ensemble.commons.DisplayablePage
import scalafx.scene.control.ScrollPane
import scalafx.scene.layout.{Priority, VBox}

/** Dashboard Page */
class DashboardPage(dashPart: String = "dashboard") extends DisplayablePage {

  private val tree = EnsembleTree.create()

  def getPage: ScrollPane = {
    val thumbs = dashPart match {
      case "dashboard" => tree.getDashThumbsCtrl
      case _           => tree.getDashThumb(dashPart)
    }

    new ScrollPane {
      vgrow = Priority.Always
      hgrow = Priority.Always
      fitToHeight = true
      fitToWidth = true
      content = new VBox {
        vgrow = Priority.Always
        hgrow = Priority.Always
        children = thumbs
      }
      styleClass += "category-page"
    }
  }
}
