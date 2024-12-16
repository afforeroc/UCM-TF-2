package org.ntic.flights
import org.ntic.flights.data.{Flight, Row, FlightsLoaderConfig}
import java.io.{FileOutputStream, ObjectOutputStream}
import scala.io.Source
import scala.util.{Try, Using}

object FileUtils {

  /**
   * This function is used to check if the line is valid or not
   * @param s: String
   * @return Boolean: true if the line is invalid, false otherwise
   */
  def isInvalidLine(s: String): Boolean =
    if (s.trim.isEmpty) true
    else {
      val tokens = s.split(FlightsLoaderConfig.delimiter)
      tokens.length != FlightsLoaderConfig.headersLength
    }

  /**
   * This function is used to read the file located in the path `filePath` and return a list of lines of the file
   *
   * @param filePath: String
   * @return List[String]
   */
  def getLinesFromFile(filePath: String): List[String] = {
    Using(Source.fromFile(filePath)) { source =>
      source.getLines().toList
    }.getOrElse(Nil)
  }

  /**
   * This function is used to load the rows from the file lines
   *
   * @param fileLines: Seq[String]
   * @return Seq[Try[Row]]
   */
  def loadFromFileLines(fileLines: Seq[String]): Seq[Try[Row]] = {
    val lines = if(FlightsLoaderConfig.hasHeaders) fileLines.tail else fileLines
    lines.map {line =>
        val tokens = line.split(FlightsLoaderConfig.delimiter).toSeq
        Row.fromStringList(tokens)
    }
  }

  def writeFile(flights: Seq[Flight], outputFilePath: String): Unit = {
    val out = new ObjectOutputStream(new FileOutputStream(outputFilePath))
    out.writeObject(flights)
    out.close()
  }

}