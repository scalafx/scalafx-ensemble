/*
 * Copyright (c) 2011-2021, ScalaFX Project
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

package org.scalafx

import javafx.embed.swing.JFXPanel
import javafx.{concurrent => jfxc}
import scalafx.Includes._
import scalafx.application.Platform
import scalafx.scene.Node
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.control.{Alert, Label, TextArea}
import scalafx.scene.layout.{GridPane, Priority}
import scalafx.stage.Window

import java.io.{PrintWriter, StringWriter}
import java.util.concurrent

/**
 * Helper methods for working with ScalaFX.
 */
package object extras {

  /**
   * Attempt to initialize JavaFX Toolkit. This is only needed when application is not
   * started by `JFXApp` or JavaFX `Application`.
   *
   * When JavaFX toolkit is not initialized and you attempt to use JavaFX components you will get exception:
   * `java.lang.IllegalStateException: Toolkit not initialized`.
   *
   * In JavaFX 9 and newer you can use `Platform.startup(() -> {})`.
   */
  def initFX(): Unit = {
    // Make sure that JavaFX Toolkit is not shutdown implicitly, it may not be possible to restart it.
    Platform.implicitExit = false
    // Create SFXPanel() to force initialization of JavaFX application thread.
    new JFXPanel()
  }

  /**
   * Run operation `op` on FX application thread.
   * If on FX Application thread it will wait for operation to compete,
   * if not on FX Application thread it will return without waiting for the operation to complete.
   *
   * @param op operation to be performed.
   */
  def onFX[R](op: => R): Unit = {
    if (Platform.isFxApplicationThread) {
      op
    } else {
      Platform.runLater {
        op
      }
    }
  }

  /**
   * Run operation `op` on FX application thread and wait for completion.
   * If the current thread is the FX application, the operation will be run on it.
   *
   * @param op operation to be performed.
   * @throws java.util.concurrent.CancellationException - if the computation was cancelled
   * @throws java.lang.InterruptedException             - if the current thread was interrupted while waiting
   * @throws java.util.concurrent.ExecutionException    - if the computation threw an exception
   */
  def onFXAndWait[R](op: => R): R = {
    if (Platform.isFxApplicationThread) {
      op
    } else {
      val callable = new concurrent.Callable[R] {
        override def call(): R = op
      }
      val future = new concurrent.FutureTask(callable)
      Platform.runLater(future)
      future.get()
    }
  }

  /**
   * Runs an operation `op` on a separate thread. Exceptions during execution are ignored.
   * Similar to [[org.scalafx.extras#run]], with default name for the thread: "offFX".
   *
   * @param op operation to be performed.
   */
  def offFX[R](op: => R): Unit = {
    run(op, "offFX")
  }

  /**
   * Run operation `op` off FX application thread and wait for completion.
   * If the current thread is not the FX application, the operation will be run on it (no new thread will ne created).
   *
   * @param op operation to be performed.
   * @throws java.util.concurrent.CancellationException - if the computation was cancelled
   * @throws java.lang.InterruptedException             - if the current thread was interrupted while waiting
   * @throws java.util.concurrent.ExecutionException    - if the computation threw an exception
   */
  def offFXAndWait[R](op: => R): R = {
    if (!Platform.isFxApplicationThread) {
      op
    } else {
      val callable = new concurrent.Callable[R] {
        override def call(): R = op
      }
      val future = new concurrent.FutureTask(callable)
      val th     = new Thread(future)
      th.setDaemon(true)
      th.start()
      future.get()
    }
  }

  /**
   * Show a modal dialog with an expandable details about an exception (stack trace).
   *
   * @param title       dialog title
   * @param message     message shown in the dialog header.
   * @param t           exception.
   * @param ownerWindow owner window that will be blacked by the dialog. Can be `null`.
   */
  def showException(title: String, message: String, t: Throwable, ownerWindow: Node): Unit = {
    val parentWindow = Option(ownerWindow).flatMap(n => Option(n.scene()).map(s => jfxWindow2sfx(s.window())))
    showException(title, message, t, parentWindow)
  }

  /**
   * Show a modal dialog with an expandable details about an exception (stack trace).
   *
   * @param title       dialog title
   * @param message     message shown in the dialog header.
   * @param t           exception.
   * @param ownerWindow owner window that will be blacked by the dialog. Can be `null` to match JavaFX convention.
   */
  def showException(title: String, message: String, t: Throwable, ownerWindow: Window): Unit = {
    showException(title, message, t, Option(ownerWindow))
  }

  /**
   * Show a modal dialog with an expandable details about an exception (stack trace).
   *
   * @param title       dialog title
   * @param message     message shown in the dialog header.
   * @param t           exception.
   * @param ownerWindow owner window that will be blacked by the dialog.
   */
  def showException(title: String, message: String, t: Throwable, ownerWindow: Option[Window] = None): Unit = {
    t.printStackTrace()

    // Rename to avoid name clashes
    val dialogTitle = title

    // Create expandable Exception.
    val exceptionText = {
      val sw = new StringWriter()
      val pw = new PrintWriter(sw)
      t.printStackTrace(pw)
      sw.toString
    }
    val label = new Label("The exception stack trace was:")
    val textArea = new TextArea {
      text = exceptionText
      editable = false
      wrapText = true
      maxWidth = Double.MaxValue
      maxHeight = Double.MaxValue
      vgrow = Priority.Always
      hgrow = Priority.Always
    }
    val expContent = new GridPane {
      maxWidth = Double.MaxValue
      add(label, 0, 0)
      add(textArea, 0, 1)
    }

    onFXAndWait {
      new Alert(AlertType.Error) {
        self =>
        initOwner(ownerWindow.orNull)
        self.title = dialogTitle
        headerText = message
        contentText = Option(t.getMessage).getOrElse("")
        // Set expandable Exception into the dialog pane.
        dialogPane().expandableContent = expContent
      }.showAndWait()
    }
  }

  /**
   * Run task on a named daemon thread.
   *
   * @param task to run
   * @param name name for the thread to run the operation. Useful for debugging.
   */
  def runTask[T](task: javafx.concurrent.Task[T], name: String): Unit = {
    val th = new Thread(task, name)
    th.setDaemon(true)
    th.start()
  }

  /**
   * Runs an operation `op` on a separate thread. Exceptions during execution are ignored.
   *
   * @param op   operation to run
   * @param name name for the thread to run the operation. Useful for debugging.
   */
  def run[R](op: => R, name: String): Unit = {

    val task = new jfxc.Task[R] {
      override def call(): R = op
    }

    runTask(task, name)
  }
}
