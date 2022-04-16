package com.grace.streampractice.chap10;

import com.grace.streampractice.chap10.model.Price;
import com.grace.streampractice.chap10.service.BasicPriceProcessor;
import com.grace.streampractice.chap10.service.DiscountPriceProcessor;
import com.grace.streampractice.chap10.service.PriceProcessor;
import com.grace.streampractice.chap10.service.TaxPriceProcessor;

public class Chapter10DecoratorPattern {

  // 구조 패턴의 하나
  // 용도에 따라 객체에 기능으 계속 추가(decorate) 할 수 있게 해준다.

  public static void main(String[] args) {
    Price unprocessedPrice = new Price("Original Price");

    PriceProcessor basicPriceProcessor = new BasicPriceProcessor();
    PriceProcessor discountPriceProcessor = new DiscountPriceProcessor();
    PriceProcessor taxPriceProcessor = new TaxPriceProcessor();

    PriceProcessor decoratedPriceProcessor = basicPriceProcessor
        .andThen(discountPriceProcessor)
        .andThen(taxPriceProcessor);
    Price processedPrice = decoratedPriceProcessor.process(unprocessedPrice);

    // Original Price, then applied discount, then applied tax
    System.out.println(processedPrice.getPrice());


    // 람다를 통헤 간단하게 만들기
    PriceProcessor decoratedPriceProcessor2 = basicPriceProcessor
        .andThen(taxPriceProcessor)
        .andThen(price -> new Price(price.getPrice() + ", then apply another procedure "));
    Price processedPrice2 = decoratedPriceProcessor2.process(unprocessedPrice);

    // Original Price, then applied tax, then apply another procedure
    System.out.println(processedPrice2.getPrice());
  }
}
