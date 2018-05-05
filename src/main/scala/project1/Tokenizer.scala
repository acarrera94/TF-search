package project1
import scala.io.Source
import java.io.{FileNotFoundException, IOException}
object Tokenizer {
  val punctuation = "[^\\p{L}\\p{Nd}]+"


  val dictionary = readFile("/Users/andrecarrera/Documents/school/2018sp/cs453/project1/src/textfiles/dictionary.txt")
      .map(s => s.replaceAll(punctuation, ""))


  def readFile(filename: String): List[String] = {
    try {
      Source.fromFile(filename, "ISO8859_1").getLines.toList
    } catch {
      case e: FileNotFoundException => List()
      case e: IOException => List()
    }
  }

  def run (filename :String): Seq[Any] = {



    def makeHyphenWord(x: String) = {
      val concatenated = x.replaceAll(punctuation, "")
      if (dictionary.contains(concatenated)) concatenated
      else x.split("-").toList
    }

    def fixPunctuation(x: String) = {
      if (x.contains("-")) makeHyphenWord(x)
      else if (x.exists(!_.isLetterOrDigit)) x.replaceAll(punctuation, "")
      else x
    }
    def flat2(any: Any): List[Any] =
      any match {
        case i: List[_] => i
        case _ => List(any)
      }

    val lines = readFile(filename)

    val allWords = lines
      .flatMap(s => s.toLowerCase().split("\\s+"))
      .filter(x => x != "")
      .map(x => fixPunctuation(x))
      .flatten(flat2)

    allWords
  }
}
