package humidity.parser

import humidity.domain.SensorData

import java.io.File

object CsvParser {

  def getSensorDataFromCsvFile(file: File): List[SensorData] = {

    val bufferedSource = io.Source.fromFile(file)
    try {
      for {
        line <- bufferedSource.getLines.drop(1) // dropping the header
        cols = line.split(",").map(_.trim)
      } yield SensorData(cols(0), cols(1))
    }.toList finally {
      bufferedSource.close
    }
  }
}
