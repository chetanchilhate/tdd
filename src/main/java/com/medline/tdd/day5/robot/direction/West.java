package com.medline.tdd.day5.robot.direction;

import com.medline.tdd.day5.robot.Coordinate;

public class West implements Direction {
  @Override
  public Direction left() {
    return Direction.SOUTH;
  }

  @Override
  public Direction right() {
    return Direction.NORTH;
  }

  @Override
  public Coordinate forward(Coordinate coordinate) {
    return coordinate.decrementX();
  }

  @Override
  public Coordinate backward(Coordinate coordinate) {
    return coordinate.incrementX();
  }

  @Override
  public String toString() {
    return this.directionToString();
  }

}
