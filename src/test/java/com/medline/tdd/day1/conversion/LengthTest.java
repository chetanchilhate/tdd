package com.medline.tdd.day1.conversion;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LengthTest {

  @Test
  void thousandMetersShouldEqualToAnotherThousandMeters() {

    //arrange
    Length thousandMeters = new Length(1000, Length.Unit.METER);
    Length anotherThousandMeters = new Length(1000, Length.Unit.METER);

    //assert
    assertEquals(thousandMeters, anotherThousandMeters);
  }

  @Test
  void oneMeterShouldNotEqualTwoMeters() {

    //arrange
    Length oneMeter = new Length(1, Length.Unit.METER);
    Length twoMeters = new Length(2, Length.Unit.METER);

    //assert
    assertNotEquals(oneMeter, twoMeters);
  }

  @Test
  void hundredCentimetersShouldEqualToOneMeter() {

    //arrange
    Length hundredCentimeters = new Length(100, Length.Unit.CENTIMETER);
    Length oneMeter = new Length(1, Length.Unit.METER);

    //assert
    assertEquals(hundredCentimeters, oneMeter);
  }

  @Test
  void oneKilometerShouldEqualToOneThousandMeters() {

    //arrange
    Length oneKilometer = new Length(1, Length.Unit.KILOMETER);
    Length thousandMeters = new Length(1000, Length.Unit.METER);

    //assert
    assertEquals(oneKilometer, thousandMeters);
  }

}