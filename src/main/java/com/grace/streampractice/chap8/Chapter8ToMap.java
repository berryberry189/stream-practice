package com.grace.streampractice.chap8;

import com.grace.streampractice.chap6.model.Order;
import com.grace.streampractice.chap6.model.User;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.grace.streampractice.chap6.model.Order.OrderStatus.*;

public class Chapter8ToMap {
    // toMap : Stream 안의 데이터를 map 의 형태로 반환해주는 collector
    // keyMapper : 데이터를 map 의 key 형태로 변환하는 function
    // valueMapper : 데이터를 map 의 value 형태로 변환하는 function
    public static void main(String[] args) {

        Map<Integer, String> numberMap = Stream.of(3, -4, 5, 7, 2)
                .collect(Collectors.toMap(Function.identity(), x -> "Number is " + x));
                                        // (x -> x) == Function.identity()
        System.out.println(numberMap);
        System.out.println(numberMap.get(3));

        User user1 = new User()
                .setId(101)
                .setName("Alice")
                .setVerified(true)
                .setEmailAddress("alice@email.com");
        User user2 = new User()
                .setId(102)
                .setName("Bob")
                .setVerified(false)
                .setEmailAddress("bob@email.com");
        User user3 = new User()
                .setId(103)
                .setName("Charlie")
                .setVerified(false)
                .setEmailAddress("charlie@email.com");
        List<User> users = Arrays.asList(user1, user2, user3);
        Map<Integer, User> userIdToUserMap = users.stream()
                .collect(Collectors.toMap(User::getId, Function.identity()));
        System.out.println(userIdToUserMap);

        Order order1 = new Order()
                .setId(101)
                .setAmount(BigDecimal.valueOf(4000))
                .setStatus(CREATED);
        Order order2 = new Order()
                .setId(102)
                .setAmount(BigDecimal.valueOf(5000))
                .setStatus(ERROR);
        Order order3 = new Order()
                .setId(103)
                .setAmount(BigDecimal.valueOf(9000))
                .setStatus(PROGRESSED);
        Order order4 = new Order()
                .setId(104)
                .setAmount(BigDecimal.valueOf(7000))
                .setStatus(IN_PROGRESS);
        Order order5 = new Order()
                .setId(105)
                .setAmount(BigDecimal.valueOf(8000))
                .setStatus(ERROR);
        List<Order> orders = Arrays.asList(order1, order2, order3, order4, order5);

        // Create a map from order id to order status
        Map<Long, Order.OrderStatus> orderIdTOOrderStatus = orders.stream()
                .collect(Collectors.toMap(Order::getId, Order::getStatus));
        System.out.println(orderIdTOOrderStatus);


    }
}
