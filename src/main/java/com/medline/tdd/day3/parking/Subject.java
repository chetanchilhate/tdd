package com.medline.tdd.day3.parking;

public interface Subject<T> {

  void addObserver(T subjectObserver);

  void removeObserver();
}
