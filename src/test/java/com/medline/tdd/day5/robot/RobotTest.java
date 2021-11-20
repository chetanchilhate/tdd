package com.medline.tdd.day5.robot;

import static com.medline.tdd.day5.robot.Move.BACKWARD;
import static com.medline.tdd.day5.robot.Move.FORWARD;
import static com.medline.tdd.day5.robot.Turn.LEFT;
import static com.medline.tdd.day5.robot.Turn.RIGHT;
import static com.medline.tdd.day5.robot.direction.Direction.EAST;
import static com.medline.tdd.day5.robot.direction.Direction.NORTH;
import static com.medline.tdd.day5.robot.direction.Direction.SOUTH;
import static com.medline.tdd.day5.robot.direction.Direction.WEST;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RobotTest {

  @Test
  void robotShouldBeFacingNorthWhenTurnLeftFromEast() {
    final Coordinate coordinate = new Coordinate(0, 0);
    Robot chiti = new Robot(new Position(coordinate, EAST));
    chiti.turn(LEFT);
    assertEquals(NORTH, chiti.getDirection());
  }

  @Test
  void robotShouldBeFacingSouthWhenTurnLeftFromWest() {
    final Coordinate coordinate = new Coordinate(0, 0);
    Robot chiti = new Robot(new Position(coordinate, WEST));
    chiti.turn(LEFT);
    assertEquals(SOUTH, chiti.getDirection());
  }

  @Test
  void robotShouldBeFacingWestWhenTurnLeftFromNorth() {
    final Coordinate coordinate = new Coordinate(0, 0);
    Robot chiti = new Robot(new Position(coordinate, NORTH));
    chiti.turn(LEFT);
    assertEquals(WEST, chiti.getDirection());
  }

  @Test
  void robotShouldBeFacingEastWhenTurnLeftFromSouth() {
    final Coordinate coordinate = new Coordinate(0, 0);
    Robot chiti = new Robot(new Position(coordinate, SOUTH));
    chiti.turn(LEFT);
    assertEquals(EAST, chiti.getDirection());
  }

  @Test
  void robotShouldBeFacingSouthWhenTurnRightFromEast() {
    final Coordinate coordinate = new Coordinate(0, 0);
    Robot chiti = new Robot(new Position(coordinate, EAST));
    chiti.turn(RIGHT);
    assertEquals(SOUTH, chiti.getDirection());
  }

  @Test
  void robotShouldBeFacingNorthWhenTurnRightFromWest() {
    final Coordinate coordinate = new Coordinate(0, 0);
    Robot chiti = new Robot(new Position(coordinate, WEST));
    chiti.turn(RIGHT);
    assertEquals(NORTH, chiti.getDirection());
  }

  @Test
  void robotShouldBeFacingEastWhenTurnRightFromNorth() {
    final Coordinate coordinate = new Coordinate(0, 0);
    Robot chiti = new Robot(new Position(coordinate, NORTH));
    chiti.turn(RIGHT);
    assertEquals(EAST, chiti.getDirection());
  }

  @Test
  void robotShouldBeFacingWestWhenTurnRightFromSouth() {
    final Coordinate coordinate = new Coordinate(0, 0);
    Robot chiti = new Robot(new Position(coordinate, SOUTH));
    chiti.turn(RIGHT);
    assertEquals(WEST, chiti.getDirection());
  }

  @Test
  public void robotShouldMoveOneStepForwardTowardsEast() {
    final Coordinate coordinate = new Coordinate(0, 0);
    Robot chiti = new Robot(new Position(coordinate, EAST));
    chiti.move(FORWARD);
    assertEquals(new Coordinate(1, 0), chiti.getCoordinate());
  }

  @Test
  public void robotShouldMoveOneStepForwardTowardsWest() {
    final Coordinate coordinate = new Coordinate(0, 0);
    Robot chiti = new Robot(new Position(coordinate, WEST));
    chiti.move(FORWARD);
    assertEquals(new Coordinate(-1, 0), chiti.getCoordinate());
  }

  @Test
  public void robotShouldMoveOneStepForwardTowardsNorth() {
    final Coordinate coordinate = new Coordinate(0, 0);
    Robot chiti = new Robot(new Position(coordinate, NORTH));
    chiti.move(FORWARD);
    assertEquals(new Coordinate(0, 1), chiti.getCoordinate());
  }

  @Test
  public void robotShouldMoveOneStepForwardTowardsSouth() {
    final Coordinate coordinate = new Coordinate(0, 0);
    Robot chiti = new Robot(new Position(coordinate, SOUTH));
    chiti.move(FORWARD);
    assertEquals(new Coordinate(0, -1), chiti.getCoordinate());
  }

  @Test
  public void robotShouldMoveOneStepBackwardFromEast() {
    final Coordinate coordinate = new Coordinate(0, 0);
    Robot chiti = new Robot(new Position(coordinate, EAST));
    chiti.move(BACKWARD);
    assertEquals(new Coordinate(-1, 0), chiti.getCoordinate());
  }

  @Test
  public void robotShouldMoveOneStepBackwardFromWest() {
    final Coordinate coordinate = new Coordinate(0, 0);
    Robot chiti = new Robot(new Position(coordinate, WEST));
    chiti.move(BACKWARD);
    assertEquals(new Coordinate(1, 0), chiti.getCoordinate());
  }

  @Test
  public void robotShouldMoveOneStepBackwardFromNorth() {
    final Coordinate coordinate = new Coordinate(0, 0);
    Robot chiti = new Robot(new Position(coordinate, NORTH));
    chiti.move(BACKWARD);
    assertEquals(new Coordinate(0, -1), chiti.getCoordinate());
  }

  @Test
  public void robotShouldMoveOneStepBackwardFromSouth() {
    final Coordinate coordinate = new Coordinate(0, 0);
    Robot chiti = new Robot(new Position(coordinate, SOUTH));
    chiti.move(BACKWARD);
    assertEquals(new Coordinate(0,1), chiti.getCoordinate());
  }

  @Test
  public void robotShouldBeFacingWestOneStepForwardTowardsWest() {
    final Coordinate coordinate = new Coordinate(0, 0);
    Robot chiti = new Robot(new Position(coordinate, NORTH));

    chiti.turn(LEFT);
    chiti.move(FORWARD);

    assertEquals(WEST, chiti.getDirection());
    assertEquals(new Coordinate(-1, 0), chiti.getCoordinate());
  }

  @Test
  @DisplayName("robot should be at position {X: 1, Y: 0, Direction: EAST} after executing L_R_F_B_R_F_R_L")
  public void robotShouldBeFacingEastOneStepForwardTowardsEast() {
    final Coordinate coordinate = new Coordinate(0, 0);
    Robot chiti = new Robot(new Position(coordinate, NORTH));

    chiti.turn(LEFT);
    chiti.turn(RIGHT);
    chiti.move(FORWARD);
    chiti.move(BACKWARD);
    chiti.turn(RIGHT);
    chiti.move(FORWARD);
    chiti.turn(RIGHT);
    chiti.turn(LEFT);

    assertEquals(EAST, chiti.getDirection());
    assertEquals(new Coordinate(1, 0), chiti.getCoordinate());
  }

  @Test
  @DisplayName("robot should be at position {X: 1, Y: -1, Direction: SOUTH} after executing F_R_L_B_R_F")
  public void robotShouldBeFacingSouthOneStepForwardTowardsEastAndSouth() {
    final Coordinate coordinate = new Coordinate(1, 0);
    Robot chiti = new Robot(new Position(coordinate, EAST));

    chiti.move(FORWARD);
    chiti.turn(RIGHT);
    chiti.turn(LEFT);
    chiti.move(BACKWARD);
    chiti.turn(RIGHT);
    chiti.move(FORWARD);

    assertEquals(new Position(new Coordinate(1, -1), SOUTH), chiti.getPosition());
  }

}