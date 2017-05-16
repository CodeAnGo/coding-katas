# Coding Kata - Mars Rover

An attempt at completing the Mars Rover problem using TDD.

## Information
This project uses:
- Scala 2.11.8
- SBT 0.13.8
- ScalaTest 2.2.6

##Requirements
Develop an api that moves a rover around on a grid.

- You are given the initial starting point (x,y) of a rover and the direction (N,S,E,W) it is facing.
- The rover receives a character array of commands.
- Implement commands that move the rover forward/backward (f,b).
- Implement commands that turn the rover left/right (l,r).
- Implement wrapping from one edge of the grid to another. (planets are spheres after all)
- Implement obstacle detection before each move to a new square. If a given sequence of commands encounters an obstacle, the rover moves up to the last possible point and reports the obstacle.

## Compile instructions
```
sbt clean compile
```

## Test instructions
```
sbt clean test
```
