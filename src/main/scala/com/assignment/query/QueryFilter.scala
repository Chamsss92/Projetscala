package com.assignment.query

import com.assignment.parser.{Airport, Country, Runway}


class QueryFilter(countries: List[Country], airports: List[Airport], runways: List[Runway]) {


  def findByCountry(country: String): List[Airport] = {
    getCountry(country) match {
      case Some(value) => findAirportAndRunways(value)
      case None => List.empty
    }
  }

  def findAirportAndRunways(country: Country): List[Airport] =
    airports.filter(x => x.iso2 == country.code)
      .map(airport => airport.copy(runways = findRunways(airport)))

  def findRunways(airport: Airport): List[Runway] =
    runways.filter(runway => runway.airportIdent == airport.ident)

  def getCountry(country: String): Option[Country] =
    countries.find(x => x.name.toLowerCase() == country.toLowerCase()
      || x.code.toLowerCase() == country.toLowerCase())


  def getCountryByAirport(airportIdent: String): Option[Country] = {
    airports.filter(air => air.ident == airportIdent)
      .map(x => x.iso2)
      .distinct
      .map(x => getCountry(x).get)
      .headOption
  }

  def getRunwayById(id: Int): Option[Runway] =
    runways.find(runway => runway.id == id)
}
