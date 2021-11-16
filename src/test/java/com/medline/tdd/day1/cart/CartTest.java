package com.medline.tdd.day1.cart;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CartTest {

  @Test
  void totalShouldBeTenWhenAppleIsAddedToCart() {
    // arrange - given
    Item item = new Item(10);
    Cart cart = new Cart();

    //act- when
    cart.add(item);
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

    //act- when
    cart.add(apple);
    double total = cart.getTotal();

    //assert - then
    assertEquals(50, total);
  }

  @Test
  void totalShouldBeFiftyWhenCartHasThreeApplesAndOneOrange() {

    // arrange - given
    Item apple = new Item(10, 3);
    Item orange = new Item(20, 1);
    Cart cart = new Cart();

    //act- when
    cart.add(apple);
    cart.add(orange);
    double total = cart.getTotal();

    //assert - then
    assertEquals(50, total);
  }

  @Test
  void totalShouldBeTenWhenCartHasOneAppleWithBuyOneGetOne() {

    // arrange - given
    Item apple = new Item(10, 1);
    apple.assignOffer(Offer.BUY_ONE_GET_ONE_OFFER);

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
    apple.assignOffer(Offer.BUY_ONE_GET_ONE_OFFER);

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
    apple.assignOffer(Offer.BUY_ONE_GET_ONE_OFFER);

    Cart cart = new Cart();

    //act- when
    cart.add(apple);
    double total = cart.getTotal();

    //assert - then
    assertEquals(20, total);
  }

  @Test
  void totalShouldBeFortyWhenCartHasFiveOrangesWithBuyTwoGetThree() {

    // arrange - given
    Item orange = new Item(20, 5);
    orange.assignOffer(Offer.BUY_TWO_GET_THREE_OFFER);

    Cart cart = new Cart();

    //act- when
    cart.add(orange);
    double total = cart.getTotal();

    //assert - then
    assertEquals(40, total);
  }

  @Test
  void totalShouldBeSixtyWhenCartHasSixOrangesWithBuyTwoGetThree() {

    // arrange - given
    Item orange = new Item(20, 6);
    orange.assignOffer(Offer.BUY_TWO_GET_THREE_OFFER);

    Cart cart = new Cart();

    //act- when
    cart.add(orange);
    double total = cart.getTotal();

    //assert - then
    assertEquals(60, total);
  }

  @Test
  void totalShouldBeEightyWhenCartHasSevenOrangesWithBuyTwoGetThree() {

    // arrange - given
    Item orange = new Item(20, 7);
    orange.assignOffer(Offer.BUY_TWO_GET_THREE_OFFER);

    Cart cart = new Cart();

    //act- when
    cart.add(orange);
    double total = cart.getTotal();

    //assert - then
    assertEquals(80, total);
  }

  @Test
  void totalShouldBeEightyWhenCartHasEightOrangesWithBuyTwoGetThree() {

    // arrange - given
    Item orange = new Item(20, 8);
    orange.assignOffer(Offer.BUY_TWO_GET_THREE_OFFER);

    Cart cart = new Cart();

    //act- when
    cart.add(orange);
    double total = cart.getTotal();

    //assert - then
    assertEquals(80, total);
  }

  @Test
  void totalShouldBeEightyWhenCartHasNineOrangesWithBuyTwoGetThree() {

    // arrange - given
    Item orange = new Item(20, 9);
    orange.assignOffer(Offer.BUY_TWO_GET_THREE_OFFER);

    Cart cart = new Cart();

    //act- when
    cart.add(orange);
    double total = cart.getTotal();

    //assert - then
    assertEquals(80, total);
  }

  @Test
  void totalShouldBeHundredWhenCartHasFiveOrangesAfterRevokingOffer() {

    // arrange - given
    Item orange = new Item(20, 5);
    Cart cart = new Cart();

    //act- when
    cart.add(orange);
    orange.assignOffer(Offer.BUY_TWO_GET_THREE_OFFER);
    orange.revokeOffer();
    double total = cart.getTotal();

    //assert - then
    assertEquals(100, total);
  }

}