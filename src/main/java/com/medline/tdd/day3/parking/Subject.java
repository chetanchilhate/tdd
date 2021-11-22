package com.medline.tdd.day3.parking;

public interface Subject {

  void addObserver(ParkingLotObserver parkingLotObserver);

  void removeObserver();
}
