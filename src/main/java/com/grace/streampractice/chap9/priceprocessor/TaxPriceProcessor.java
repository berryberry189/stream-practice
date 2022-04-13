package com.grace.streampractice.chap9.priceprocessor;

import com.grace.streampractice.chap6.model.Order;

import java.math.BigDecimal;
import java.util.function.Function;

public class TaxPriceProcessor implements Function<Order, Order> {

    private final BigDecimal taxRete;

    public TaxPriceProcessor(BigDecimal taxRete){
        this.taxRete = taxRete;
    }

    // 새금 합한 amount
    @Override
    public Order apply(Order order) {
        return order.setAmount(order.getAmount()
                .multiply(taxRete.divide(new BigDecimal(100)).add(BigDecimal.ONE)));
    }
}
