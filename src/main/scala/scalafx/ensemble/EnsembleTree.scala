package scalafx.ensemble

import java.io.File
import scalafx.scene.control.Label
import scalafx.scene.control.TreeItem
import scalafx.scene.control.TreeItem.sfxTreeItemTojfx
import scalafx.scene.image.Image
import scalafx.scene.image.Image.sfxImage2jfx
import scalafx.scene.image.ImageView
import scalafx.ensemble.commons.SortUtils
import scalafx.scene.Node
import scalafx.scene.text.Text
import scalafx.scene.text.Font
import scalafx.scene.layout.FlowPane
import scalafx.scene.layout.VBox
import scalafx.geometry.Insets
import javafx.geometry.Pos

/**
 * Object to load examples as Map which in turn is used
 * to create TreeItem in the UI
 */
object EnsembleTree {

  val fil = new File(getClass().getResource("/ensemble/examples").getPath())

  def create() = {
    new EnsembleTree(createTree, createThumbnails)
  }

  /**
   * build a map by iterating through the examples folder.
   * This is used in UI
   */
  private def createTree() = {
    var egPlesTree = Map[String, List[TreeItem[String]]]()
    fil.listFiles().foreach(x => {
      if (x.isDirectory()) {
        var leaves = List[TreeItem[String]]()
        x.listFiles().foreach(a => {
          val leafname = a.getName().split(".txt")
          leaves = leaves.::(new TreeItem(leafname(0)))
        })
        egPlesTree = egPlesTree.+((x.getName().capitalize,
          leaves.sortWith(SortUtils.treeItemSort(_, _))))
      }
    })
    egPlesTree.toSeq.sortWith(_._1 > _._1).toMap
  }

  private def createThumbnails() = {
    var thumbnails = Map[String, List[EnsembleThumbNail]]()
    fil.listFiles().foreach(x => {
      if (x.isDirectory()) {
        var thumbs = List[EnsembleThumbNail]()
        x.listFiles().foreach(a => {
          val leafname = a.getName().split(".txt")
          val img = new ImageView()
          img.image = new Image(this.getClass.getResourceAsStream(
            "/scalafx/ensemble/images/CalendarTextFieldSample.png"))
          val lbl = new Label()
          lbl.text = leafname(0)
          thumbs = thumbs.::(EnsembleThumbNail(img, lbl))
        })
        thumbnails = thumbnails.+((x.getName().capitalize,
          thumbs.sortWith(SortUtils.thumbNailsSort(_, _))))
      }
    })
    thumbnails.toSeq.sortWith(_._1 > _._1).toMap
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

  def getDashThumbsCtrl() = {
    var thums = List[Node]()
    import scalafx.ensemble.Converter._
    thumbnails.foreach(x => {
      // could be thums = thums.::(x._1).::(x._2) but cant use it 
      // due to implicits 
      thums = thums.::(x._2)
      thums = thums.::(x._1)
    })
    thums
  }
}

object Converter {
  implicit def convertToText(value: String): Node = {
    new Text {
      text = value
      font = new Font("Sans-serif", 30)
      styleClass.add("page-header")
    }
  }

  implicit def convertToThumbBoxes(value: List[EnsembleThumbNail]): Node = {
    val fp = new FlowPane {
      hgap = 4
      vgap = 4
      padding = Insets(5, 5, 5, 5)
      prefWrapLength = 400
    }
    value.foreach(y => {
      val x = new VBox {
        styleClass.add("sample-tile")
        alignment = Pos.CENTER
        content = List(y.imgView, y.caption)
      }
      fp.content.add(x)
    })
    fp
  }
}
