package com.medline.tdd.cart;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BuyNGetMOfferTest {

  @Test
  void calculateOfferPriceForBuyOneGetOne() {

    BuyNGetMOffer buyOneGetOne = new BuyNGetMOffer(1, 1);

    double offerPrice = buyOneGetOne.calculateOfferPrice(10.0, 5);

    assertEquals(30, offerPrice);

  }

}