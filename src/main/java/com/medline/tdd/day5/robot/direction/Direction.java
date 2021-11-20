package com.medline.tdd.day5.robot.direction;

import com.medline.tdd.day5.robot.Coordinate;

public interface Direction {

  Direction EAST = new East();
  Direction WEST = new West();
  Direction NORTH = new North();
  Direction SOUTH = new South();

  Direction left();

  Direction right();

  Coordinate forward(Coordinate coordinate);

  Coordinate backward(Coordinate coordinate);

  default String directionToString() {
    return this.getClass().getSimpleName().toUpperCase();
  }

}
