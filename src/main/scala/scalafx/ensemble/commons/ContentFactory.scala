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

package scalafx.ensemble.commons

import java.util.Locale
import javafx.scene.control.Alert
import javafx.scene.control.Alert.AlertType
import javafx.stage.Stage

import scalafx.Includes._
import scalafx.ensemble.commons.IOUtils._
import scalafx.ensemble.sbt.SBTProjectBuilder
import scalafx.event.ActionEvent
import scalafx.scene.Node
import scalafx.scene.control.{Button, Label, ScrollPane, ToolBar}
import scalafx.scene.input.{Clipboard, ClipboardContent}
import scalafx.scene.layout.{BorderPane, Priority, StackPane, VBox}
import scalafx.scene.web.WebView
import scalafx.stage.DirectoryChooser

/**
 * populates the tabbed content by loading
 * EnsembleExample instance
 */
object ContentFactory {
  def createContent(exampleName: String, groupName: String) = {

    // Construct content of the samples dynamically
    val fullClassName = ExampleInfo.className(exampleName, groupName)
    var cache = Map[String, EnsembleExample]()
    val sampleNode = if (cache.get(fullClassName).isDefined) {
      cache(fullClassName).getContent
    } else {
      val inst = Class.forName(fullClassName).newInstance().asInstanceOf[EnsembleExample]
      cache = cache.+((fullClassName, inst))
      inst.getContent
    }

    val header = new Label(exampleName) {
      styleClass += "page-header"
    }

    val sampleArea = new StackPane {
      children = sampleNode
      vgrow = Priority.Sometimes
    }

    // ScrollPane is applied for borderPane that contains samples
    new ScrollPane {
      fitToWidth = true
      fitToHeight = true
      content = new VBox(8) {
        children ++= Seq(header, sampleArea)
        styleClass += "sample-page"
      }
      styleClass += "noborder-scroll-pane"
    }
  }


  def createSrcContent(exampleName: String, exampleGroupName: String): Node = {

    // Load syntax highlighter
    val shCoreJs = loadResourceAsString(this, "/scalafx/ensemble/syntaxhighlighter/shCore.js") + ";"
    val shBrushScala = loadResourceAsString(this, "/scalafx/ensemble/syntaxhighlighter/shBrushScala.js")
    val shCoreDefaultCss = loadResourceAsString(this, "/scalafx/ensemble/syntaxhighlighter/shCoreDefault.css")

    val exampleInfo = new ExampleInfo(exampleName, exampleGroupName)

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
            {"\n" + exampleInfo.sourceCode}
          </pre>
          <script type="text/javascript">SyntaxHighlighter.all();</script>
        </body>
      </html>

    // Inject SyntaxHighlighter scripts
    val htmlSource = html.mkString.
      replace("@@shCoreJs@@", shCoreJs).
      replace("@@shBrushScala@@", shBrushScala).
      replace("@@shCoreDefaultCss@@", shCoreDefaultCss)

    //Border pane is sufficient to handle the content
    val borderPane = new BorderPane() {

      top = new ToolBar {
        items = Seq(
          new Button {
            thisButton =>
            text = "Save SBT Project..."
            tooltip = "Save sample code in a new project that can be build and run with SBT"
            onAction = (ae: ActionEvent) => try {
              val initialDir = SBTProjectBuilder.parentDir
              val fileChooser = new DirectoryChooser() {
                title = "Save SBT Project As:"
                initialDirectory = initialDir
              }
              val result = Option(fileChooser.showDialog(thisButton.scene.window()))
              result match {
                case Some(projectDir) =>
                  SBTProjectBuilder.createSampleProject(projectDir, exampleInfo)
                  SBTProjectBuilder.parentDir = projectDir.getCanonicalFile.getParentFile
                case _ =>
              }
            } catch {
              case t: Throwable =>
                val stage = thisButton.scene().window().asInstanceOf[Stage]
                showError(stage, title = thisButton.text(), header = "Error saving sample SBT project",
                  message = t.getClass.getName + ": " + t.getMessage, t)
            }
          },
          new Button {
            thisButton =>
            text = "Copy Source"
            tooltip = "Copy sample source code to clipboard"
            onAction = (ae: ActionEvent) => try {
              val content = new ClipboardContent()
              content.putString(exampleInfo.sourceCode)
              content.putHtml(htmlSource)
              Clipboard.systemClipboard.setContent(content)
            } catch {
              case t: Throwable =>
                val stage = thisButton.scene().window().asInstanceOf[Stage]
                showError(stage, title = thisButton.text(),
                  header = "Error copying source to clipboard", message = t.getClass.getName + ": " + t.getMessage, t)
            }
          }
        )
      }
      // Load source through webview
      center = new WebView() {
        contextMenuEnabled = false
        prefWidth = 300
        this.engine.loadContent(htmlSource)
      }
    }

    borderPane
  }

  def isMac: Boolean = {
    val os = System.getProperty("os.name").toLowerCase(new Locale(""))
    os.indexOf("mac") >= 0
  }

  private def showError(stage: Stage, title: String, header: String, message: String, t: Throwable): Unit = {
    t.printStackTrace()
    val alert = new Alert(AlertType.ERROR)
    alert.initOwner(stage)
    alert.setContentText(message)
    alert.setHeaderText(header)
    alert.setTitle(title)
    alert.showAndWait()
  }
}
