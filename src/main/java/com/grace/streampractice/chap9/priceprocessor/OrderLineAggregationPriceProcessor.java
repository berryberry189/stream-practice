package com.grace.streampractice.chap9.priceprocessor;

import com.grace.streampractice.chap6.model.Order;
import com.grace.streampractice.chap6.model.OrderLine;

import java.math.BigDecimal;
import java.util.function.Function;

public class OrderLineAggregationPriceProcessor implements Function<Order, Order> {


    // Order 안의 OrderLine 들의 amount 의 합을 Order에 세팅
    @Override
    public Order apply(Order order) {
        return order.setAmount(order.getOrderLines().stream()
                .map(OrderLine::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add));

    }
}
