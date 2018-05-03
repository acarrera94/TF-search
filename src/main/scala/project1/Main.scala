package project1

object Main extends App {
  val words = Tokenizer.run()

  val stopWords = new StopWords

  val filteredWords = words.filter(w => !stopWords.contains(w.toString))

  println(filteredWords)

}
