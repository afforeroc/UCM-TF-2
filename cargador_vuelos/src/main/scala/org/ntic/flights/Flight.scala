import org.ntic.flights.Airport
import org.ntic.flights.Time
import org.ntic.flights.FlightDate
import org.ntic.flights.Row
/**
 * This class is used to represent a flight with its information like the date, origin, destination, scheduled departure time,
 * scheduled arrival time, departure delay and arrival delay.
 *
 * @param flDate: String
 * @param origin: Airport
 * @param dest: Airport
 * @param scheduledDepTime: Time
 * @param scheduledArrTime: Time
 * @param depDelay: Double
 * @param arrDelay: Double
 */
case class Flight(flDate: String,
                  origin: Airport,
                  dest: Airport,
                  scheduledDepTime: Time,
                  scheduledArrTime: Time,
                  depDelay: Double,
                  arrDelay: Double) extends Ordered[Flight] {
  lazy val flightDate: FlightDate = FlightDate.fromString(flDate)
  lazy val actualDepTime: Time = Time.fromMinutes(scheduledDepTime.asMinutes + depDelay.toInt)
  lazy val actualArrTime: Time = Time.fromMinutes(scheduledArrTime.asMinutes + arrDelay.toInt)
  lazy val isDelayed: Boolean = depDelay != 0 || arrDelay != 0
  override def compare(that: Flight): Int = this.actualArrTime.compare(that.actualArrTime)
}

object Flight {
  /**
   * This function is used to create a Flight object from a string with the information of the flight separated by a
   * delimiter defined in the configuration. The function returns a Flight object with the information of the flight.
   * The input string should have the following format:
   * "FL_DATE,ORIGIN_AIRPORT_ID,ORIGIN,ORIGIN_CITY_NAME,ORIGIN_STATE_ABR,DEST_AIRPORT_ID,DEST,DEST_CITY_NAME,DEST_STATE_ABR,DEP_TIME,ARR_TIME,DEP_DELAY,ARR_DELAY"
   *
   * @param flightInfo: String
   * @return Flight
   */
  def fromString(flightInfo: String): Flight = {
    val delimiter = "," // Define the delimiter (adjust if needed from configuration)
    val columns = flightInfo.split(delimiter).map(_.trim)

    /**
     * This function is used to get the value of a column from the array of String generated from the row of the csv
     * and stored in the variable `columns`.
     * @param colName: String name of the column
     * @return String value of the column
     */
    def getColValue(colName: String): String = {
      val columnIndexMap = Map(
        "FL_DATE" -> 0,
        "ORIGIN_AIRPORT_ID" -> 1,
        "ORIGIN" -> 2,
        "ORIGIN_CITY_NAME" -> 3,
        "ORIGIN_STATE_ABR" -> 4,
        "DEST_AIRPORT_ID" -> 5,
        "DEST" -> 6,
        "DEST_CITY_NAME" -> 7,
        "DEST_STATE_ABR" -> 8,
        "DEP_TIME" -> 9,
        "ARR_TIME" -> 10,
        "DEP_DELAY" -> 11,
        "ARR_DELAY" -> 12
      )
      columns(columnIndexMap(colName))
    }

    val oriAirport = Airport(
      airportId = getColValue("ORIGIN_AIRPORT_ID").toLong,
      code = getColValue("ORIGIN"),
      cityName = getColValue("ORIGIN_CITY_NAME"),
      stateAbr = getColValue("ORIGIN_STATE_ABR"))

    val destAirport = Airport(
      airportId = getColValue("DEST_AIRPORT_ID").toLong,
      code = getColValue("DEST"),
      cityName = getColValue("DEST_CITY_NAME"),
      stateAbr = getColValue("DEST_STATE_ABR"))

    Flight(
      flDate = getColValue("FL_DATE"),
      origin = oriAirport,
      dest = destAirport,
      scheduledDepTime = Time.fromString(getColValue("DEP_TIME")),
      scheduledArrTime = Time.fromString(getColValue("ARR_TIME")),
      depDelay = getColValue("DEP_DELAY").toDouble,
      arrDelay = getColValue("ARR_DELAY").toDouble
    )
  }

  /**
   * This function is used to create a Flight object from a Row object. The function returns a Flight object with the
   * information of the flight.
   *
   * @param row: Row
   * @return Flight
   */
  def fromRow(row: Row): Flight = {
    val originAirport = Airport(
      airportId = row.originAirportId,
      code = row.origin,
      cityName = row.originCityName,
      stateAbr = row.originStateAbr
    )

    val destAirport = Airport(
      airportId = row.destAirportId,
      code = row.dest,
      cityName = row.destCityName,
      stateAbr = row.destStateAbr
    )

    Flight(
      flDate = row.flDate,
      origin = originAirport,
      dest = destAirport,
      scheduledDepTime = Time.fromString(row.depTime),
      scheduledArrTime = Time.fromString(row.arrTime),
      depDelay = row.depDelay,
      arrDelay = row.arrDelay
    )
  }
}
