package com.grace.streampractice.chap8;

import com.grace.streampractice.chap6.model.Order;
import com.grace.streampractice.chap6.model.OrderLine;
import com.grace.streampractice.chap6.model.User;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class Chapter8Reduce {
    // reduce : 주어진 함수를 반복 적용해 Stream 안의 데이터를 하나의 값으로 합치는 작업 (값이 하나만 나올때 까지 함수를 계속 적용)
    // Max, Min, Count 도 사실 reduce 의 일종
    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(5, -7, 8, -4, 9);
        int sum = numbers.stream()
                .reduce((x, y) -> x + y).get();
        System.out.println(sum);

        // min 함수를 reduce 로 표현
        int min = numbers.stream()
                .reduce((x, y) -> x > y ? x : y)
                .get();
        System.out.println(min);

        int product = numbers.stream()
                .reduce(1, (x, y) -> x * y);
        System.out.println(product);


        // 숫자가 스트링으로 들어있을 떄
        List<String> numberStrList = Arrays.asList("5", "-7", "8", "-4", "9");
        int sumOfNumberStrList = numberStrList.stream()
                .map(Integer::parseInt)
                .reduce(0, (x, y) -> x + y);
        System.out.println(sumOfNumberStrList);
        // 자주는 사용 X
        int sumOfNumberStrList2 = numberStrList.stream()
                .reduce(0, (number, str) -> number + Integer.parseInt(str), (num1, num2) -> num1 + num2);
        System.out.println(sumOfNumberStrList2);


        User user1 = new User()
                .setId(101)
                .setName("Alice")
                .setFriendUserIds(Arrays.asList(201, 202, 203, 204));
        User user2 = new User()
                .setId(102)
                .setName("Bob")
                .setFriendUserIds(Arrays.asList(204, 205, 206));
        User user3 = new User()
                .setId(103)
                .setName("Charlie")
                .setFriendUserIds(Arrays.asList(204, 205, 207));
        List<User> users = Arrays.asList(user1, user2, user3);

        // 모든 유저의 친구수의 총 합
        int sumOfNumberOfFriends = users.stream()
                .map(User::getFriendUserIds)
                .map(List::size)
                .reduce(0, (x, y) -> x + y);
        System.out.println(sumOfNumberOfFriends);

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

        // find the sum of amounts
        BigDecimal sumOfAmounts = orders.stream()
                .map(Order::getOrderLines) // Stream<List<OrderLine>>
                .flatMap(List::stream)     // Stream<OrderLine>
                .map(OrderLine::getAmount) // Stream<BigDecimal>
                .reduce(BigDecimal.ZERO, BigDecimal::add); // (x, y) -> x.add(y)
        System.out.println(sumOfAmounts);

    }
}
