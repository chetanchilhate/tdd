package com.medline.tdd.day5.robot;

import com.medline.tdd.day5.robot.direction.Direction;

public class Robot {

  private Position position;

  public Robot(Position position) {
    this.position = position;
  }

  public Direction getDirection() {
    return this.position.getDirection();
  }

  public Coordinate getCoordinate() {
    return this.position.getCoordinate();
  }

  public Position getPosition() {
    return this.position;
  }

  public void move(Move move) {
    this.position = new Position(move.execute(getDirection(), getCoordinate()), getDirection());
  }

  public void turn(Turn turn) {
    this.position = new Position(getCoordinate(), turn.execute(getDirection()));
  }

}
