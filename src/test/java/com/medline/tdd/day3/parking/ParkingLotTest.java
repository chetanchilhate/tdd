package com.medline.tdd.day3.parking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
  public void notifyParkingFullWhenParkingLotIsFull() {
    ParkingLotObserver parkingLotObserver = mock(ParkingLotObserver.class);
    ParkingLot parkingLot = new ParkingLot(2);
    parkingLot.addObserver(parkingLotObserver);

    Car maruti = new Car("V1");
    parkingLot.park(maruti);

    Car bmw = new Car("V2");
    parkingLot.park(bmw);
    verify(parkingLotObserver, times(1)).notify(ParkingLotStatus.FULL, parkingLot);
  }

  @Test
  public void notifyParkingAvailableAfterParkingBecomesAvailable() {
    ParkingLotObserver parkingLotObserver = mock(ParkingLotObserver.class);

    ParkingLot parkingLot = new ParkingLot(1);
    parkingLot.addObserver(parkingLotObserver);

    Car maruti = new Car("V1");
    parkingLot.park(maruti);

    assertEquals(maruti, parkingLot.unpark(maruti.getVehicleNumber()));
    verify(parkingLotObserver, times(1)).notify(ParkingLotStatus.AVAILABLE, parkingLot);
  }

  @Test
  public void notifyParkingIsFullOnlyOnceWhenMultipleCarsComeToPark() {
    ParkingLotObserver parkingLotObserver = mock(ParkingLotObserver.class);
    ParkingLot parkingLot = new ParkingLot(2);
    parkingLot.addObserver(parkingLotObserver);

    Car maruti = new Car("V1");
    parkingLot.park(maruti);

    Car nexon = new Car("V2");
    parkingLot.park(nexon);

    Car bmw = new Car("V3");
    Car jaguar = new Car("V4");

    assertFalse(parkingLot.park(bmw));
    assertFalse(parkingLot.park(jaguar));

    verify(parkingLotObserver, times(1)).notify(ParkingLotStatus.FULL, parkingLot);
  }

}
