package com.medline.tdd.day5.robot.direction;

import com.medline.tdd.day5.robot.Coordinate;

public class North implements Direction {

  @Override
  public Direction left() {
    return Direction.WEST;
  }

  @Override
  public Direction right() {
    return Direction.EAST;
  }

  @Override
  public Coordinate forward(Coordinate coordinate) {
    return coordinate.incrementY();
  }

  @Override
  public Coordinate backward(Coordinate coordinate) {
    return coordinate.decrementY();
  }

  @Override
  public String toString() {
    return this.directionToString();
  }

}
