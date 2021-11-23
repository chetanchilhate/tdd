package com.medline.tdd.day3.parking.parkinglot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.medline.tdd.day3.parking.Car;
import org.junit.jupiter.api.Test;

class ParkingLotTest {

  @Test
  void parkOneCarInEmptyParkingLot() {
    ParkingLot parkingLot = new ParkingLot(4);

    Car maruti = new Car("V1");
    boolean parked = parkingLot.park(maruti);
    assertTrue(parked);
  }

  @Test
  void unableToParkCarInFullParkingLot() {
    ParkingLot parkingLot = new ParkingLot(1);

    Car maruti = new Car("V1");
    boolean parked = parkingLot.park(maruti);
    assertTrue(parked);

    Car bmw = new Car("V2");
    boolean bmwParked = parkingLot.park(bmw);
    assertFalse(bmwParked);
  }

  @Test
  void unparkAlreadyParkedCar() {
    ParkingLot parkingLot = new ParkingLot(5);

    Car maruti = new Car("V1");
    parkingLot.park(maruti);

    assertEquals(maruti, parkingLot.unpark(maruti.getVehicleNumber()));
  }

  @Test
  void unableToUnparkFromEmptyParkingLot() {
    ParkingLot parkingLot = new ParkingLot(5);
    assertThrows(RuntimeException.class, () -> parkingLot.unpark("V1"),
        "Car with provided VehicleNumber does not exists in the ParkingLot");
  }

  @Test
  void unparkBMWSuccessfullyFromFullParkingLot() {
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
  void shouldNotBeAbleToParkSameCarTwice() {
    ParkingLot parkingLot = new ParkingLot(3);

    Car maruti = new Car("V1");
    parkingLot.park(maruti);
    assertFalse(parkingLot.park(maruti));
  }

  @Test
  void unableToUnparkSameCarTwice() {
    ParkingLot parkingLot = new ParkingLot(3);

    Car maruti = new Car("V1");
    parkingLot.park(maruti);
    parkingLot.unpark(maruti.getVehicleNumber());

    String vehicleNumber = maruti.getVehicleNumber();

    assertThrows(RuntimeException.class, () -> parkingLot.unpark(vehicleNumber),
        "Car with provided VehicleNumber does not exists in the ParkingLot");
  }

  @Test
  void notifyParkingFullWhenParkingLotIsFull() {
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
  void notifyParkingAvailableAfterParkingBecomesAvailable() {
    ParkingLotObserver parkingLotObserver = mock(ParkingLotObserver.class);

    ParkingLot parkingLot = new ParkingLot(1);
    parkingLot.addObserver(parkingLotObserver);

    Car maruti = new Car("V1");
    parkingLot.park(maruti);

    assertEquals(maruti, parkingLot.unpark(maruti.getVehicleNumber()));
    verify(parkingLotObserver, times(1)).notify(ParkingLotStatus.AVAILABLE, parkingLot);
  }

  @Test
  void notifyParkingIsFullOnlyOnceWhenMultipleCarsComeToPark() {
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
