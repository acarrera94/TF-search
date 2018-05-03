package project1
import scala.io.Source

object Tokenizer {

  def run (): Seq[Any] = {
    val filename = "/Users/andrecarrera/Documents/school/2018sp/cs453/project1/src/textfiles/Doc (2).txt"
    val dictionaryFilename = "/Users/andrecarrera/Documents/school/2018sp/cs453/project1/src/textfiles/dictionary.txt"
    val punctuation = "[^\\p{L}\\p{Nd}]+"

    def readFile(filename: String): Seq[String] = {
      val bufferedSource = io.Source.fromFile(filename)
      val lines = (for (line <- bufferedSource.getLines()) yield line).toList
      bufferedSource.close
      lines
    }
    val dictionary = readFile(dictionaryFilename)
      .map(s => s.replaceAll(punctuation, ""))

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
