package com.medline.tdd.day1.cart;

import static java.lang.Math.floorDiv;
import static java.lang.Math.floorMod;
import static java.lang.Math.min;

public class BuyNGetMOffer implements Offer {

  private final int noOfPricedItemsInLot;
  private final int noOfFreeItemsInLot;

  public BuyNGetMOffer(int n, int m) {
    this.noOfPricedItemsInLot = n;
    this.noOfFreeItemsInLot = m;
  }

  @Override
  public double calculateOfferPrice(final double singleItemPrice, final int quantity) {
    return totalItemsToBePriced(quantity) * singleItemPrice;
  }

  private int totalItemsToBePriced(int quantity) {
    int lotSize = noOfPricedItemsInLot + noOfFreeItemsInLot;
    int noOfLots = floorDiv(quantity, lotSize);
    int remainingItems = floorMod(quantity, lotSize);
    int remainingItemsToBePriced = min(remainingItems, noOfPricedItemsInLot);
    return (noOfLots * noOfPricedItemsInLot) + remainingItemsToBePriced;
  }

}
