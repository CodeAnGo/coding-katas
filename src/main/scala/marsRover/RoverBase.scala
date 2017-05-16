package marsRover
import scala.math
/**
  * Created by luke on 16/05/17.
  */

class Position(var x: Int, var y: Int)

class Obstacle(val location: Position)

class Rover(var location: Position = new Position(0,0), var direction: Char = 'N', var instructions: List[Char] = List.empty[Char]){

  def getCurrentPosition(): Position = location

  def getCurrentDirection(): Char = direction

  private def setDirection(newDirection: Char): Unit =(direction = newDirection)

  private def setPosition(newPosition: Position): Unit =(location = newPosition)

  private def turn(direction: Char): Unit = setDirection(direction)

  private def step(): Unit = {
    getCurrentDirection() match{
      case 'N' => setPosition(new Position(getCurrentPosition().x, getCurrentPosition().y + 1))
      case 'S' => setPosition(new Position(getCurrentPosition().x, getCurrentPosition().y + -1))
      case 'E' => setPosition(new Position(getCurrentPosition().x + 1, getCurrentPosition().y))
      case 'W' => setPosition(new Position(getCurrentPosition().x + -1, getCurrentPosition().y))
    }
  }

  def instructRover(instructions: List[Char]): Boolean = {
    for (i <- instructions.indices) {
      if ("NESW".contains(instructions(i))) {
        setDirection(instructions(i))
      } else if (instructions(i) == 'M') {
        step()
      } else {
        return false
      }
    }
    return true
  }
}

class Planet(val minPos: Position, val maxPos: Position){

  def calculateSize: Int = (scala.math.abs(minPos.x) + scala.math.abs(maxPos.x)) * (scala.math.abs(minPos.y) + scala.math.abs(maxPos.y))

}


