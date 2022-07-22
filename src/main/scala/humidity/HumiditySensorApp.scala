package humidity

import humidity.domain.{SensorData, SensorOutPut}
import humidity.parser.CsvParser.getSensorDataFromCsvFile
import humidity.util.Constants.NAN
import humidity.util.Utils
import humidity.util.Utils.{average, findMaxAndMin}

import java.io.File
import scala.collection.immutable
import scala.math.Ordering.Implicits.seqOrdering

object HumiditySensorApp extends App{
  if (args.length != 1) {
    println("Please provide directory")
  }
  val directoryName: String = args(0)
  val fileNames: List[File] = Utils.getListOfFilesFromDirectory(directoryName)
  val listOfSensorData: List[SensorData] = fileNames.flatMap(getSensorDataFromCsvFile)
  val mapOfSensorIdAndHumidity: Map[String, List[String]] = listOfSensorData.foldLeft(Map.empty[String, List[String]]) { (a, sd) =>

    if (a.contains(sd.sensorId)) {
      a.updated(sd.sensorId, sd.humidity :: a(sd.sensorId))
    }
    else {
      a + (sd.sensorId -> List(sd.humidity))
    }

  }

  println(s"Num of processed files: ${fileNames.size}" )
  println(s"Num of processed measurements: ${listOfSensorData.count(_.humidity.nonEmpty)}" )
  println(s"Num of failed measurements: ${listOfSensorData.count(_.humidity==NAN)}")
  println("Sensors with highest avg humidity: ")
  println("sensor-id, min, avg, max: ")
  val sensorIds = mapOfSensorIdAndHumidity

  val sensorOutPut: List[SensorOutPut] = sensorIds.map { sId =>
    val validSensorHumidity = mapOfSensorIdAndHumidity(sId._1).filter(_!=NAN).map(_.toInt)
    val avg = average(validSensorHumidity)
    val (max, min)= findMaxAndMin(validSensorHumidity)
    SensorOutPut(sId._1, min, avg, max)
  }.toList.sortBy(-_.average)

  sensorOutPut.foreach{ sensorOutPut=>
    println(s"   ${sensorOutPut.sensorId} , ${if(sensorOutPut.minimum==0) NAN else sensorOutPut.minimum}, ${if(sensorOutPut.average==0) NAN else sensorOutPut.average }, ${if(sensorOutPut.maximum ==0) NAN else sensorOutPut.maximum } ")
  }
}


