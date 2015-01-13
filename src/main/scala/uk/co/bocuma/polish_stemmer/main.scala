package uk.co.bocuma.polish_stemmer
import org.getopt.stempel._
object Main {
    def main(args: Array[String]) {
      val stemmer = new Stemmer()
      println(stemmer.stem("robisz",false))
    }
}
