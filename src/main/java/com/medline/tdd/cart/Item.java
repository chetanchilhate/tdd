package com.medline.tdd.cart;

import static java.lang.Math.max;

import java.util.Objects;

public class Item {

  private double price;
  private int quantity = 1;
  private Offer offer = Offer.NO_OFFER;

  public Item(double price) {
    this.price = price;
  }

  public Item(double price, int quantity) {
    this(price);
    this.quantity =  max(quantity, 0);
  }

  public double getTotalPrice() {
    return offer.calculateOfferPrice(price, quantity);
  }

  public void assignOffer(Offer offer) {
    Objects.requireNonNull(offer, "Offer can't be null");
    this.offer = offer;
  }

  public void revokeOffer() {
    this.offer = Offer.NO_OFFER;
  }

}
