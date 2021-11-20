package com.medline.tdd.day5.robot.direction;

import com.medline.tdd.day5.robot.Coordinate;

public class South implements Direction {
  @Override
  public Direction left() {
    return Direction.EAST;
  }

  @Override
  public Direction right() {
    return Direction.WEST;
  }

  @Override
  public Coordinate forward(Coordinate coordinate) {
    return coordinate.decrementY();
  }

  @Override
  public Coordinate backward(Coordinate coordinate) {
    return coordinate.incrementY();
  }

  @Override
  public String toString() {
    return this.directionToString();
  }

}
