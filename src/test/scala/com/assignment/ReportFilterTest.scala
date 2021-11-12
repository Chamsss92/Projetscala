package com.assignment

import com.assignment.parser.{Airport, Country, Runway}
import com.assignment.report.ReportFilter
import org.scalatest.funsuite.AnyFunSuite

class ReportFilterTest extends AnyFunSuite  {

  val classloader: ClassLoader = Thread.currentThread.getContextClassLoader

  val countries: List[Country] = Country.parse(classloader.getResource("countries.csv").getPath)
  val airports: List[Airport] = Airport.parse(classloader.getResource("airports.csv").getPath)
  val runways: List[Runway] = Runway.parse(classloader.getResource("runways.csv").getPath)

  val reportFilter = new ReportFilter(countries, airports, runways)

  test("Top 10 Highest Number of Airports In a Country") {
    val top10 = reportFilter.top10CountriesByAirport()
    assert(top10.size == 10)
    assert(top10(0).id == 302755)
    assert(top10(0).code == "US")
    assert(top10(0).name == "United States")
  }

  test("Bottom 10 Lowest Number of Airports In a Country") {
    val bottom10 = reportFilter.topBottom10CountriesByAirport()
    assert(bottom10.size == 10)
    assert(bottom10(0).id == 302698)
    assert(bottom10(0).code == "JE")
    assert(bottom10(0).name == "Jersey")
  }


  test("Type of runway by country - surface") {
    val runwaySurfacesByCountry = reportFilter.typeOfRunways()

    val surface1 = runwaySurfacesByCountry("US")
    assert(surface1.size > 0)

    val surface2 = runwaySurfacesByCountry("GB")
    assert(surface2.size == 3)
  }

  test("Top 10 most common runways") {
    val commonRunways = reportFilter.top10CommonRunways()

    assert(commonRunways.size == 10)
    assert(commonRunways(0).id == 269408)
    assert(commonRunways(0).leIdent == "H1")

  }
}