package com.medline.tdd.day3.parking;

import java.util.StringJoiner;

public class Car {

  private final String vehicleNumber;

  public Car(String vehicleNumber) {
    this.vehicleNumber = vehicleNumber;
  }

  public String getVehicleNumber() {
    return vehicleNumber;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Car.class.getSimpleName() + "[", "]")
        .add("vehicleNumber='" + vehicleNumber + "'")
        .toString();
  }
}
