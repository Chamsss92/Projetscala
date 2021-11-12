package com.assignment.report

import com.assignment.parser.{Airport, Country, Runway}
import com.assignment.query.QueryFilter

class ReportFilter(countries: List[Country], airports: List[Airport], runways: List[Runway])
{

  val queryFilter = new QueryFilter(countries, airports, runways)

  def top10CountriesByAirport(): List[Country] =  airports.groupBy(_.iso2)
      .map(t => (t._1, t._2.length))
      .toList
      .sortBy(x => x._2 )
      .reverse
      .map(x => x._1)
      .take(10)
      .map(iso2 => queryFilter.getCountry(iso2).get)


  def topBottom10CountriesByAirport(): List[Country] =  airports.groupBy(_.iso2)
    .map(t => (t._1, t._2.length))
    .toList
    .sortBy(x => x._2 )
    .map(x => x._1)
    .take(10)
    .map(iso2 => queryFilter.getCountry(iso2).get)


  def typeOfRunways(): Map[String, List[String]] = {
    runways.groupBy(_.airportIdent)
      .map(x => (queryFilter.getCountryByAirport(x._1), x._2.map(_.surface)))
      .filter(x => x._1.isDefined)
      .map(x => (x._1.get.code, x._2))
  }


  def top10CommonRunways(): List[Runway] = {
    runways.groupBy(_.leIdent)
      .map(t => (t._2.map(a => a.id), t._2.length))
      .toList
      .sortBy(x => x._2)
      .reverse
      .flatMap(x => x._1)
      .distinct
      .take(10)
      .map(x => queryFilter.getRunwayById(x))
      .filter(x => x.isDefined)
      .map(x => x.get)
  }
}
