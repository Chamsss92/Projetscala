package com.assignment.parser

case class Country(id: Int,
                   code: String,
                   name: String,
                   continent: String,
                   wikipediaLink: String,
                   airports: List[Airport] = List.empty)
{
}

object Country extends CsvParser[Country] {
  def apply(filename: String): List[Country] = parse(filename)
  override def write(data: List[Map[String, String]]): List[Country] = {
    data.map(x => Country(x("id").toInt, x("code"), x("name"), x("continent"),
      x("wikipedia_link")))
  }
}
