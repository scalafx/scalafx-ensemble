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

package scalafx.ensemble

import java.io.File
import scalafx.Includes._
import scalafx.scene.control.Label
import scalafx.scene.control.TreeItem
import scalafx.scene.image.Image
import scalafx.scene.image.ImageView
import scalafx.ensemble.commons.SortUtils
import scalafx.scene.Node
import scalafx.scene.text.Text
import scalafx.scene.layout.{TilePane, VBox}
import scalafx.geometry.{Orientation, Insets, Pos}
import scala.collection.immutable.TreeMap
import scalafx.scene.input.MouseEvent
import scalafx.ensemble.commons.PageDisplayer

/**
 * Object to load examples as Map which in turn is used
 * to create TreeItem in the UI
 */
object EnsembleTree {

  val fil = new File(getClass.getResource("/scalafx/ensemble/example/").getPath)

  def create() = {
    new EnsembleTree(createTree(), createThumbnails())
  }

  /**
   * build a map by iterating through the examples folder.
   * This is used in UI
   */
  private def createTree() = {
    var egPlesTree = TreeMap[String, List[TreeItem[String]]]()
    fil.listFiles().foreach(x => {
      if (x.isDirectory) {
        var leaves = List[TreeItem[String]]()
        x.listFiles().foreach(a => {
          if (a.getName.contains(".scala")) {
            val leafName = a.getName.stripSuffix(".scala").stripPrefix("Ensemble")
            leaves = leaves.::(new TreeItem(leafName))
          }
        })
        egPlesTree = egPlesTree.+((x.getName.capitalize,
          leaves.sortWith(SortUtils.treeItemSort)))
      }
    })
    egPlesTree
  }

  private def createThumbnails() = {
    var thumbnails = TreeMap[String, List[EnsembleThumbNail]]()
    fil.listFiles().foreach(x => {
      if (x.isDirectory) {
        val ctrlgpName = x.getName
        var thumbs = List[EnsembleThumbNail]()
        x.listFiles().foreach(a => {
          if (a.getName.contains(".scala")) {
            val leafName = a.getName.stripSuffix(".scala").stripPrefix("Ensemble")
            val img = new ImageView {
              onMouseClicked = (p1: MouseEvent) => {
                    Ensemble.pageViewHolder.items.remove(1)
                    Ensemble.pageViewHolder.items.add(1,
                      PageDisplayer.choosePage(ctrlgpName + " > " + leafName))
              }
              val filePath = "/scalafx/ensemble/example/" + ctrlgpName + "/" + leafName + "Sample.png"
              image = new Image(this.getClass.getResourceAsStream(filePath))
            }
            val lbl = new Label(leafName)
            thumbs = thumbs.::(EnsembleThumbNail(img, lbl))
          }
        })
        thumbnails = thumbnails.+((x.getName.capitalize,
          thumbs.sortWith(SortUtils.thumbNailsSort)))
      }
    })
    thumbnails
  }
}

case class EnsembleThumbNail(imgView: ImageView, caption: Label)

/**
 * The class provide accessibility methods to access the
 * underlying map
 */
class EnsembleTree(map: Map[String, List[TreeItem[String]]],
                   thumbnails: Map[String, List[EnsembleThumbNail]]) {

  def getLeaves(keyName: String) = map get keyName get

  /**
   * returns the entire tree
   */
  def getTree = {
    var treeSibls = List[TreeItem[String]]()
    map.foreach(x => {
      val sibl = new TreeItem[String](x._1)
      sibl.expanded = true
      x._2.foreach(y => {
        sibl.getChildren().add(y)
      })
      treeSibls = treeSibls.::(sibl)
    })
    treeSibls
  }

  def getThumbs(keyName: String) = thumbnails get keyName get

  import scalafx.ensemble.Converter._

  def getDashThumbsCtrl() = {
    var thums = List[Node]()
    thumbnails.foreach(x => {
      thums = thums.::(x._1)
      thums = thums.::(x._2)
    })
    thums.reverse
  }

  def getDashThumb(ctrlGrpName: String) = {
    var thums = List[Node]()
    thums = thums.::(thumbnails.get(ctrlGrpName).get)
    thums = thums.::(ctrlGrpName)
    thums
  }
}

object Converter {
  implicit def convertToText(value: String): Node = {
    new Text {
      text = value
      styleClass.add("category-header")
    }
  }

  implicit def convertToThumbBoxes(value: List[EnsembleThumbNail]): Node = {
    val fp = new TilePane {
      prefColumns = 1
      hgap = 4
      vgap = 4
      padding = Insets(10, 10, 10, 10)
      orientation = Orientation.HORIZONTAL
      styleClass.add("category-page-flow")
    }
    value.foreach(y => {
      val x = new VBox {
        styleClass.add("sample-tile")
        alignmentInParent = Pos.CENTER
        content = List(y.imgView, y.caption)
      }
      fp.content.add(x)
    })
    fp
  }
}
