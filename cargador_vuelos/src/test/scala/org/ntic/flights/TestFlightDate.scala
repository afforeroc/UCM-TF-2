package org.ntic.flights
import org.scalatest.funsuite.AnyFunSuite

class TestFlightDate extends AnyFunSuite {
  test("Conjunto de pruebas") {
    // Prueba que funciona correctamente
    assert(FlightDate.fromString("7/1/2023 12:00:00 AM") == FlightDate(7, 1, 2023))
    // Debería lanzar un AssertionError si el año es menor de 1987
    intercept[AssertionError] { FlightDate.fromString("7/1/1986 12:00:00 AM") }
    // Debería lanzar un AssertionError si el mes es mayor a 12
    intercept[AssertionError] { FlightDate.fromString("7/13/1987 12:00:00 AM") }
    // Debería lanzar un AssertionError si el mes es menor a 1
    intercept[AssertionError] { FlightDate.fromString("7/0/1987 12:00:00 AM") }
    // Debería lanzar un AssertionError si día es mayor al la máxima cantidad de días para el mes establecido
    intercept[AssertionError] { FlightDate.fromString("31/11/2024 12:00:00 AM") }
    // Debería lanzar un AssertionError si día es menor al la minima cantidad de días para el mes establecido
    intercept[AssertionError] { FlightDate.fromString("0/11/2024 12:00:00 AM") }
  }
}
