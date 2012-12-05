package scalafx.ensemble
import java.io.File
object ioPractise {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(89); 
	val fil = new File(".");System.out.println("""fil  : java.io.File = """ + $show(fil ));$skip(19); val res$0 = 
	fil.isDirectory();System.out.println("""res0: Boolean = """ + $show(res$0))}
}