package com.medline.tdd;

public class Offer {

  private final static int LOT_SIZE = 2;

  public double calculateOfferPrice(double singleItemPrice, int quantity) {
    return (Math.ceil(Integer.valueOf(quantity).doubleValue() / LOT_SIZE) * singleItemPrice);
  }

}
