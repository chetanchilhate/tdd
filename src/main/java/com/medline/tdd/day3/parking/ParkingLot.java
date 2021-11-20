package com.medline.tdd.day3.parking;

import static java.util.Optional.ofNullable;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {

  private final int capacity;
  private final Map<String, Car> parkingMap;
  private ParkingLotObserver parkingLotObserver;

  public ParkingLot(int capacity) {
    this.capacity = capacity;
    parkingMap = new HashMap<>(capacity);
  }

  public boolean park(Car car) {
    if (isFull() || isParked(car.getVehicleNumber())) {
      return false;
    }
    parkingMap.put(car.getVehicleNumber(), car);
    if(isFull() && ofNullable(parkingLotObserver).isPresent()) {
      parkingLotObserver.notify(true);
    }
    return true;
  }

  public boolean isFull() {
    return parkingMap.size() == capacity;
  }

  public Car unpark(String vehicleNumber) {
    if (!isParked(vehicleNumber)) {
      throw new RuntimeException("Car with provided VehicleNumber does not exists in the ParkingLot");
    }

    if(isFull() && ofNullable(parkingLotObserver).isPresent()) {
      parkingLotObserver.notify(false);
    }
    return parkingMap.remove(vehicleNumber);
  }

  private boolean isParked(String vehicleNumber) {
    return parkingMap.containsKey(vehicleNumber);
  }

  public void addObserver(ParkingLotObserver parkingLotObserver) {
    this.parkingLotObserver = parkingLotObserver;
  }

  public int getAvailableSpaces() {
    return capacity - parkingMap.size();
  }

}
