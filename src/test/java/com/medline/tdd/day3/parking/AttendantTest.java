package com.medline.tdd.day3.parking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.medline.tdd.day3.parking.Attendant;
import com.medline.tdd.day3.parking.Car;
import com.medline.tdd.day3.parking.ParkingLot;
import com.medline.tdd.day3.parking.ParkingLotFinder;
import java.util.ArrayList;
import java.util.List;
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
    assertThrows(RuntimeException.class, () -> attendant.direct(bmw, ParkingLotFinder.FIRST), "No parking lot available to park a car");
    assertFalse(attendant.canDirect());
  }

}
