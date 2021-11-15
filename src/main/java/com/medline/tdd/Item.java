package com.medline.tdd;

import static java.util.Optional.ofNullable;

public class Item {

  private double price;
  private int quantity = 1;
  private Offer offer;

  public Item(double price) {
    this.price = price;
  }

  public Item(double price, int quantity) {
    this(price);
    this.quantity = quantity;
  }

  public  double getTotalPrice() {
    return ofNullable(offer).isPresent() ? offer.calculateOfferPrice(price, quantity) : price * quantity ;
  }

  public void setOffer(Offer offer) {
    this.offer = offer;
  }

}
