package com.medline.tdd;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class OfferTest {

  @Test
  void calculateOfferPriceForBuyOnegetOne() {

    Offer buyOneGetOne = new Offer();

    double offerPrice = buyOneGetOne.calculateOfferPrice(10, 5);

    assertEquals(30, offerPrice);

  }
}