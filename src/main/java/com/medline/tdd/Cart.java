package com.medline.tdd;

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

  //needs to be remove unused
  public void addItems(Item item, int quantity) {

    for(int i=0 ; i < quantity; i++) {
      items.add(item);
    }
  }
}
