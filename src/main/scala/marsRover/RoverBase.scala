package marsRover
import scala.math
/**
  * Created by luke on 16/05/17.
  */


object Rover extends App{

}
class Position(val x: Int, val y: Int)
class Obstacle(val location: Position)


class Rover(val location: Position, val direction: Char, val instructions: Array[Char]){

}

class Planet(val minPos: Position, val maxPos: Position){
  def calculateSize: Int ={
    val xSize: Int = scala.math.abs(minPos.x) + scala.math.abs(maxPos.x)
    val ySize: Int = scala.math.abs(minPos.y) + scala.math.abs(maxPos.y)
    return xSize * ySize
  }
}

