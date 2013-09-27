/*
 * Copyright (c) 2013, ScalaFX Ensemble Project
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

import java.util.Locale

import scalafx.ensemble.EnsembleThumbNail
import scalafx.ensemble.stage.DashboardPage
import scalafx.ensemble.stage.EnsembleTabbedPage
import scalafx.scene.Node
import scalafx.scene.control.ScrollPane
import scalafx.scene.control.TreeItem
import scalafx.scene.layout.BorderPane
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
          displayPage(new DashboardPage(value.split("-")(1).trim()))
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
    compare(ti.value(), t2.value())

  def thumbNailsSort = (t1: EnsembleThumbNail, t2: EnsembleThumbNail) =>
    compare(t1.caption.text(), t2.caption.text())

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
    scrollPane.styleClass.add("noborder-scroll-pane")
    scrollPane
  }


  def createSrcContent(ctrlName: String, ctrlgroupName: String = "") = {

    // Load syntax highlighter
    val shCoreJs = loadResource("/scalafx/ensemble/syntaxhighlighter/shCore.js") + ";"
    val shBrushScala = loadResource("/scalafx/ensemble/syntaxhighlighter/shBrushScala.js")
    val shCoreDefaultCss = loadResource("/scalafx/ensemble/syntaxhighlighter/shCoreDefault.css")

    // Load source code text
    val source = loadAndConvertSourceCode("/scalafx/ensemble/example/" + ctrlgroupName + "/Ensemble" + ctrlName + ".scala")

    // Create HTML, for now do not embed SyntaxHighlighter scripts to avoid issues with auto-escaping,
    // just put placeholders @@...@@
    val codeFont = if (isMac) "Ayuthaya" else "monospace"
    val html =
      <html>
        <head>
          <script type="text/javascript">
            @@shCoreJs@@
            @@shBrushScala@@
          </script>
          <style>
            @@shCoreDefaultCss@@
            .syntaxhighlighter {{
            overflow: visible;
            font: 12px
            {codeFont}
            !important; line-height: 150% !important;
            }}
            code {{ font: 12px
            {codeFont}
            !important; line-height: 150% !important; }}
          </style>
        </head>
        <body>
          <pre class="brush: scala;gutter: false;toolbar: false;">
            {"\n" + source}
          </pre>
          <script type="text/javascript">SyntaxHighlighter.all();</script>
        </body>
      </html>

    // Inject SyntaxHighlighter scripts
    val htmlString = html.mkString.
      replace("@@shCoreJs@@", shCoreJs).
      replace("@@shBrushScala@@", shBrushScala).
      replace("@@shCoreDefaultCss@@", shCoreDefaultCss)

    //Border pane is sufficient to handle the content
    val borderPane = new BorderPane() {
      // Load source through webview
      center = new WebView() {
        contextMenuEnabled = false
        prefWidth = 300
        this.engine.loadContent(htmlString)
      }
    }

    borderPane
  }

  def loadAndConvertSourceCode(path: String): String = {

    // Load source code text
    val sourceRaw = loadResource(path)

    // Remove initial comment
    var source = sourceRaw.replaceFirst( """(?s)/\*(.*?)\*/""", "")

    // Remove package statement
    source = source.replaceFirst( """package\s*\S*""", "")

    // Remove empty lines at the beginning
    source = source.replaceFirst( """(?s)\s*""", "")

    // Append required imports
    source = "" +
      "import scalafx.application.JFXApp\n" +
      "import scalafx.scene.Scene\n" +
      source

    // Remove local imports
    source = source.replaceAll( """import scalafx.ensemble.\S*\s*""", "")

    // Change `class ExambleSomething extends EnsembleExample {`
    // to     `object SomethingSample extends JFXApp
    source = source.replaceFirst(
      """class\s*Ensemble(\S*)\s*extends\s*EnsembleExample\s*\{""",
      """object $1Sample extends JFXApp {""")

    // Replace `getContent` method with stage and scene creation
    val stageHeader = "" +
      "\n" +
      "  stage = new JFXApp.PrimaryStage {\n" +
      "    scene = new Scene {\n" +
      "      root ="
    source = source.replaceFirst( """\s*def\s*getContent\s*=""", stageHeader)

    // Locate code that needs additional braces since two were introduced in `stageHeader`
    val openingBraceIndex = {
      val start = source.indexOf(stageHeader)
      require(start >= 0, "Internal error, failed to find `stageHeader`.")
      source.indexOf("{", start + stageHeader.length)
    }
    require(openingBraceIndex >= 0, "Internal error, failed to find `stageHeader`.")
    // Get index of closing brace
    val closingBraceIndex = {
      var braceCount = 1
      var index = openingBraceIndex
      while (braceCount > 0) {
        index += 1
        source(index) match {
          case '{' => braceCount += 1
          case '}' => braceCount -= 1
          case _ => {}
        }
      }
      index
    }

    // Ident body of the code that used to be `getContent` but now is assigned to scene.root.
    val prefix = source.substring(0, openingBraceIndex + 1)
    val bodyIndented = {
      val body = source.substring(openingBraceIndex + 1, closingBraceIndex + 1)
      body.lines.mkString("\n    ")
    }
    val postfix = source.substring(closingBraceIndex + 1)

    // Combine final code
    prefix +
      bodyIndented + "\n" +
      "    }\n" +
      "  }" +
      postfix
  }


  def loadResource(path: String): String = {
    val in = this.getClass.getResourceAsStream(path)
    scala.io.Source.fromInputStream(in).mkString
  }

  def isMac: Boolean = {
    val os = System.getProperty("os.name").toLowerCase(new Locale(""))
    os.indexOf("mac") >= 0
  }
}

trait EnsembleExample {
  def getContent: Node
}

trait DisplayablePage {
  def getPage: Node
}
