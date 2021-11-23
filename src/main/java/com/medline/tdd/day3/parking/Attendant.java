package com.medline.tdd.day3.parking;

import com.medline.tdd.day3.parking.parkinglot.ParkingLot;
import com.medline.tdd.day3.parking.parkinglot.ParkingLotFinder;
import com.medline.tdd.day3.parking.parkinglot.ParkingLotStatus;
import com.medline.tdd.day3.parking.parkinglot.ParkingLotSubscriber;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Attendant implements ParkingAttendant, ParkingLotSubscriber<ParkingLot> {

  private final List<ParkingLot> parkingLots;
  private final List<ParkingLot> availableParkingLots;

  public Attendant(final List<ParkingLot> parkingLots) {
    this.parkingLots = Collections.unmodifiableList(parkingLots);
    Predicate<ParkingLot> isParkingAvailable = isParkingLotAvailable();
    this.availableParkingLots = parkingLots.stream().filter(isParkingAvailable).collect(Collectors.toList());
  }

  private Predicate<ParkingLot> isParkingLotAvailable() {
    Predicate<ParkingLot> hasNoObserver = Predicate.not(ParkingLot::hasObserver);
    Predicate<ParkingLot> hasHasObserverAndAvailableParking = parkingLot -> parkingLot.hasObserver() && parkingLot.isAvailable();
    return hasNoObserver.or(hasHasObserverAndAvailableParking);
  }

  @Override
  public void direct(Car car) {
    direct(car, ParkingLotFinder.FIRST);
  }

  public void direct(Car car, ParkingLotFinder parkingLotFinder) {
    Optional<ParkingLot> parkingLotOptional = parkingLotFinder.findParkingLot(this.availableParkingLots);
    if (parkingLotOptional.isEmpty()) {
      throw new RuntimeException("No parking lot available to park a car");
    }
    parkingLotOptional.ifPresent(parkingLot -> parkingLot.park(car));
  }

  @Override
  public boolean canDirect() {
    return this.availableParkingLots.stream().anyMatch(Predicate.not(ParkingLot::isFull));
  }

  @Override
  public void notify(ParkingLotStatus parkingLotStatus, ParkingLot parkingLot) {
    parkingLotStatus.updateAvailableParkingLots(this.availableParkingLots, parkingLot);
  }

  public List<ParkingLot> getAvailableParkingLots() {
    return Collections.unmodifiableList(this.availableParkingLots);
  }

  @Override
  public List<ParkingLot> getSubjects() {
    return parkingLots;
  }

}
