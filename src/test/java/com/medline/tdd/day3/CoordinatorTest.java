package com.medline.tdd.day3;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class CoordinatorTest {

  @Test
  void parkCarSuccessfullyWithSingleCoordinatorAndSingleAttendant() {

    ParkingLot groundFloorParkingLot = mock(ParkingLot.class);

    List<ParkingLot> timParkingLots = new ArrayList<>();

    timParkingLots.add(groundFloorParkingLot);

    Attendant tim = spy(new Attendant(timParkingLots));

    Car maruti = new Car("V1");

    Coordinator coordinator = new Coordinator();

    coordinator.assign(tim, maruti);
    
    verify(groundFloorParkingLot, times(1)).park(maruti);

  }
  
}
