package com.medline.tdd.day3;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

public class ParkingLotTest {

  @Test
  public void parkOneCarInEmptyParkingLot() {
    ParkingLot parkingLot = new ParkingLot(4);

    Car maruti = new Car("V1");
    boolean parked = parkingLot.park(maruti);
    assertTrue(parked);
  }

  @Test
  public void unableToParkCarInFullParkingLot() {
    ParkingLot parkingLot = new ParkingLot(1);

    Car maruti = new Car("V1");
    boolean parked = parkingLot.park(maruti);
    assertTrue(parked);

    Car bmw = new Car("V2");
    boolean bmwParked = parkingLot.park(bmw);
    assertFalse(bmwParked);
  }

  @Test
  public void unparkAlreadyParkedCar() {
    ParkingLot parkingLot = new ParkingLot(5);

    Car maruti = new Car("V1");
    parkingLot.park(maruti);

    assertEquals(maruti, parkingLot.unpark(maruti.getVehicleNumber()));
  }

  @Test
  public void unableToUnparkFromEmptyParkingLot() {
    ParkingLot parkingLot = new ParkingLot(5);
    assertThrows(RuntimeException.class, () -> parkingLot.unpark("V1"),
        "Car with provided VehicleNumber does not exists in the ParkingLot");
  }

  @Test
  public void unparkBMWSuccessfullyFromFullParkingLot() {
    ParkingLot parkingLot = new ParkingLot(3);

    Car maruti = new Car("V1");
    parkingLot.park(maruti);

    Car bmw = new Car("V2");
    parkingLot.park(bmw);

    Car nexon = new Car("V3");
    parkingLot.park(nexon);

    assertEquals(bmw, parkingLot.unpark(bmw.getVehicleNumber()));
  }

  @Test
  public void shouldNotBeAbleToParkSameCarTwice() {
    ParkingLot parkingLot = new ParkingLot(3);

    Car maruti = new Car("V1");
    parkingLot.park(maruti);
    assertFalse(parkingLot.park(maruti));
  }

  @Test
  public void unableToUnparkSameCarTwice() {
    ParkingLot parkingLot = new ParkingLot(3);

    Car maruti = new Car("V1");
    parkingLot.park(maruti);
    parkingLot.unpark(maruti.getVehicleNumber());
    assertThrows(RuntimeException.class, () -> parkingLot.unpark(maruti.getVehicleNumber()),
        "Car with provided VehicleNumber does not exists in the ParkingLot");
  }

  @Test
  public void displayParkingFullWhenParkingLotIsFull() {
    ParkigLotObserver parkigLotObserver = mock(ParkigLotObserver.class);
    ParkingLot parkingLot = new ParkingLot(2);
    parkingLot.addObserver(parkigLotObserver);

    Car maruti = new Car("V1");
    parkingLot.park(maruti);

    Car bmw = new Car("V2");
    parkingLot.park(bmw);

    verify(parkigLotObserver, times(1)).notify(true);
  }

  @Test
  public void displayParkingFullWhenParkingLotIsFull_V2() {
    ParkigLotObserver parkigLotObserver = mock(ParkigLotObserver.class);
    ParkingLot parkingLot = new ParkingLot(2);
    parkingLot.addObserver(parkigLotObserver);

    Car maruti = new Car("V1");
    parkingLot.park(maruti);

    Car bmw = new Car("V2");
    parkingLot.park(bmw);

    verify(parkigLotObserver, times(1)).notify(true);
  }

  @Test
  public void displayParkingAvailableAfterParkingBecomesAvailable() {
    ParkigLotObserver parkigLotObserver = mock(ParkigLotObserver.class);

    ParkingLot parkingLot = new ParkingLot(1);
    parkingLot.addObserver(parkigLotObserver);

    Car maruti = new Car("V1");
    parkingLot.park(maruti);

    assertEquals(maruti, parkingLot.unpark(maruti.getVehicleNumber()));
    verify(parkigLotObserver, times(1)).notify(false);
  }

  @Test
  public void displayParkingFullAfterParkingLotIsFull() {
    ParkigLotObserver parkigLotObserver = mock(ParkigLotObserver.class);
    ParkingLot parkingLot = new ParkingLot(2);
    parkingLot.addObserver(parkigLotObserver);

    Car maruti = new Car("V1");
    parkingLot.park(maruti);

    Car nexon = new Car("V2");
    parkingLot.park(nexon);

    Car bmw = new Car("V3");
    assertFalse(parkingLot.park(bmw));

    verify(parkigLotObserver, times(1)).notify(true);
  }

}
