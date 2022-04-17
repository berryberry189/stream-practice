package com.grace.streampractice.chap10.service;

import com.grace.streampractice.chap10.model.Price;

@FunctionalInterface // 추상 메서드가 1개만 존재하는 interface
// 함수형 인터페이스를 사용하는 이유 : 람다식은 함수형 인터페이스로만 접근이 가능하기 때문
public interface PriceProcessor {

  Price process(Price price);

  // 자신 먼저 작동하고 다음 PriceProcessor 를 작동한다.
  default PriceProcessor andThen(PriceProcessor next){
    return price -> next.process(process(price));
  }

}
