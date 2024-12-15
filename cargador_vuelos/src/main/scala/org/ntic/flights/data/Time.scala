package org.ntic.flights.data

case class Time(hours: Int, minutes: Int) extends Ordered[Time] {
  require(hours >= 0 && hours <= 23, "hours must be within 0 and 23")
  require(minutes >= 0 && minutes <= 59, "minutes must be within 0 and 59")
  val asMinutes: Int = hours * 60 + minutes
  override lazy val toString: String = f"$hours%02d:$minutes%02d"

  def minus(that: Time): Int = this.asMinutes - that.asMinutes
  def -(that: Time): Int = minus(that)
  override def compare(that: Time): Int = this - that
}

object Time {
  private val totalMinutesInADay = 1440

  /**
   * This function is used to create a Time object from a string
   * @param timeStr: String
   * @return Time
   */
  def fromString(timeStr: String): Time = {
    val formatted: String = timeStr.reverse.padTo(4, '0').reverse
    var hours: Int = formatted.substring(0, 2).toInt
    var minutes: Int = formatted.substring(2, 4).toInt
    if (hours >= 24) hours %= 24
    if (minutes >= 60) minutes %= 60
    Time(hours, minutes)
  }

  def fromMinutes(minutes: Int): Time = {
    val normalized = if (minutes < 0) 0 else minutes % totalMinutesInADay
    Time(normalized / 60, normalized % 60)
  }
}