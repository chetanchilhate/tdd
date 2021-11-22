package com.medline.tdd.day3.parking;

import java.util.List;

public interface ParkingLotStatus {

  ParkingLotStatus FULL = List::remove;
  ParkingLotStatus AVAILABLE = List::add;

  void updateAvailableParkingLots(List<ParkingLot> availableParkingLots, ParkingLot parkingLot);

}
