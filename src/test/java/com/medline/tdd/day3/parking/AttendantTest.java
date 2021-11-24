package com.medline.tdd.day3.parking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.medline.tdd.day3.parking.parkinglot.ParkingLot;
import com.medline.tdd.day3.parking.parkinglot.ParkingLotFinder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

class AttendantTest {

  @Test
  void parkCarToFirstAvailableParkingLot() {

    //arrange
    List<ParkingLot> parkingLots = new ArrayList<>();

    ParkingLot groundParking = spy(new ParkingLot(10));
    ParkingLot firstFloorParking = new ParkingLot(15);

    parkingLots.add(groundParking);
    parkingLots.add(firstFloorParking);

    Attendant attendant = new Attendant(parkingLots);

    Car bmw = new Car("V1");

    //act
    attendant.direct(bmw);

    //assert
    verify(groundParking, times(1)).park(bmw);
  }

  @Test
  void parkCarToSecondAvailableParkingLotWhenFirstIsFull() {

    //arrange
    List<ParkingLot> parkingLots = new ArrayList<>();

    ParkingLot groundParking = new ParkingLot(1);
    groundParking.park(new Car("V1"));
    ParkingLot firstFloorParking = spy(new ParkingLot(2));

    parkingLots.add(groundParking);
    parkingLots.add(firstFloorParking);

    Attendant attendant = new Attendant(parkingLots);

    Car bmw = new Car("V1");

    //act
    attendant.direct(bmw);

    //assert
    verify(firstFloorParking, times(1)).park(bmw);
  }

  @Test
  void parkMultipleCarToFirstAvailableParkingLot() {

    //arrange
    List<ParkingLot> parkingLots = new ArrayList<>();

    ParkingLot groundParking = spy(new ParkingLot(10));
    ParkingLot firstFloorParking = new ParkingLot(15);

    parkingLots.add(groundParking);
    parkingLots.add(firstFloorParking);

    Attendant attendant = new Attendant(parkingLots);

    Car bmw = new Car("V1");
    Car bentley = new Car("V2");

    //act
    attendant.direct(bmw);
    attendant.direct(bentley);

    //assert
    verify(groundParking, times(1)).park(bmw);
    verify(groundParking, times(1)).park(bentley);

  }

  @Test
  void parkCarToParkingLotWithMaxSpaces() {

    //arrange
    List<ParkingLot> parkingLots = new ArrayList<>();

    ParkingLot groundParking = new ParkingLot(1);
    ParkingLot firstFloorParking = spy(new ParkingLot(2));

    parkingLots.add(groundParking);
    parkingLots.add(firstFloorParking);

    Attendant attendant = new Attendant(parkingLots);

    Car bmw = new Car("V1");

    //act
    attendant.direct(bmw, ParkingLotFinder.MAX);

    //assert
    verify(firstFloorParking, times(1)).park(bmw);
  }

  @Test
  void parkCarToParkingLotWithMinSpaces() {

    //arrange
    List<ParkingLot> parkingLots = new ArrayList<>();

    ParkingLot groundParking = spy(new ParkingLot(1));
    ParkingLot firstFloorParking = new ParkingLot(2);

    parkingLots.add(groundParking);
    parkingLots.add(firstFloorParking);

    Attendant attendant = new Attendant(parkingLots);

    Car bmw = new Car("V1");

    //act
    attendant.direct(bmw, ParkingLotFinder.MIN);

    //assert
    verify(groundParking, times(1)).park(bmw);
  }

  @Test
  void unableToParkCarWhenAllParkingLotAreFull() {

    //arrange
    List<ParkingLot> parkingLots = new ArrayList<>();

    ParkingLot groundParking = new ParkingLot(1);
    groundParking.park(new Car("V1"));
    ParkingLot firstFloorParking = new ParkingLot(1);
    firstFloorParking.park(new Car("V2"));

    parkingLots.add(groundParking);
    parkingLots.add(firstFloorParking);

    Attendant attendant = new Attendant(parkingLots);

    Car bmw = new Car("V3");

    //act, assert
    assertThrows(RuntimeException.class, () -> attendant.direct(bmw), "No parking lot available to park a car");
    assertFalse(attendant.canDirect());
  }

  @Test
  void availableParkingLotsShouldContainGroundParking() {

    //arrange
    List<ParkingLot> parkingLots = new ArrayList<>();

    ParkingLot groundParking = new ParkingLot(1);
    parkingLots.add(groundParking);

    //act
    Attendant attendant = new Attendant(parkingLots);

    //assert
    assertTrue(attendant.getAvailableParkingLots().contains(groundParking));
  }

  @Test
  void availableParkingLotsShouldNotContainGroundParking() {

    //arrange
    List<ParkingLot> parkingLots = new ArrayList<>();

    ParkingLot groundParking = new ParkingLot(1);
    parkingLots.add(groundParking);

    Attendant attendant = new Attendant(parkingLots);

    Car bmw = new Car("V1");

    //act
    attendant.subscribe();
    groundParking.park(bmw);

    //assert
    assertFalse(attendant.getAvailableParkingLots().contains(groundParking));
  }

  @Test
  void availableParkingLotsShouldContainGroundParkingWhenCarParkedAfterUnsubscribe() {

    //arrange
    List<ParkingLot> parkingLots = new ArrayList<>();

    ParkingLot groundParking = new ParkingLot(1);
    parkingLots.add(groundParking);

    Attendant attendant = new Attendant(parkingLots);

    Car bmw = new Car("V1");

    attendant.subscribe();
    attendant.direct(bmw);

    groundParking.unpark(bmw.getVehicleNumber());

    //act
    attendant.unsubscribe();
    attendant.direct(bmw);

    //assert
    assertTrue(attendant.getAvailableParkingLots().contains(groundParking));
  }

  @Test
  void availableParkingLotsShouldContainGroundParkingAfterParkingBecomesAvailable() {

    //arrange
    List<ParkingLot> parkingLots = new ArrayList<>();

    ParkingLot groundParking = new ParkingLot(1);
    parkingLots.add(groundParking);

    Car bmw = new Car("V1");
    groundParking.park(bmw);

    Attendant attendant = new Attendant(parkingLots);

    //act
    attendant.subscribe();
    groundParking.unpark(bmw.getVehicleNumber());

    //assert
    assertTrue(attendant.getAvailableParkingLots().contains(groundParking));
  }

  @Test
  void notifyAttendantObserversThatAttendantIsNotAvailableToDirectCars() {

    Car bmw = new Car("V1");
    ParkingLot groundFloorParking = new ParkingLot(1);

    Attendant jhon = new Attendant(List.of(groundFloorParking));
    jhon.subscribe();

    AttendantObserver attendantObserver = mock(AttendantObserver.class);
    jhon.addObserver(attendantObserver);

    jhon.direct(bmw);

    verify(attendantObserver, times(1)).notify(AttendantStatus.FULL);
  }

  @Test
  void efficiencyTest() {

    List<Car> cars = new ArrayList<>();

    IntStream.range(1, 11).forEach(i -> cars.add(new Car("V" + i)));

    List<ParkingLot> parkingLots = new ArrayList<>();
    IntStream.iterate(0, n -> n + 2)
             .limit(5)
             .forEach(i -> {
               ParkingLot parkingLot = new ParkingLot(2);
               parkingLot.park(cars.get(i));
               parkingLot.park(cars.get(i + 1));
               parkingLots.add(parkingLot);
             });

    ParkingLot parkingLot = parkingLots.get(4);
    parkingLot.unpark(cars.get(8).getVehicleNumber());
    parkingLot.unpark(cars.get(9).getVehicleNumber());
    Attendant attendant = new Attendant(parkingLots);

    attendant.direct(cars.get(8));
    attendant.direct(cars.get(9));

  }

}
