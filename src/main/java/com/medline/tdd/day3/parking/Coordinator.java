package com.medline.tdd.day3.parking;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Coordinator implements ParkingAttendant {

  private final List<ParkingAttendant> parkingAttendants;

  public Coordinator(final List<ParkingAttendant> parkingAttendants) {
    this.parkingAttendants = Collections.unmodifiableList(parkingAttendants);
  }

  @Override
  public void direct(Car car) {
    Optional<ParkingAttendant> parkingAttendantOptional = parkingAttendants.stream()
                                                                           .filter(ParkingAttendant::canDirect)
                                                                           .findFirst();
    parkingAttendantOptional.ifPresent(parkingAttendant -> {
      parkingAttendant.direct(car);
    });
  }

  @Override
  public boolean canDirect() {
    return parkingAttendants.stream().anyMatch(ParkingAttendant::canDirect);
  }

}
