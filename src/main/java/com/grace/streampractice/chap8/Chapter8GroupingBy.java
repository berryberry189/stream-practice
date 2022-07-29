package com.grace.streampractice.chap8;

import com.grace.streampractice.chap6.model.Order;
import com.grace.streampractice.chap6.model.Order.OrderStatus;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.grace.streampractice.chap6.model.Order.OrderStatus.*;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.mapping;

public class Chapter8GroupingBy {
    // grouping by : Stream 안의 데이터에 classifier 를 적용했을떄 결과값이 같은 값까리 List 로 모아서 Map 형태로 반환해주는 collector
    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(13, 2, 104, 213, 402, 349, 649, 675, 208, 213);
        // 1의 자리 수가 같은 데이터 끼리 묶기
        Map<Integer, List<Integer>> unitDigitMap = numbers.stream()
                .collect(groupingBy(number -> number % 10));
        System.out.println(unitDigitMap);

        Map<Integer, Set<Integer>> unitDigitSet = numbers.stream()
                .collect(groupingBy(number -> number % 10, Collectors.toSet()));
        System.out.println(unitDigitSet);

        Map<Integer, List<String>> unitDigitStrMap =  numbers.stream()
                .collect(groupingBy(number -> number % 10,
                        mapping(number -> "unit digit is " + number, Collectors.toList())));
        System.out.println(unitDigitStrMap);


        Order order1 = new Order()
                .setId(101)
                .setAmount(BigDecimal.valueOf(4000))
                .setOrderName("오더1")
                .setStatus(CREATED);
        Order order2 = new Order()
                .setId(102)
                .setAmount(BigDecimal.valueOf(5000))
                .setOrderName("오더2")
                .setStatus(ERROR);
        Order order3 = new Order()
                .setId(103)
                .setAmount(BigDecimal.valueOf(9000))
                .setOrderName("오더3")
                .setStatus(PROGRESSED);
        Order order4 = new Order()
                .setId(104)
                .setAmount(BigDecimal.valueOf(7000))
                .setOrderName("오더4")
                .setStatus(IN_PROGRESS);
        Order order5 = new Order()
                .setId(105)
                .setAmount(BigDecimal.valueOf(8000))
                .setOrderName("오더5")
                .setStatus(ERROR);
        List<Order> orders = Arrays.asList(order1, order2, order3, order4, order5);
        // create a map from order status to the list of corresponding orders
        Map<OrderStatus, List<Order>> orderStatusMap = orders.stream()
                .collect(groupingBy(Order::getStatus));
        System.out.println(orderStatusMap);

        // order status 별 order amount 의 합
        Map<OrderStatus, BigDecimal> orderStatusToSumOdAmountMap = orders.stream()
                .collect(groupingBy(Order::getStatus, // status 로 그루핑
                        mapping(Order::getAmount,     // 그루핑 된 상태에서 amount 추출
                                Collectors.reducing(BigDecimal.ZERO, BigDecimal::add)))); // 추출된 amount 를 더하기
        System.out.println(orderStatusToSumOdAmountMap);

        // key : OrderStatus key, value: amount list
        Map<OrderStatus, List<BigDecimal>> orderStatusToAmountListMap = orders.stream()
                .collect(groupingBy(Order::getStatus,
                        mapping(Order::getAmount, Collectors.toList())));
        System.out.println("======= orderStatusToAmountListMap =======");
        System.out.println(orderStatusToAmountListMap);


        // joining
        // https://recordsoflife.tistory.com/55
        Map<OrderStatus, String> ordersPerType = orders.stream()
            .collect(groupingBy(Order::getStatus,
                mapping(Order::getOrderName, joining(", ", "Order Names: [", "]"))));
        System.out.println("================ ordersPerType =================");
        System.out.println(ordersPerType);

        // passingFailing
        Map<Boolean, List<Order>> passingFailing = orders.stream()
            .collect(Collectors.partitioningBy(s -> s.getAmount().longValue() > 6000));
        System.out.println("================ passingFailing =================");
        System.out.println(passingFailing);



    }
}
