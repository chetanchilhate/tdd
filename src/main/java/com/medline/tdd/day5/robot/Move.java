package com.medline.tdd.day5.robot;

import com.medline.tdd.day5.robot.direction.Direction;

public interface Move {

  Move FORWARD = Direction::forward;

  Move BACKWARD = Direction::backward;

  Coordinate execute(Direction direction, Coordinate coordinate);

}
