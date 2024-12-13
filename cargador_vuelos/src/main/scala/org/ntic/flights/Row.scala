package org.ntic.flights
import scala.util.{Try, Success, Failure}

/**
 * This class is used to represent a row of the flights data. It contains the following fields:
 * @param flDate: String
 * @param originAirportId: Long
 * @param origin: String
 * @param originCityName: String
 * @param originStateAbr: String
 * @param destAirportId: Long
 * @param dest: String
 * @param destCityName: String
 * @param destStateAbr: String
 * @param depTime: String
 * @param depDelay: Double
 * @param arrTime: String
 * @param arrDelay: Double
 */
case class Row (
                 flDate: String,
                 originAirportId: Long,
                 origin: String,
                 originCityName: String,
                 originStateAbr: String,
                 destAirportId: Long,
                 dest: String,
                 destCityName: String,
                 destStateAbr: String,
                 depTime: String,
                 depDelay: Double,
                 arrTime: String,
                 arrDelay: Double
               )

object Row {
  /**
   * This method is used to create a Row object from a list of tokens. It returns a Try[Row] object.
   * If the list of tokens is not valid or any of the tokens is invalid, it returns a Failure object. Otherwise, it returns a Success object.
   *
   * @param tokens: Seq[String]
   * @return Try[Row]
   */
  def fromStringList(tokens: Seq[String]): Unit = {
    val result: Try[Row] = Try {
      val flDate = tokens.head.trim
      val originAirportId = tokens(1).trim.toLong
      val origin = tokens(2).trim
      val originCityName = tokens(3).trim
      val originStateAbr = tokens(4).trim
      val destAirportId = tokens(5).trim.toLong
      val dest = tokens(6).trim
      val destCityName = tokens(7).trim
      val destStateAbr = tokens(8).trim
      val depTime = tokens(9).trim
      val depDelay = tokens(10).trim.toDouble
      val arrTime = tokens(11).trim
      val arrDelay = tokens(12).trim.toDouble
      Row(flDate, originAirportId, origin, originCityName, originStateAbr, destAirportId, dest, destCityName, destStateAbr, depTime, depDelay, arrTime, arrDelay)
    }
    result match {
      case Success(value) => println(s"La creación del Row fue exitosa con el valor: $value")
      case Failure(exception) => println(s"La creación del Row fue fallido con la excepción: ${exception.getMessage}")
    }
  }
}

