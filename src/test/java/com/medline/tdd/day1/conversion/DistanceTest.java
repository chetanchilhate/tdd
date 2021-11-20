package com.medline.tdd.day1.conversion;

import static org.junit.jupiter.api.Assertions.*;

import com.medline.tdd.day1.conversion.Distance.Unit;
import org.junit.jupiter.api.Test;

class DistanceTest {

  @Test
  void thousandMetersShouldEqualToAnotherThousandMeters() {

    //arrange
    Distance thousandMeters = new Distance(1000, Unit.METER);
    Distance anotherThousandMeters = new Distance(1000, Unit.METER);

    //assert
    assertEquals(thousandMeters, anotherThousandMeters);
  }

  @Test
  void oneMeterShouldNotEqualTwoMeters() {

    //arrange
    Distance oneMeter = new Distance(1, Unit.METER);
    Distance twoMeters = new Distance(2, Unit.METER);

    //assert
    assertNotEquals(oneMeter, twoMeters);
  }

  @Test
  void hundredCentimetersShouldEqualToOneMeter() {

    //arrange
    Distance hundredCentimeters = new Distance(100, Unit.CENTIMETER);
    Distance oneMeter = new Distance(1, Unit.METER);

    //assert
    assertEquals(hundredCentimeters, oneMeter);
  }

  @Test
  void oneKilometerShouldEqualToOneThousandMeters() {

    //arrange
    Distance oneKilometer = new Distance(1, Unit.KILOMETER);
    Distance thousandMeters = new Distance(1000, Unit.METER);

    //assert
    assertEquals(oneKilometer, thousandMeters);
  }

  @Test
  void oneMeterPlusOneMeterShouldEqualToTwoMeters() {

    //arrange
    Distance oneMeter = new Distance(1, Unit.METER);
    Distance anotherOneMeter = new Distance(1, Unit.METER);
    Distance twoMeters = new Distance(2, Unit.METER);

    //act
    Distance sum = oneMeter.addLength(anotherOneMeter);

    //assert
    assertEquals(twoMeters, sum);
  }

  @Test
  void fiveHundredMetersPlusOneKilometerShouldEqualToFifteenHundredMeters() {

    //arrange
    Distance fiveHundredMeters = new Distance(500, Unit.METER);
    Distance oneKilometer = new Distance(1, Unit.KILOMETER);
    Distance fifteenHundredMeters = new Distance(1500, Unit.METER);

    //act
    Distance sum = fiveHundredMeters.addLength(oneKilometer);

    //assert
    assertEquals(fifteenHundredMeters, sum);
  }

  @Test
  void oneKilometerPlusThreeHundredMetersShouldEqualToOnePointThreeKilometers() {

    //arrange
    Distance oneKilometer = new Distance(1, Unit.KILOMETER);
    Distance threeHundredMeters = new Distance(300, Unit.METER);
    Distance onePointThreeKilometers = new Distance(1.3, Unit.KILOMETER);

    //act
    Distance sum = oneKilometer.addLength(threeHundredMeters);

    //assert
    assertEquals(onePointThreeKilometers, sum);
  }

}