package util

import humidity.domain.SensorData
import humidity.util.Utils
import humidity.util.Utils.{average, getListOfFilesFromDirectory}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import java.io.File
class UtilsSpec extends AnyFlatSpec with Matchers {

  "Utils" should "get list of files from directory successfully" in {

    val files: Seq[File] = getListOfFilesFromDirectory("/Users/dhirendrakashyap/Desktop/scala_humidity_sensor/src/test/resources/")
    files.nonEmpty should be (true)
    files.size should be (1)
  }
  it should "get average" in {

    val average = Utils.average(List(1,2,3))
    average should be (2)

  }

  it should "get min and max" in {

    val (max, min) = Utils.findMaxAndMin(List(1,2,3))
    min should be (1)
    max should be (3)
  }
}





