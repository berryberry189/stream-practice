package com.grace.streampractice.chap10.service;

import com.grace.streampractice.chap10.model.Price;

@FunctionalInterface
public interface PriceProcessor {

  Price process(Price price);

  // 자신 먼저 작동하고 다음 PriceProcessor 를 작동한다.
  default PriceProcessor andThen(PriceProcessor next){
    return price -> next.process(process(price));
  }

}
