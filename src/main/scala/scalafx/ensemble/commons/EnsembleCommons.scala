package scalafx.ensemble.commons

import scalafx.scene.Node

trait EnsembleExample {
  def getContent:Node
}

object ContentFactory {
  def createContent(ctrlName: String) = {
    val qualCtrl = "scalafx.ensemble.example.Ensemble" + ctrlName
    var cache = Map[String, EnsembleExample]()
    if (cache.get(qualCtrl).isDefined) {
      cache.get(qualCtrl).get.getContent
    } else {
      val inst = Class.forName(qualCtrl).newInstance().asInstanceOf[EnsembleExample]
      cache = cache.+((qualCtrl, inst))
      println(cache)
      inst.getContent
    }
  }
}