package org.ntic.flights.data
import scala.util.{Failure, Success, Try}

/**
 * This class is used to represent a report of the flights file with the valid rows, invalid rows and the flights
 * extracted from the valid rows.
 * @param validRows: Seq[Row]
 * @param invalidRows: Seq[String]
 * @param flights: Seq[Flight]
 */
case class FlightsFileReport(validRows: Seq[Row], invalidRows: Seq[String], flights: Seq[Flight]) {
  override val toString: String = {
    val numberOfValidRows = validRows.size
    val numberOfInvalidRows = invalidRows.size

    val errorDetails = invalidRows
      .groupBy(identity)
      .map { case (error, occurrences) => s"$error: ${occurrences.size}" }
      .mkString("\n")

    s"""
       |FlightsReport:
       |  - $numberOfValidRows valid rows.
       |  - $numberOfInvalidRows invalid rows.
       |Error summary:
       |$errorDetails""".stripMargin
  }

}

object FlightsFileReport {
  /**
   * This function is used to create a FlightsFileReport from a list of Try[Row] objects where each Try[Row] represents a row
   * loaded from the file. If the row is valid, it is added to the validRows list, otherwise the error message is added to
   * the invalidRows list. Finally, the valid rows are converted to Flight objects and added to the flights list.
   *
   * @param rows: Seq[Try[Row]]
   * @return FlightsFileReport
   */
    def fromRows(rows: Seq[Try[Row]]): FlightsFileReport = {
      val validRows = rows.filter(row => row.isSuccess).map(row => row.get)
      val invalidRows = rows.filter(row => row.isFailure).map(row => row.failed.get.toString)
      val flights = validRows.map(row => Flight.fromRow(row))
      FlightsFileReport(validRows, invalidRows, flights)
    }
}

