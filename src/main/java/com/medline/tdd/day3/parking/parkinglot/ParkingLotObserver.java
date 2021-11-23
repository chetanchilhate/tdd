package com.medline.tdd.day3.parking.parkinglot;

public interface ParkingLotObserver {

  void notify(ParkingLotStatus parkingLotStatus, ParkingLot parkingLot);

}
