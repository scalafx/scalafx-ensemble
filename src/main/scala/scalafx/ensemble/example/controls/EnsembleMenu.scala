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
import scalafx.scene.control.{CheckMenuItem, Menu, MenuBar, MenuItem}
import scalafx.scene.image.Image
import scalafx.scene.image.ImageView
import scalafx.scene.layout.BorderPane


/** An example of a menu bar. Includes illustration of a check menu item.
  *
  * @see scalafx.scene.control.MenuBar
  * @see scalafx.scene.control.Menu
  * @see scalafx.scene.control.MenuItem
  * @resource /scalafx/ensemble/images/crumb-selected-focused.png
  */
class EnsembleMenu extends EnsembleExample {

  // @stage-property width = 400
  // @stage-property height = 200

  val fooMenuItem = new MenuItem("foo")

  def getContent = new BorderPane {
    top = new MenuBar {
      maxWidth = 400
      useSystemMenuBar = true
      menus = List(
        new Menu("Scala") {
          items = List(
            new Menu("Author Info") {
              graphic = new ImageView {
                image = new Image(this.getClass.getResourceAsStream("/scalafx/ensemble/images/crumb-selected-focused.png"))
                margin = Insets(0, 0, 0, 5)
              }
              items = List(
                new MenuItem("Type Safe"),
                new MenuItem("Martin Odersky")
              )
            },
            new Menu("Features") {
              items = List(
                new MenuItem("Object Oriented"),
                new MenuItem("Functional"),
                fooMenuItem,
                new CheckMenuItem( """Show "foo" item""") {
                  selected = true
                  selected.onInvalidate {
                    fooMenuItem.setVisible(selected())
                    println( """Menu item "foo" is now """ + (if (fooMenuItem.visible()) "" else "not") + " visible")
                  }
                }
              )
            },
            new MenuItem("ScalaFX")
          )
        })
    }
  }
}