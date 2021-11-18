package com.medline.tdd.day3;

public class Coordinator {

  private final Attendant attendant;

  public Coordinator(Attendant attendant) {

    this.attendant = attendant;
  }

  public void direct(Car car) {
    attendant.direct(car);
  }

}
