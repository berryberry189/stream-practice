package com.grace.streampractice.chap10.service;

import com.grace.streampractice.chap10.model.Price;

public class BasicPriceProcessor implements PriceProcessor{

  @Override
  public Price process(Price price) {
    return price;
  }
}
