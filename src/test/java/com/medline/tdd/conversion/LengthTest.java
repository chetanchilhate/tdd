package com.medline.tdd.conversion;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LengthTest {

  @Test
  void thousandMetersShouldEqualToAnotherThousandMeters() {

    Length thousandMeters = new Length(1000, Length.Unit.METER);
    Length anotherThousandMeters = new Length(1000, Length.Unit.METER);

    assertEquals(thousandMeters, anotherThousandMeters);
  }

  @Test
  void oneMeterShouldNotEqualTwoMeters() {

    Length oneMeter = new Length(1, Length.Unit.METER);
    Length twoMeters = new Length(2, Length.Unit.METER);

    assertNotEquals(oneMeter, twoMeters);
  }

  @Test
  void hundredCentimetersShouldEqualToOneMeter() {

    Length hundredCentimeters = new Length(100, Length.Unit.CENTIMETER);
    Length oneMeter = new Length(1, Length.Unit.METER);

    assertEquals(hundredCentimeters, oneMeter);
  }

  @Test
  void oneKilometerShouldEqualToOneThousandMeters() {

    Length oneKilometer = new Length(1, Length.Unit.KILOMETER);
    Length thousandMeters = new Length(1000, Length.Unit.METER);

    assertEquals(oneKilometer, thousandMeters);
  }

}