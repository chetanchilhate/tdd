package com.medline.tdd.day5.robot;

import static java.lang.String.join;
import static java.util.Arrays.asList;

import com.medline.tdd.day5.robot.direction.Direction;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/*
Design a system for robot, which are capable of acting on the following instructions:
1. L: Change direction to left.
2. R: Change direction to the right.
3. F: Move one step forward.
4. B: Move one step backward.

Example
1: Suppose a robot is initially placed on the plane at {X: 0, Y: 0} coordinates and
facing North cardinal direction, following instructions are provided to the robot

Input: LRFBRFRL
Output: The robot is at {X: 1, Y: 0}, facing EAST direction.

Input: FRLBRF
Output: The robot is at {X: 1, Y: -1}, facing SOUTH direction.

Note: Every new input should consider the last position of the robot.

 */
public class App {

  public static void main(String[] args) {

    final Map<String, Instruction> instructionMap = createInstructionMap();

    final List<List<String>> inputs = asList(asList("LRFBRFRL".split("")), asList("FRLBRF".split("")));

    final Robot chiti = new Robot(new Position(new Coordinate(0, 0), Direction.NORTH));

    System.out.println("Initial : " + chiti.getPosition());

    for (List<String> input : inputs) {

      System.out.print("Input   : ".concat(join("", input)).concat("\n"));

      input.stream()
           .map(instructionMap::get)
           .filter(Objects::nonNull)
           .forEach(instruction -> instruction.accept(chiti));

      System.out.println("Output  : " + chiti.getPosition());
    }

  }

  private static Map<String, Instruction> createInstructionMap() {
    return Map.of("L", Instruction.TURN_LEFT,
        "R", Instruction.TURN_RIGHT,
        "F", Instruction.MOVE_FORWARD,
        "B", Instruction.MOVE_BACKWARD);
  }

}
