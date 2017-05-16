package marsRover
import scala.math
/**
  * Created by luke on 16/05/17.
  */

case class Position(var x: Int = 0, var y: Int = 0)

class Rover(var location: Position = Position(0,0), var direction: Char = Localizer.NORTH, var instructions: List[Char] = List.empty[Char]){
  def getCurrentPosition(): Position = location
  def getCurrentDirection(): Char = direction
  private def setDirection(newDirection: Char): Unit =(direction = newDirection)
  private def setPosition(newPosition: Position): Unit =(location = newPosition)

  private def step(): Position = {
    getCurrentDirection() match{
      case Localizer.NORTH => Position(getCurrentPosition().x, getCurrentPosition().y + 1)
      case Localizer.SOUTH => Position(getCurrentPosition().x, getCurrentPosition().y + -1)
      case Localizer.EAST => Position(getCurrentPosition().x + 1, getCurrentPosition().y)
      case Localizer.WEST => Position(getCurrentPosition().x + -1, getCurrentPosition().y)
    }
  }

  def instructRover(instructions: List[Char], planet: Planet): Boolean = {
    var error = false
    for (i <- instructions.indices) {
      if (Localizer.COMPASS.contains(instructions(i))) {
        setDirection(instructions(i))
        error = false
      } else if (instructions(i) == Localizer.MOVE) {
        if (!planet.getObstacles().contains(step())) { setPosition(step()); error = false } else { error = true }
      } else {
        error = true
      }
    }
    !error
  }
}

class Planet(val minPos: Position, val maxPos: Position, var obstacles: List[Position] = List.empty[Position]){

  def makeObstacle(where: Position): Boolean = {
   if (getObstacles().contains(where)){
     false
   } else {
     obstacles = obstacles ::: List(where)
     true
   }
  }

  def getObstacles(): List[Position] = obstacles
  def calculateSize: Int = (scala.math.abs(minPos.x) + scala.math.abs(maxPos.x)) * (scala.math.abs(minPos.y) + scala.math.abs(maxPos.y))
}


