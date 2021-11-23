package com.medline.tdd.day3.parking.parkinglot;

import java.util.List;

public interface ParkingLotSubscriber<T extends ParkingLotSubject > extends ParkingLotObserver {

  default void subscribe() {
    getSubjects().forEach(subject -> subject.addObserver(this));
  }

  default void unsubscribe() {
    getSubjects().forEach(ParkingLotSubject::removeObserver);
  }

  List<T> getSubjects();

}
