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
package scalafx.ensemble.commons

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.URISyntaxException

import scalafx.Includes.jfxPriority2sfx
import scalafx.ensemble.EnsembleThumbNail
import scalafx.ensemble.stage.DashboardPage
import scalafx.ensemble.stage.EnsembleTabbedPage
import scalafx.scene.Node
import scalafx.scene.Node.sfxNode2jfx
import scalafx.scene.control.Label.sfxLabel2jfx
import scalafx.scene.control.ScrollPane
import scalafx.scene.control.ScrollPane.sfxScrollPane2jfx
import scalafx.scene.control.TreeItem
import scalafx.scene.control.TreeItem.sfxTreeItemTojfx
import scalafx.scene.layout.BorderPane
import scalafx.scene.layout.BorderPane.sfxBorderPane2jfx
import scalafx.scene.layout.Priority
import scalafx.scene.layout.VBox
import scalafx.scene.web.WebView

/**
 * the class that updates tabbed view or dashboard view
 * based on the TreeItem selected from left pane
 */
object PageDisplayer {

  def choosePage(value: String = "dashBoard"): Node = {
    value match {
      case "dashBoard" => {
        displayPage(new DashboardPage)
      }
      case _ => {
        if (value.startsWith("dashBoard - ")) {
          displayPage(new DashboardPage((value.split("-")(1)).trim()))
        } else {
          displayPage(EnsembleTabbedPage.buildTab(value.split(">")(1).trim(), value.split(">")(0).trim()))
        }
      }
    }
  }

  private def displayPage(nodeToAdd: DisplayablePage): Node = {
    val pageContent = new VBox {
      vgrow = Priority.ALWAYS
      hgrow = Priority.ALWAYS
      styleClass.add("category-page")
    }
    pageContent.content.removeAll()
    pageContent.content.add(nodeToAdd.getPage)
    pageContent
  }
}

/**
 * utility to sort the items
 */
object SortUtils {

  def treeItemSort = (ti: TreeItem[String], t2: TreeItem[String]) =>
    compare(ti.getValue(), t2.getValue())

  def thumbNailsSort = (t1: EnsembleThumbNail, t2: EnsembleThumbNail) =>
    compare(t1.caption.getText(), t2.caption.getText())

  def sortKeys = (x: String, y: String) => compare(x, y)

  private def compare = (x: String, y: String) =>
    x.compareToIgnoreCase(y) < 0
}

/**
 * populates the tabbed content by loading
 * EnsembleExample instance
 */
object ContentFactory {
  def createContent(ctrlName: String, ctrlgroupName: String = "") = {
    val borderPane = new BorderPane

    //Construct content of the samples dynamically
    val qualCtrl = "scalafx.ensemble.example." +
      ctrlgroupName + ".Ensemble" + ctrlName
    var cache = Map[String, EnsembleExample]()
    if (cache.get(qualCtrl).isDefined) {
      borderPane.setCenter(cache.get(qualCtrl).get.getContent)
    } else {
      val inst = Class.forName(qualCtrl).newInstance().asInstanceOf[EnsembleExample]
      cache = cache.+((qualCtrl, inst))
      borderPane.setCenter(inst.getContent)
    }
    //Scrollpane is applied for borderpane that contains samples
    val scrollPane = new ScrollPane {
      fitToWidth = true
      fitToHeight = true
      minWidth = 725
      content = borderPane
    }
    scrollPane.getStyleClass().add("noborder-scroll-pane")
    scrollPane
  }

  
  def createSrcContent(ctrlName: String, ctrlgroupName: String = "") = {
    val htmlpage = """<html><head></head><body><div style="font-size: 13px; font-family: sans-serif; white-space:pre;">"""
      
    val createDiv = (src:String) => {
      """<div style="font-size: 13px; font-family: sans-serif; color: green; white-space:pre;">""" + src + """</div>"""
    }
    
    val createOpenDiv = (src: String)=> {
      """<div style="font-size: 13px; font-family: sans-serif; color: green; white-space:pre;">""" + src + """<br/>"""
    }
    //read function to read the file content one by one 
    val readSrc = (s: String, builder: StringBuilder) => {
      s match {
        case null => false
        case _ => {
          if (s.contains("//")) {
            builder.append(createDiv(s))
          } else if (s.contains("/*")) {
            builder.append(createOpenDiv(s))
          } else if (s.contains("*/")) {
            builder.append(s);
            builder.append("</div>")
          } else {
            builder.append(s);
            builder.append("<br/>")
          }
          true
        }
      }
    }
    // File to read src file
    val file = this.getClass().getResource(
      "/ensemble/examples/" + ctrlgroupName + "/" + ctrlName + ".txt")
    // Stringbuilder to store src code lines
    val builder = new StringBuilder().append(htmlpage)

    try {
      // load src into String
      val in = file.openStream()
      val reader = new BufferedReader(new InputStreamReader(in))
      var isContent = true
      do {
        isContent = readSrc(reader.readLine(), builder)
      } while (isContent)
      reader.close()
    } catch {
      case cnfe: ClassNotFoundException => cnfe.printStackTrace()
      case urie: URISyntaxException => urie.printStackTrace()
      case ioe: IOException => ioe.printStackTrace()
    }
    // Complete the html content
    builder.append("</p></body></html>")

    //Border pane is sufficient to handle the content
    val borderPane = new BorderPane() {
      // Load source through webview
      center = new WebView() {
        contextMenuEnabled = false
        prefWidth = 300
        this.engine.loadContent(builder.mkString)
      }
    }

    borderPane
  }
}

trait EnsembleExample {
  def getContent: Node
}

trait DisplayablePage {
  def getPage: Node
}
