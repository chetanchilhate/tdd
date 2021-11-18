package com.medline.tdd.day3;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

class CoordinatorTest {

  @Test
  void directCarSuccessfullyToAttendant() {

    //arrange
    Attendant tim = mock(Attendant.class);
    Coordinator coordinator = new Coordinator(tim);

    Car maruti = new Car("V1");

    //act
    coordinator.direct(maruti);

    //assert
    verify(tim, times(1)).direct(maruti);

  }
  
}
