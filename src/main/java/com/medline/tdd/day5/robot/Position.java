package com.medline.tdd.day5.robot;

import com.medline.tdd.day5.robot.direction.Direction;
import java.util.StringJoiner;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Position {

  private final Coordinate coordinate;
  private final Direction direction;

  public Position(Coordinate coordinate, Direction direction) {
    this.coordinate = coordinate;
    this.direction = direction;
  }

  public Direction getDirection() {
    return direction;
  }

  public Coordinate getCoordinate() {
    return coordinate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;

    if (o == null || getClass() != o.getClass()) return false;

    Position position = (Position) o;

    return new EqualsBuilder().append(coordinate, position.coordinate)
                              .append(direction, position.direction)
                              .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(coordinate)
        .append(direction)
        .toHashCode();
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Position.class.getSimpleName() + "[", "]")
        .add("coordinate=" + coordinate)
        .add("direction=" + direction)
        .toString();
  }
}
