package scalafx.ensemble

import java.io.File
import scalafx.scene.control.TreeItem

/**
 * Object to load examples as Map which in turn is used 
 * to create TreeItem in the UI
 */
object EnsembleControlsTree {

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
    new EnsembleControlsTree(egPlesTree)
  }

}

/**
 * The class provide accessibility methods to access the 
 * underlying map
 */
class EnsembleControlsTree(map: Map[String, List[TreeItem[String]]]) {

  def getLeaves(keyName: String) = {
    map.get(keyName).get
  }

  /**
   *returns the entire tree  
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

