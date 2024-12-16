package org.ntic.flights.data
import com.sun.media.sound.InvalidFormatException
import java.time.YearMonth

/**
 * This class is used to represent a date of a flight
 * @param day: Int
 * @param month: Int
 * @param year: Int
 */
case class FlightDate(day: Int, month: Int, year: Int) {
  private lazy val formattedDate: String = f"$day%02d/$month%02d/$year%02d"
  override def toString: String = formattedDate
}

object FlightDate {
  /**
   * This function is used to convert a string to a FlightDate
   * @param date: String
   * @return FlightDate
   */
  def fromString(date: String): FlightDate = {
    date.split(" ").head.split("/").map(x => x.toInt).toList match {
      case List(month, day, year) => {
        try {
          // Validar que el año sea mayor o igual a 1987
          if (year < 1987) {
            throw new AssertionError(s"El año $year no es válido ya que debe ser mayor o igual a 1987.")
          }
          // Validar que el mes esté en el rango de 1 a 12
          if (month < 1 || month > 12) {
            throw new AssertionError(s"El mes $month no es válido ya que debe estar entre 1 y 12.")
          }
          // Usamos YearMonth para obtener el número máximo de días en el mes para el año especificado
          val yearMonth = YearMonth.of(year, month)
          val maxDayInMonth = yearMonth.lengthOfMonth()  // Obtiene el máximo de días del mes
          // Validar que el día esté dentro del rango de días del mes
          if (day < 1 || day > maxDayInMonth) {
            throw new AssertionError(s"El día $day no es válido para el mes $month del año $year.")
          }
          // Si todas las validaciones son correctas, devolver el objeto FlightDate
          FlightDate(day, month, year)
        } catch {
          case e: AssertionError =>
            // Lanza el AssertionError con el mensaje adecuado
            throw new AssertionError(s"Error en la fecha. Detalles: ${e.getMessage}")
        }
        FlightDate(day, month, year)
      }
      case _ => throw new InvalidFormatException(s"$date tiene un formato inválido")
    }
  }
}
