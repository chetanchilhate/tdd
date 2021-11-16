package com.medline.tdd.cart;

public interface Offer {

  BuyNGetMOffer NO_OFFER = new BuyNGetMOffer(1, 0);

  BuyNGetMOffer BUY_ONE_GET_ONE_OFFER = new BuyNGetMOffer(1, 1);

  BuyNGetMOffer BUY_TWO_GET_THREE_OFFER = new BuyNGetMOffer(2, 3);

  double calculateOfferPrice(double singleItemPrice, int quantity);

}
