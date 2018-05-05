package project1

import scala.math.log
object Main extends App {

  case class Document (index: Int, frequency: Int, max: Double)

  val stemmer = new PorterStemmer

  def stem(a: String) = stemmer.stem(a)


  def indexedWordsInDocuments(docNumber: Int): Map[String, List[Document]] ={

    if (docNumber == 323 ) return Map()

    val words = Tokenizer.run(s"/Users/andrecarrera/Documents/school/2018sp/cs453/project1/src/docs/Doc (${docNumber.toString}).txt")

    val filteredWords = words.filter(w => !(new StopWords).contains(w.toString))

    val stemmedWords = filteredWords.map(w => stem(w.toString)).filter(w => w != "Invalid term")

    val values = stemmedWords.groupBy(x => x).mapValues(_.size)

    val maxValue = values.maxBy(_._2)._2

    val indexedWords = values
      .map(x => x._1 -> List(Document(docNumber, x._2, maxValue )))

    val nextIndexedWords = indexedWordsInDocuments(docNumber + 1)

    val mergedIndexes = indexedWords ++ nextIndexedWords.map { case (k,v) => k -> (v ::: indexedWords.getOrElse(k,List())) }

    mergedIndexes
  }

  def inverseDocumentFrequency(w: String):Double = {
    log2(322 / allIndexed(stem(w)).length)
  }
  def log2(x: Double): Double = scala.math.log(x)/scala.math.log(2)

  def score(query: String, document: Int) = {
    query.split(" ").map(s => {
      val sco = termFrequency(s, document) * inverseDocumentFrequency(s)
      if (sco.isNaN) 0.0
      else sco
    })
  }

  def termFrequency (word: String, document: Int ) :Double = {
    //frequency of a word in a document / max1(frequency(l,d)
    val thisStem = allIndexed.get(stem(word))

    if (thisStem.isEmpty) 0

    val doc = thisStem.get
      .find(doc => doc.index == document)
      .getOrElse(Document(0,0,0.0))

    if (doc.max==0 ) 0
    doc.frequency / doc.max
    //divide by the most occuring word in document
  }


  def topScores(query: String) = {
    (1 to 322).map(i => i -> score(query, i)).sortWith(_._2.sum > _._2.sum)
  }

  val allIndexed = indexedWordsInDocuments(1)

  println(allIndexed)


  println("Enter a Query:")
  for (ln <- io.Source.stdin.getLines) {
    topScores(ln.toLowerCase).foreach(a =>{
      print(s"Doc: ${a._1}  Score: ${a._2.sum}")
      a._2.foreach(b => print(s" $b "))
      println
    })
  }

}
