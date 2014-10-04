/*
 * Copyright (c) 2012-2014, ScalaFX Ensemble Project
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

import java.io.{File, IOException}

import scala.collection.immutable.TreeMap
import scalafx.Includes._
import scalafx.ensemble.commons.{ExampleInfo, PageDisplayer, SortUtils}
import scalafx.event.ActionEvent
import scalafx.geometry.{Insets, Orientation}
import scalafx.scene.control._
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.layout.{Region, TilePane}

/**
 * Object to load examples as Map which in turn is used
 * to create TreeItem in the UI
 */
object EnsembleTree {

  val examplePath = new File(getClass.getResource(ExampleInfo.examplesDir).getPath)

  def create(): EnsembleTree = new EnsembleTree(createTree(), createThumbnails())

  /**
   * build a map by iterating through the examples folder.
   * This is used in UI
   */
  private def createTree(): Map[String, List[TreeItem[String]]] = {
    // Sanity check, the listing mey not work when ScalaFX Ensemble is packaged into a jar.
    val exampleRootFiles = examplePath.listFiles()
    if (exampleRootFiles == null)
      throw new IOException("Cannot list files in the example directory. May be caused by Issue #10.")

    val pairs = for (dir <- exampleRootFiles if dir.isDirectory) yield {
      val leaves = for (f <- dir.listFiles() if f.getName.contains(".scala")) yield {
        val leafName = f.getName.stripSuffix(".scala").stripPrefix("Ensemble")
        new TreeItem(ExampleInfo.formatAddSpaces(leafName))
      }
      dir.getName.capitalize -> leaves.toList.sortWith(SortUtils.treeItemSort)
    }
    TreeMap(pairs: _*)
  }

  private def createThumbnails() = {
    // Sanity check, the listing mey not work when ScalaFX Ensemble is packaged into a jar.
    val exampleRootFiles = examplePath.listFiles()
    if (exampleRootFiles == null)
      throw new IOException("Cannot list files in the example directory. May be caused by Issue #10.")

    val pairs = for (dir <- examplePath.listFiles() if dir.isDirectory) yield {
      val groupName = dir.getName
      val thumbs = for (f <- dir.listFiles() if f.getName.contains(".scala")) yield {
        val leafName = f.getName.stripSuffix(".scala").stripPrefix("Ensemble")
        val sampleName = ExampleInfo.formatAddSpaces(leafName)
        val img = new ImageView {
          val filePath = ExampleInfo.thumbnailPath(leafName, groupName)
          val inputStream = this.getClass.getResourceAsStream(filePath)
          if (inputStream == null) {
            throw new IOException("Unable to locate resource: " + filePath)
        }
          image = new Image(inputStream)
        }
        val button = new Button(sampleName, img) {
          prefWidth = 140
          prefHeight = 145
          contentDisplay = ContentDisplay.Top
          styleClass.clear()
          styleClass += "sample-tile"
          onAction = (ae: ActionEvent) => {
            Ensemble.splitPane.items.remove(1)
            Ensemble.splitPane.items.add(1,
              PageDisplayer.choosePage(groupName + " > " + sampleName))
          }
        }
        EnsembleThumbNail(button)
      }
      dir.getName.capitalize -> thumbs.toList.sortWith(SortUtils.thumbNailsSort)
    }
    TreeMap(pairs: _*)
  }
}

case class EnsembleThumbNail(button: Button)

/**
 * The class provide accessibility methods to access the
 * underlying map
 */
class EnsembleTree(tree: Map[String, List[TreeItem[String]]],
                   thumbnails: Map[String, List[EnsembleThumbNail]]) {

  def getLeaves(keyName: String) = tree(keyName)

  /**
   * returns the entire tree
   */
  def getTree: List[TreeItem[String]] = tree.map {
    case (name, items) => new TreeItem[String](name) {
      expanded = true
      children = items
    }
  }.toList

  def getThumbs(keyName: String) = thumbnails(keyName)


  def getDashThumbsCtrl =
    thumbnails.flatMap {
      case (heading, ts) => Seq(createCategoryLabel(heading), createTiles(ts))
    }

  def getDashThumb(ctrlGrpName: String) =
    Seq(
      createCategoryLabel(ctrlGrpName),
      createTiles(thumbnails(ctrlGrpName))
    )

  private def createCategoryLabel(value: String) =
    new Label {
      text = value
      maxWidth = Double.MaxValue
      minHeight = Region.USE_PREF_SIZE
      styleClass += "category-header"
    }

  private def createTiles(value: List[EnsembleThumbNail]) = new TilePane {
    prefColumns = 1
    hgap = 4
    vgap = 4
    padding = Insets(10, 10, 10, 10)
    orientation = Orientation.HORIZONTAL
    styleClass += "category-page-flow"
    content = value.map(_.button)
  }
}