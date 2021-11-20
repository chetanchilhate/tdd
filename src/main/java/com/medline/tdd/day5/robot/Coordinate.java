package com.medline.tdd.day5.robot;

import java.util.StringJoiner;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Coordinate {
  private final int x;
  private final int y;

  public Coordinate(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public Coordinate incrementX() {
    return new Coordinate(this.x + 1, this.y);
  }

  public Coordinate incrementY() {
    return new Coordinate(this.x, this.y + 1);
  }

  public Coordinate decrementX() {
    return new Coordinate(this.x - 1, this.y);
  }

  public Coordinate decrementY() {
    return new Coordinate(this.x, this.y - 1);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;

    if (o == null || getClass() != o.getClass()) return false;

    Coordinate that = (Coordinate) o;

    return new EqualsBuilder().append(x, that.x).append(y, that.y).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(x).append(y).toHashCode();
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Coordinate.class.getSimpleName() + "[", "]")
        .add("x=" + x)
        .add("y=" + y)
        .toString();
  }
}
