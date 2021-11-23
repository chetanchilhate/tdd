package com.medline.tdd.day3.parking.parkinglot;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface ParkingLotFinder {

  ParkingLotFinder FIRST = parkingLots -> parkingLots.stream().filter(Predicate.not(ParkingLot::isFull)).findFirst();
  ParkingLotFinder MAX = parkingLots -> parkingLots.stream().filter(Predicate.not(ParkingLot::isFull)).max(Comparator.comparing(ParkingLot::getAvailableSpaces));
  ParkingLotFinder MIN = parkingLots -> parkingLots.stream().filter(Predicate.not(ParkingLot::isFull)).min(Comparator.comparing(ParkingLot::getAvailableSpaces));

  Optional<ParkingLot> findParkingLot(List<ParkingLot> parkingLots);

}

