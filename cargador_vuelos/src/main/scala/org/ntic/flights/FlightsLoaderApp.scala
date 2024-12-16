package org.ntic.flights
import org.ntic.flights.data.{Flight, FlightsFileReport, Row, FlightsLoaderConfig}

import scala.util.Try

object FlightsLoaderApp extends App {
  val fileLines: Seq[String] = FileUtils.getLinesFromFile(FlightsLoaderConfig.filePath)
  val rows: Seq[Try[Row]] = FileUtils.loadFromFileLines(fileLines)
  val flightReport: FlightsFileReport = FlightsFileReport.fromRows(rows)
  val flights: Seq[Flight] = flightReport.flights
  val outputDir = FlightsLoaderConfig.outputDir

  flights.map(flight => flight.origin).distinct.foreach { origin =>
    val filteredFlights = flights.filter(flight => flight.origin == origin)
    val delayedFlights = filteredFlights.filter(flight => flight.isDelayed).sorted
    val notDelayedFlights = filteredFlights.filterNot(flight => flight.isDelayed).sorted
    val delayedFlightsObj = s"$outputDir/${origin.code}_delayed.obj"
    val flightsObj = s"$outputDir/${origin.code}.obj"
    FileUtils.writeFile(delayedFlights, delayedFlightsObj)
    FileUtils.writeFile(notDelayedFlights, flightsObj)
  }
  println(flightReport)
}