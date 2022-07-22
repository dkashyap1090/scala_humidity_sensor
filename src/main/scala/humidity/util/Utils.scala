package humidity.util

import java.io.File

object Utils {

  def getListOfFilesFromDirectory(directory: String): List[File] = {
    val dir = new File(directory)
    if (dir.exists && dir.isDirectory) {
      dir.listFiles.filter(_.isFile).toList
    } else {
      List.empty[File]
    }
  }

  def average(listOfHumidity: List[Int]): Int = {
    if (listOfHumidity.nonEmpty)
      listOfHumidity.sum / listOfHumidity.length
    else
      0
  }

  def findMaxAndMin(listOfHumidity: List[Int]): (Int, Int) = {
    if (listOfHumidity.nonEmpty)
      listOfHumidity.foldLeft((listOfHumidity.head, listOfHumidity.head))((a, b) => (if (a._1 > b) a._1 else b, if (a._2 < b) a._2 else b))
    else
      (0,0)
  }
}
