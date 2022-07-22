package humidity.domain

case class SensorData(sensorId:String, humidity:String)

case class SensorOutPut(sensorId:String, minimum:Int, average:Int, maximum:Int)
