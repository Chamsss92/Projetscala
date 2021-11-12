package com.assignment

import com.assignment.parser.{Airport, Country, Runway}
import com.assignment.query.QueryFilter
import com.assignment.report.ReportFilter

import java.nio.file.Paths
import scala.io.StdIn

object Client extends App {

  val countries: List[Country] = Country.parse(Util.getPath("/data/countries.csv"))
  val airports: List[Airport] = Airport.parse(Util.getPath("/data/airports.csv"))
  val runways: List[Runway] = Runway.parse(Util.getPath("/data/runways.csv"))

  println("Enter Query or Report")
  val input = StdIn.readLine()

  if(input.toLowerCase() == "query") {
    val query = new QueryFilter(countries, airports, runways)
    println("Entry Country Name or ISO2")
    val country: String = StdIn.readLine()
    val found = query.findByCountry(country)
    println(found)
  }
  else if(input.toLowerCase() == "report") {
    println("Choose following options")
    println("Press 1 for Top 10 Countries With Most Airports")
    println("Press 2 for Bottom 10 Countries With Lowest Airports")
    println("Press 3 for Type of runways per countries")
    println("Press 4 for Top 10 most common runways latitude")

    val report = new ReportFilter(countries, airports, runways)
    val found = StdIn.readLine() match {
      case "1" => report.top10CountriesByAirport()
      case "2" => report.topBottom10CountriesByAirport()
      case "3" => report.typeOfRunways()
      case "4" => report.top10CommonRunways()
    }
    println(found)
  }
}
