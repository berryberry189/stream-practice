package com.grace.streampractice.chap9;

import com.grace.streampractice.chap6.model.Order;
import com.grace.streampractice.chap6.model.OrderLine;
import com.grace.streampractice.chap9.priceprocessor.OrderLineAggregationPriceProcessor;
import com.grace.streampractice.chap9.priceprocessor.TaxPriceProcessor;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Chapter9FunctionComposition {
    // Function Composition : 함수합성, 여러개의 함수를 합쳐서 새로운 함수로 만드는 것
    public static void main(String[] args) {

        Function<Integer, Integer> multiplyByTwo = x -> 2 * x;
        Function<Integer, Integer> addTen = x -> x + 10;

        // 함수 합성
        Function<Integer, Integer> composedFunction = multiplyByTwo.andThen(addTen);
        System.out.println(composedFunction.apply(3));

        Order unprocessedOrder = new Order()
                .setId(101)
                .setOrderLines(Arrays.asList(
                        new OrderLine().setAmount(BigDecimal.valueOf(1000)),
                        new OrderLine().setAmount(BigDecimal.valueOf(2000))
                ));

        List<Function<Order, Order>> priceProcessor = getPriceProcessor(unprocessedOrder);
        // 하나로 합치기
        Function<Order, Order> mergePriceProcessor = priceProcessor.stream()
                .reduce(Function.identity(), Function::andThen);
        // ((priceProcessor1, priceProcessor2) -> priceProcessor1.andThen(priceProcessor2)) ==  (Function::andThen)

        Order processedOrder = mergePriceProcessor.apply(unprocessedOrder);
        System.out.println(processedOrder);
    }

    public static List<Function<Order, Order>> getPriceProcessor(Order order){
        return Arrays.asList(new OrderLineAggregationPriceProcessor(),
                new TaxPriceProcessor(new BigDecimal("9.375")));
    }


}
