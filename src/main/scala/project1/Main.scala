package project1

object Main extends App {
  val words = Tokenizer.run()

  val stopWords = new StopWords

  val filteredWords = words.filter(w => !stopWords.contains(w.toString))

  val stemmer = new PorterStemmer

  val stemmedWords = filteredWords.map(w => stemmer.stem(w.toString)).filter(w => w != "Invalid term")

  println(stemmedWords)

}
