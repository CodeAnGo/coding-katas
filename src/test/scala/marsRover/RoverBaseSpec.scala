package marsRover

import base.UnitSpec
import com.sun.xml.internal.ws.dump.LoggingDumpTube.Position
import org.scalatest.{FlatSpec, Matchers}
/**
  * Created by luke on 16/05/17.
  */

/*
Source: http://dallashackclub.com/rover
Develop an api that moves a rover around on a grid.
* You are given the initial starting point (x,y) of a rover and the direction (N,S,E,W) it is facing.
* The rover receives a character array of commands.
* Implement commands that move the rover forward/backward (f,b).
* Implement commands that turn the rover left/right (l,r).
* Implement obstacle detection before each move to a new square.
If a given sequence of commands encounters an obstacle, the rover moves up to the last possible point and reports the obstacle.
*/

class MarsRoverTests extends FlatSpec with Matchers {

  "Planet" can "define its size" in {
    val mars = new Planet(Position(-10,-10), Position(10,10))
    mars.calculateSize should be (400)
    val jupiter = new Planet(Position(-25,-34), Position(50,40))
    jupiter.calculateSize should not be (100)
  }

  "Planet" can "get obstacles" in {
    val mars = new Planet(Position(-10,-10), Position(10,10))
    mars.getObstacles() should be (List.empty[Position])
  }

  "Planet" can "make obstacles" in {
    val mars = new Planet(Position(-10,-10), Position(10,10))
    mars.getObstacles() should be (List.empty[Position])
    val obstacle = mars.makeObstacle( Position(5,5))
    mars.getObstacles().contains(Position(5,5)) should be (true)
  }

  "Rover" can "land on planet" in {
    val RoverOne = new Rover
    RoverOne.getCurrentPosition.x should be (0)
    RoverOne.getCurrentPosition.y should be (0)
    RoverOne.getCurrentDirection() should be ('N')
  }

  "Rover" can "turn on planet" in {
    val RoverOne = new Rover
    val mars = new Planet(Position(-10,-10), Position(10,10))
    RoverOne.getCurrentDirection() should be ('N')
    RoverOne.instructRover(List('S'), mars)
    RoverOne.getCurrentDirection() should be ('S')
  }

  "Rover" can "move on planet" in {
    val RoverOne = new Rover
    val mars = new Planet(Position(-10,-10), Position(10,10))
    RoverOne.getCurrentPosition.x should be (0)
    RoverOne.getCurrentPosition.y should be (0)
    RoverOne.getCurrentDirection() should be ('N')
    RoverOne.instructRover(List('M'), mars)
    RoverOne.getCurrentPosition.x should be (0)
    RoverOne.getCurrentPosition.y should be (1)
    RoverOne.getCurrentDirection() should be ('N')
  }

  "Rover" can "move & turn on planet" in {
    val RoverOne = new Rover
    val mars = new Planet(Position(-10,-10), Position(10,10))
    RoverOne.getCurrentPosition.x should be (0)
    RoverOne.getCurrentPosition.y should be (0)
    RoverOne.getCurrentDirection() should be ('N')
    RoverOne.instructRover(List('M','M','E','M','M','M'), mars)
    RoverOne.getCurrentPosition.x should be (3)
    RoverOne.getCurrentPosition.y should be (2)
    RoverOne.getCurrentDirection() should be ('E')
  }

  "Rover" can "accept and execute orders" in {
    val RoverOne = new Rover
    val mars = new Planet(Position(-10,-10), Position(10,10))
    RoverOne.getCurrentPosition.x should be (0)
    RoverOne.getCurrentPosition.y should be (0)
    RoverOne.getCurrentDirection() should be ('N')
    RoverOne.instructRover(List('E','M','M','N','M'), mars) should be (true)
    RoverOne.getCurrentPosition.x should be (2)
    RoverOne.getCurrentPosition.y should be (1)
    RoverOne.getCurrentDirection() should be ('N')
  }

  "Rover" can "stop executing orders when it hits an obstacle" in {
    val RoverOne = new Rover
    val mars = new Planet(Position(-10,-10), Position(10,10))
    mars.getObstacles() should be (List.empty[Position])
    val obstacle = mars.makeObstacle( Position(0,1))
    mars.getObstacles().contains(Position(0,1)) should be (true)
    RoverOne.getCurrentPosition.x should be (0)
    RoverOne.getCurrentPosition.y should be (0)
    RoverOne.getCurrentDirection() should be ('N')
    RoverOne.instructRover(List('M'), mars) should be (false)
  }
}