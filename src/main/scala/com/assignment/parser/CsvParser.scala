package com.assignment.parser

import com.github.tototoshi.csv.CSVReader

import java.io.File


import com.github.tototoshi.csv.CSVReader

abstract class CsvParser[T] {

  def parse(filename: String): List[T] = {
    val reader = CSVReader.open(new File(filename))
    val data = reader.allWithHeaders()
    write(data)
  }

  def write(data: List[Map[String, String]]): List[T]
}