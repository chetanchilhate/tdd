package com.medline.tdd.day3.parking;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.medline.tdd.day3.parking.Attendant;
import com.medline.tdd.day3.parking.Car;
import com.medline.tdd.day3.parking.Coordinator;
import com.medline.tdd.day3.parking.ParkingAttendant;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CoordinatorTest {

  @Test
  void directCarSuccessfullyToFirstAvailableAttendant() {

    //arrange
    Attendant tim = mock(Attendant.class);
    List<ParkingAttendant> parkingAttendants = List.of(tim);

    Coordinator mike = new Coordinator(parkingAttendants);

    Car maruti = new Car("V1");

    when(tim.canDirect()).thenReturn(true);

    //act
    mike.direct(maruti);

    //assert
    verify(tim, times(1)).direct(maruti);
  }

  @Test
  void directCarSuccessfullyToFirstAvailableAttendantOfCoordinator() {

    //arrange
    Attendant tim = mock(Attendant.class);
    List<ParkingAttendant> jhonAttendants = List.of(tim);
    Coordinator jhon = spy(new Coordinator(jhonAttendants));

    List<ParkingAttendant> mikeAttendants = List.of(jhon);
    Coordinator mike = new Coordinator(mikeAttendants);

    Car maruti = new Car("V1");

    when(tim.canDirect()).thenReturn(true);

    //act
    mike.direct(maruti);

    //assert
    verify(tim, times(1)).direct(maruti);
    verify(jhon, times(1)).direct(maruti);
  }

  @Test
  @DisplayName("direct car successfully to second attendant of coordinator when first attendant has no space")
  void directCarSuccessfullyWithSingeCoordinatorAndMultipleAttendant() {

    //arrange
    Attendant tim = mock(Attendant.class);
    Attendant bill = mock(Attendant.class);
    List<ParkingAttendant> jhonAttendants = List.of(tim, bill);
    Coordinator jhon = spy(new Coordinator(jhonAttendants));

    List<ParkingAttendant> mikeAttendants = List.of(jhon);
    Coordinator mike = new Coordinator(mikeAttendants);

    Car maruti = new Car("V1");

    when(tim.canDirect()).thenReturn(false);
    when(bill.canDirect()).thenReturn(true);

    //act
    mike.direct(maruti);

    //assert
    verify(jhon, times(1)).direct(maruti);
    verify(bill, times(1)).direct(maruti);
  }

  @Test
  @DisplayName("direct car successfully to first available attendant of second coordinator when first coordinator has no attendant available")
  void directCarSuccessfullyWithMultipleCoordinatorAndMultipleAttendant() {

    //arrange
    Attendant tim = mock(Attendant.class);
    List<ParkingAttendant> jhonAttendants = List.of(tim);
    Coordinator jhon = new Coordinator(jhonAttendants);

    Attendant bill = mock(Attendant.class);
    List<ParkingAttendant> siaAttendants = List.of(bill);
    Coordinator sia = spy(new Coordinator(siaAttendants));

    List<ParkingAttendant> mikeAttendants = List.of(jhon, sia);
    Coordinator mike = new Coordinator(mikeAttendants);

    Car maruti = new Car("V1");

    when(tim.canDirect()).thenReturn(false);
    when(bill.canDirect()).thenReturn(true);

    //act
    mike.direct(maruti);

    //assert
    verify(sia, times(1)).direct(maruti);
    verify(bill, times(1)).direct(maruti);
  }

}
