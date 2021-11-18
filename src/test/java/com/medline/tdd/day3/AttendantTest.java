package com.medline.tdd.day3;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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

    attendant.direct(bmw);

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

    attendant.direct(bmw);

    verify(firstFloorParking, times(1)).park(bmw);
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

    attendant.direct(bmw, ParkingLotSearchStrategy.MAX);

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

    attendant.direct(bmw, ParkingLotSearchStrategy.MIN);

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

    assertThrows(RuntimeException.class, () -> attendant.direct(bmw, ParkingLotSearchStrategy.FIRST));
  }

}
