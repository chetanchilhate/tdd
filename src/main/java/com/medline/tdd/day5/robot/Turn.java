package com.medline.tdd.day5.robot;

import com.medline.tdd.day5.robot.direction.Direction;

public interface Turn {

  Turn LEFT = Direction::left;

  Turn RIGHT = Direction::right;

  Direction execute(Direction direction);

}
