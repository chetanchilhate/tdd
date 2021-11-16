package com.medline.tdd.day1.cart;

import java.util.ArrayList;
import java.util.List;

public class Cart {

  private final List<Item> items = new ArrayList<>();

  public double getTotal() {
    return items.stream()
                .map(Item::getTotalPrice)
                .reduce(0.0 , Double::sum);
  }

  public void add(Item item) {
    items.add(item);
  }

}
