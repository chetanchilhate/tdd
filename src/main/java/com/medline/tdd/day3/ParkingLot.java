package com.medline.tdd.day3;

import static java.util.Optional.ofNullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ParkingLot {

  private final int capacity;
  private final Map<String, Car> parkingMap;
  private ParkigLotObserver parkigLotObserver;

  public ParkingLot(int capacity) {
    this.capacity = capacity;
    parkingMap = new HashMap<>(capacity);
  }

  public boolean park(Car car) {
    if (isFull() || isParked(car.getVehicleNumber())) {
      return false;
    }
    parkingMap.put(car.getVehicleNumber(), car);
    if(isFull() && ofNullable(parkigLotObserver).isPresent()) {
      parkigLotObserver.notify(true);
    }
    return true;
  }

  private boolean isFull() {
    return parkingMap.size() == capacity;
  }

  public Car unpark(String vehicleNumber) {
    if (!isParked(vehicleNumber)) {
      throw new RuntimeException("Car with provided VehicleNumber does not exists in the ParkingLot");
    }

    if(isFull() && ofNullable(parkigLotObserver).isPresent()) {
      parkigLotObserver.notify(false);
    }
    return parkingMap.remove(vehicleNumber);
  }

  private boolean isParked(String vehicleNumber) {
    return parkingMap.containsKey(vehicleNumber);
  }

  public void addObserver(ParkigLotObserver parkigLotObserver) {
    this.parkigLotObserver = parkigLotObserver;
  }
}
