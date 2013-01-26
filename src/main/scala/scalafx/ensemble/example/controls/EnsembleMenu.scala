package scalafx.ensemble.example.controls

import scalafx.Includes._
import scalafx.ensemble.commons.EnsembleExample
import scalafx.geometry.Insets
import scalafx.scene.Node
//import scalafx.scene.Node.sfxNode2jfx
import scalafx.scene.control.Menu
import scalafx.scene.control.MenuBar
import scalafx.scene.control.MenuItem
import scalafx.scene.image.Image
//import scalafx.scene.image.Image.sfxImage2jfx
import scalafx.scene.image.ImageView
import scalafx.scene.layout.VBox
import scalafx.scene.text.Font
import scalafx.scene.text.Text

class EnsembleMenu extends EnsembleExample {
  def getContent = {
    new VBox {
      vgrow = javafx.scene.layout.Priority.ALWAYS
      hgrow = javafx.scene.layout.Priority.ALWAYS
      spacing = 10
      margin = Insets(50, 0, 0, 50)
      content = List(
        new Text {
          text = "Ensemble Menu"
          font = new Font("Verdana", 20)
        },
        new MenuBar {
          maxHeight = 70
          maxWidth = 400
          useSystemMenuBar = true
          //TODO CheckMenuItem is missing in scalafx.. create it later
          menus = List(
            new Menu("Scala") {
              items = List(
                new Menu {
                  text = "Author Info"
                  graphic = new ImageView {
                    image = new Image(this.getClass.getResourceAsStream("/scalafx/ensemble/images/crumb-selected-focused.png"))
                    margin = Insets(0, 0, 0, 5)
                  }.asInstanceOf[Node]
                  items = List(new MenuItem {
                    text = "Type Safe"
                  }, new MenuItem {
                    text = "Martin Odersky"
                  })
                }, new Menu {
                  text = "Features"
                  items = List(new MenuItem {
                    text = "Object Oriented"
                  }, new MenuItem {
                    text = "Functional"
                  })
                }, new MenuItem {
                  text = "ScalaFX"
                })
            })
        })
    }
  }
}