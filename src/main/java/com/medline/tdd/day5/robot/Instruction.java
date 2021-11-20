package com.medline.tdd.day5.robot;

import static com.medline.tdd.day5.robot.Move.BACKWARD;
import static com.medline.tdd.day5.robot.Move.FORWARD;
import static com.medline.tdd.day5.robot.Turn.LEFT;
import static com.medline.tdd.day5.robot.Turn.RIGHT;

public interface Instruction {

  Instruction TURN_LEFT = robot -> robot.turn(LEFT);

  Instruction TURN_RIGHT = robot -> robot.turn(RIGHT);

  Instruction MOVE_FORWARD = robot -> robot.move(FORWARD);

  Instruction MOVE_BACKWARD = robot -> robot.move(BACKWARD);

  void accept(Robot robot);

}
