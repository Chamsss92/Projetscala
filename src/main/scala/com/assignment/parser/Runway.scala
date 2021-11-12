package com.assignment.parser

case class Runway(id: Int,
                  airportRef: Int,
                  airportIdent: String,
                  lengthFt: String,
                  widthFt: String,
                  surface: String,
                  lighted: Int,
                  closed: Int,
                  leIdent: String)


object Runway extends CsvParser[Runway] {

  def apply(filename: String): List[Runway] = parse(filename)

  override def write(data: List[Map[String, String]]): List[Runway] = {
    data.map(x => Runway(x("id").toInt, x("airport_ref").toInt, x("airport_ident"),
      x("length_ft"), x("width_ft"), x("surface"), x("lighted").toInt,
      x("closed").toInt, x("le_ident")))
  }
}
