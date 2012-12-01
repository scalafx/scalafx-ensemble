package scalafx.ensemble

import java.io.File
import scalafx.scene.control.TreeItem
import scalafx.scene.image.ImageView
import scalafx.scene.control.Label

/**
 * Object to load examples as Map which in turn is used
 * to create TreeItem in the UI
 */
object EnsembleTree {

  def createTree() = {
    val fil = new File(getClass().getResource("/ensemble/examples").getPath())
    var egPlesTree = Map[String, List[TreeItem[String]]]()
    fil.listFiles().foreach(x => {
      if (x.isDirectory()) {
        var leaves = List[TreeItem[String]]()
        x.listFiles().foreach(a => {
          val leafname = a.getName().split(".txt")
          leaves = leaves.::(new TreeItem(leafname(0)))
        })
        egPlesTree = egPlesTree.+((x.getName(), leaves))
      }
    })
    egPlesTree
  }

  def create() = {
    new EnsembleTree(createTree, createThumbnails)
  }

  def createThumbnails() = {
    var egPlesTree = Map[String, List[EnsembleThumbNail]]()
    egPlesTree
  }

}

case class EnsembleThumbNail(imgView: ImageView, caption: Label)

/**
 * The class provide accessibility methods to access the
 * underlying map
 */
class EnsembleTree(map: Map[String, List[TreeItem[String]]], thumbnails: Map[String, List[EnsembleThumbNail]]) {

  def getLeaves(keyName: String) = {
    map.get(keyName).get
  }

  /**
   * returns the entire tree
   */
  def getTree() = {
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
}

