package com.medline.tdd.day3.parking;

public interface ParkingLotObserver {

  void notify(ParkingLotStatus parkingLotStatus, ParkingLot parkingLot);

}
