package com.medline.tdd.day3;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface ParkingLotSearchStrategy {

  ParkingLotSearchStrategy FIRST = parkingLots -> parkingLots.stream().filter(Predicate.not(ParkingLot::isFull)).findFirst();
  ParkingLotSearchStrategy MAX = parkingLots -> parkingLots.stream().filter(Predicate.not(ParkingLot::isFull)).max(Comparator.comparing(ParkingLot::getAvailableSpaces));
  ParkingLotSearchStrategy MIN = parkingLots -> parkingLots.stream().filter(Predicate.not(ParkingLot::isFull)).min(Comparator.comparing(ParkingLot::getAvailableSpaces));

  Optional<ParkingLot> findParkingLot(List<ParkingLot> parkingLots);

}

