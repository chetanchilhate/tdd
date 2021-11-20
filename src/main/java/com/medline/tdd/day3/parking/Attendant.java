package com.medline.tdd.day3.parking;

import java.util.List;
import java.util.Optional;

public class Attendant implements ParkingAttendant {

  private final List<ParkingLot> parkingLots;
  private boolean canDirect;

  public Attendant(List<ParkingLot> parkingLots) {
    this.parkingLots = parkingLots;
  }

  @Override
  public void direct(Car car) {
    direct(car, ParkingLotFinder.FIRST);
  }

  public void direct(Car car, ParkingLotFinder parkingLotFinder) {
    Optional<ParkingLot> parkingLotOptional = parkingLotFinder.findParkingLot(parkingLots);
    if (parkingLotOptional.isEmpty()) {
      throw new RuntimeException("No parking lot available to park a car");
    }
    parkingLotOptional.ifPresent(parkingLot -> {
      parkingLot.park(car);
      canDirect = true;
    });
  }

  @Override
  public boolean canDirect() {
    return canDirect;
  }

}
