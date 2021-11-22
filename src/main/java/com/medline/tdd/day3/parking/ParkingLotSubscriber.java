package com.medline.tdd.day3.parking;

import java.util.List;

public interface ParkingLotSubscriber<T extends Subject > extends ParkingLotObserver {

  default void subscribe() {
    getSubjects().forEach(subject -> subject.addObserver(this));
  }

  default void unsubscribe() {
    getSubjects().forEach(Subject::removeObserver);
  }

  List<T> getSubjects();

}
