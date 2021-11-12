package com.assignment

import com.assignment.parser.{Airport, Country, Runway}
import com.assignment.query.QueryFilter
import org.scalatest.funsuite.AnyFunSuite

class QueryFilterTest extends AnyFunSuite  {

  val classloader = Thread.currentThread.getContextClassLoader
  val countries: List[Country] = Country.parse(classloader.getResource("countries.csv").getPath)
  val airports: List[Airport] = Airport.parse(classloader.getResource("airports.csv").getPath)
  val runways: List[Runway] = Runway.parse(classloader.getResource("runways.csv").getPath)


  test("Find number of airports by Country Name") {
    val query = new QueryFilter(countries, airports, runways)
    val filtered = query.findByCountry("United Kingdom")
    assert(!filtered.isEmpty)
    assert(filtered.size == 489)
    assert(filtered.filter(x => x.ident == "EG03")(0).runways.size == 1)
  }


  test("Find number of airports by Country ISO 2") {
    val query = new QueryFilter(countries, airports, runways)
    val filtered = query.findByCountry("GB")
    assert(!filtered.isEmpty)
    assert(filtered.size == 489)
    assert(filtered.filter(x => x.ident == "EG03")(0).runways.size == 1)
  }
}
