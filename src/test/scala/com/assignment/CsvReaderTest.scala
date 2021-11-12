package com.assignment

import com.assignment.parser.{Airport, Country, Runway}
import org.scalatest.funsuite.AnyFunSuite

class CsvReaderTest extends AnyFunSuite  {

  val classloader = Thread.currentThread.getContextClassLoader

  test("Countries parser map to list of objects") {
    val fileName = classloader.getResource("countries.csv").getPath
    val country = Country.parse(fileName)
    assert(!country.isEmpty)
  }

  test("Airports parser map to list of objects") {
    val fileName = classloader.getResource("airports.csv").getPath
    val airports = Airport.parse(fileName)
    assert(!airports.isEmpty)
  }

  test("Runways parser map to list of objects") {
    val fileName = classloader.getResource("runways.csv").getPath
    val runways = Runway.parse(fileName)
    assert(!runways.isEmpty)
  }
}
