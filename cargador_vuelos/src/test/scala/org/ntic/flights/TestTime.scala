import org.ntic.flights.Time
import org.scalatest.funsuite.AnyFunSuite

class TestTime extends AnyFunSuite {
  test("Conjunto de pruebas") {
    // Prueba que funciona correctamente
    val time1 = Time.fromString("1234")
    assert(time1.hours == 12)
    assert(time1.minutes == 34)
    // Prueba la diferencia de minutos entre dos objetos Time
    val time2 = Time.fromString("1300")
    val time3 = Time.fromString("1200")
    val diff_minutes = time2 - time3
    assert(diff_minutes == 60)
    // Prueba la comparación entre dos objetos Time
    val time4 = Time.fromString("1300")
    val time5 = Time.fromString("1200")
    assert(time4 > time5)
    assert(!(time4 < time5))
  }
}