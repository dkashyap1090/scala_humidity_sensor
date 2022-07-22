package parser

import humidity.domain.SensorData
import humidity.parser.CsvParser.getSensorDataFromCsvFile
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import java.io.File

class CsvParserSpec extends AnyFlatSpec with Matchers {

  "CsvParser" should "Parse The Csv file successfully" in {

    val listOfSensorData: List[SensorData] = getSensorDataFromCsvFile(new File("/Users/dhirendrakashyap/Desktop/scala_humidity_sensor/src/test/resources/leader-1.csv"))
    listOfSensorData.nonEmpty should be (true)
    listOfSensorData.size should be (3)
  }
}
