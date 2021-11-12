package com.assignment.parser

case class Airport(id: Int,
                   ident: String,
                   airportType: String,
                   name: String,
                   latitude: String,
                   longitude: String,
                   elevation: String,
                   continent: String,
                   iso2: String,
                   isoRegion: String,
                   municipality: String,
                   scheduledService: String,
                   runways: List[Runway] = List.empty)

object Airport extends CsvParser[Airport] {
  def apply(filename: String): List[Airport] = parse(filename)

  override def write(data: List[Map[String, String]]): List[Airport] = {
    data.map(x => Airport(x("id").toInt, x("ident"), x("type"), x("name"),
      x("latitude_deg"), x("longitude_deg"), x("elevation_ft"),
      x("continent"), x("iso_country"), x("iso_region"), x("municipality"),x("scheduled_service")))
  }
}