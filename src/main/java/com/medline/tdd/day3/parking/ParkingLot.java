package com.medline.tdd.day3.parking;

import static java.util.Optional.ofNullable;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot implements Subject {

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

    notifyParkingStatus(ParkingLotStatus.FULL);
    return true;
  }

  private void notifyParkingStatus(ParkingLotStatus parkingLotStatus) {
    if (isFull() && hasObserver()) {
      parkingLotObserver.notify(parkingLotStatus, this);
    }
  }

  public boolean hasObserver() {
    return ofNullable(parkingLotObserver).isPresent();
  }

  public boolean isFull() {
    return parkingMap.size() == capacity;
  }

  public boolean isAvailable() {
    return !isFull();
  }

  public Car unpark(String vehicleNumber) {
    if (!isParked(vehicleNumber)) {
      throw new RuntimeException("Car with provided VehicleNumber does not exists in the ParkingLot");
    }

    notifyParkingStatus(ParkingLotStatus.AVAILABLE);
    return parkingMap.remove(vehicleNumber);
  }

  private boolean isParked(String vehicleNumber) {
    return parkingMap.containsKey(vehicleNumber);
  }

  public void addObserver(ParkingLotObserver parkingLotObserver) {
    this.parkingLotObserver = parkingLotObserver;
  }

  public void removeObserver() {
    this.parkingLotObserver = null;
  }

  public int getAvailableSpaces() {
    return capacity - parkingMap.size();
  }

}
