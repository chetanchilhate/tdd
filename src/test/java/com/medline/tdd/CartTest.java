package com.medline.tdd;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CartTest {

  @Test
  void totalShouldBeTenWhenAppleIsAddedToCart() {

    // arrange - given
    Item item = new Item(10);
    Cart cart = new Cart();
    cart.add(item);

    //act- when
    double total = cart.getTotal();

    //assert - then
    assertEquals(10.00, total);
  }
  @Test
  void totalShouldBeZeroWhenCartIsEmpty() {

    // arrange - given
    Cart cart = new Cart();

    //act- when
    double total = cart.getTotal();

    //assert - then
    assertEquals(0, total);
  }

  @Test
  void totalShouldBeFiftyWhenCartHasFiveApples() {

    // arrange - given
    Item apple = new Item(10, 5);
    Cart cart = new Cart();
    cart.add(apple);

    //act- when
    double total = cart.getTotal();

    //assert - then
    assertEquals(50, total);
  }

  @Test
  void totalShouldBeTenWhenCartHasOneAppleWithBuyOneGetOne() {

    // arrange - given
    Item apple = new Item(10, 1);
    apple.setOffer(new Offer());

    Cart cart = new Cart();

    //act- when
    cart.add(apple);
    double total = cart.getTotal();

    //assert - then
    assertEquals(10, total);
  }

  @Test
  void totalShouldBeTenWhenCartHasTwoApplesWithBuyOneGetOne() {

    // arrange - given
    Item apple = new Item(10, 2);
    apple.setOffer(new Offer());

    Cart cart = new Cart();

    //act- when
    cart.add(apple);
    double total = cart.getTotal();

    //assert - then
    assertEquals(10, total);
  }


  @Test
  void totalShouldBeTwentyWhenCartHasThreeApplesWithBuyOneGetOne() {

    // arrange - given
    Item apple = new Item(10, 3);
    apple.setOffer(new Offer());

    Cart cart = new Cart();

    //act- when
    cart.add(apple);
    double total = cart.getTotal();

    //assert - then
    assertEquals(20, total);
  }

}