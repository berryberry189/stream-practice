package com.grace.streampractice.chap6;

import com.grace.streampractice.chap6.model.Order;
import com.grace.streampractice.chap6.model.OrderLine;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Chapter6FlatMap {
    public static void main(String[] args) {
        String[][] cities = new String[][]{
                {"Seoul", "Busan"}   ,
                {"San Francisco", "New York"},
                {"Madrid", "Barcelona"}
        };

        Stream<String[]> cityStream = Arrays.stream(cities);
        Stream<Stream<String>> cityStreamStream = cityStream.map(x -> Arrays.stream(x));
        List<Stream<String>> cityStreamList = cityStreamStream.collect(Collectors.toList());

        // 위의 cityStream은 이미 조작하여 사용 불가
        Stream<String[]> cityStream2 = Arrays.stream(cities);
        Stream<String> flattenedCityStream = cityStream2.flatMap(x -> Arrays.stream(x));
        List<String> flattenedCityList = flattenedCityStream.collect(Collectors.toList());
        System.out.println(flattenedCityList);

        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        Order order1 = new Order()
                .setId(101)
                .setOrderLines(Arrays.asList(
                        new OrderLine()
                            .setId(10001)
                            .setType(OrderLine.OrderLineType.PURCHASE)
                            .setAmount(BigDecimal.valueOf(5000)),
                        new OrderLine()
                            .setId(10002)
                            .setType(OrderLine.OrderLineType.PURCHASE)
                            .setAmount(BigDecimal.valueOf(4000))
                        ))
                .setCreatedByUserId(101);
        Order order2 = new Order()
                .setId(102)
                .setOrderLines(Arrays.asList(
                        new OrderLine()
                                .setId(10003)
                                .setType(OrderLine.OrderLineType.PURCHASE)
                                .setAmount(BigDecimal.valueOf(2000)),
                        new OrderLine()
                                .setId(10004)
                                .setType(OrderLine.OrderLineType.DISCOUNT)
                                .setAmount(BigDecimal.valueOf(-1000))
                ))
                .setCreatedByUserId(103);
        Order order3 = new Order()
                .setId(103)
                .setOrderLines(Arrays.asList(
                        new OrderLine()
                                .setId(10005)
                                .setType(OrderLine.OrderLineType.PURCHASE)
                                .setAmount(BigDecimal.valueOf(2000)),
                        new OrderLine()
                                .setId(10006)
                                .setType(OrderLine.OrderLineType.PURCHASE)
                                .setAmount(BigDecimal.valueOf(6000))
                ))
                .setCreatedByUserId(102);
        List<Order> orders = Arrays.asList(order1, order2, order3);
        List<OrderLine> orderLineList = orders.stream()
                .map(Order::getOrderLines)
                .flatMap(List::stream)
                .collect(Collectors.toList());
        System.out.println(orderLineList);

    }
}
