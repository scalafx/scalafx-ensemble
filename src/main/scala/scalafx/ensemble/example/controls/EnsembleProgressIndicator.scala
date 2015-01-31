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

import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.scene.control.ProgressIndicator
import scalafx.scene.layout.GridPane

class EnsembleProgressIndicator extends EnsembleExample {

  def getContent = {
    // Progress Indicators 1, 2, 3, 4
    val p1 = new ProgressIndicator {
      prefWidth = 50
      prefHeight = 50
    }
    val p2 = new ProgressIndicator {
      prefWidth = 50
      prefHeight = 50
      progress = 0.25F
    }
    val p3 = new ProgressIndicator {
      prefWidth = 50
      prefHeight = 50
      progress = 0.50F
    }
    val p4 = new ProgressIndicator {
      prefWidth = 50
      prefHeight = 50
      progress = 1.0F
    }
    //Add all progress indicators in grid pane
    new GridPane() {
      hgap = 20
      vgap = 20
      padding = Insets(20)
      add(p1, 1, 0)
      add(p2, 0, 1)
      add(p3, 1, 1)
      add(p4, 2, 1)
    }
  }
}