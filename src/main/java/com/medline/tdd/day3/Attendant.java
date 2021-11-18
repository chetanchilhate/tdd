package com.medline.tdd.day3;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class Attendant {

  private final List<ParkingLot> parkingLots;

  public Attendant(List<ParkingLot> parkingLots) {
    this.parkingLots = parkingLots;
  }

  public void direct(Car car) {
    direct(car, ParkingLotSearchStrategy.FIRST);
  }

  public void direct(Car car, ParkingLotSearchStrategy parkingLotSearchStrategy) {

    Optional<ParkingLot> parkingLotOptional = parkingLotSearchStrategy.findParkingLot(parkingLots);
    if(parkingLotOptional.isEmpty()) {
      throw new RuntimeException("No parking lot available to park a car");
    }
    parkingLotOptional.ifPresent(parkingLot -> parkingLot.park(car));
  }

}
