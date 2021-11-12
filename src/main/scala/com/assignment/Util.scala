package com.assignment

import java.nio.file.Paths

object Util {

  def getPath(path: String): String =
    s"${Paths.get(".").toAbsolutePath}$path"
}
