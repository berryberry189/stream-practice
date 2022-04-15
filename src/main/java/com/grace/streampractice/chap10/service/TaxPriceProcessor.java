package com.grace.streampractice.chap10.service;

import com.grace.streampractice.chap10.model.Price;

public class TaxPriceProcessor implements PriceProcessor{

  @Override
  public Price process(Price price) {
    return new Price(price.getPrice() + ", then applied tax");
  }
}
