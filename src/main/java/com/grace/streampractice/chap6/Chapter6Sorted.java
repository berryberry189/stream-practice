package com.grace.streampractice.chap6;

import com.grace.streampractice.chap6.model.Order;
import com.grace.streampractice.chap6.model.User;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Chapter6Sorted {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(3, -4, 7, 4);
        List<Integer> sortedNumbers = numbers.stream().sorted().collect(Collectors.toList());
        System.out.println(sortedNumbers);

        User user1 = new User()
                .setId(101)
                .setName("Paul")
                .setVerified(true);
        User user2 = new User()
                .setId(102)
                .setName("David")
                .setVerified(false);
        User user3 = new User()
                .setId(103)
                .setName("John")
                .setVerified(false);
        List<User> users = Arrays.asList(user1, user2, user3);
        List<User> sortedUsers = users.stream()
                .sorted((u1, u2)-> u1.getName().compareTo(u2.getName()))
                .collect(Collectors.toList());
        System.out.println(sortedUsers);

        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        Order order1 = new Order()
                .setId(101)
                .setStatus(Order.OrderStatus.CREATED)
                .setCreatedByUserId(101)
                .setCreatedAt(now.minusHours(4));
        Order order2 = new Order()
                .setId(102)
                .setStatus(Order.OrderStatus.ERROR)
                .setCreatedByUserId(103)
                .setCreatedAt(now.minusHours(40));
        Order order3 = new Order()
                .setId(103)
                .setStatus(Order.OrderStatus.PROGRESSED)
                .setCreatedByUserId(102)
                .setCreatedAt(now.minusHours(30));
        Order order4 = new Order()
                .setId(104)
                .setStatus(Order.OrderStatus.IN_PROGRESS)
                .setCreatedByUserId(104)
                .setCreatedAt(now.minusHours(15));
        Order order5 = new Order()
                .setId(105)
                .setStatus(Order.OrderStatus.ERROR)
                .setCreatedByUserId(101)
                .setCreatedAt(now.minusHours(20));
        List<Order> orders = Arrays.asList(order1, order2, order3, order4, order5);
        List<Order> sortedOrders = orders.stream()
                .sorted((o1, o2) -> o1.getCreatedAt().compareTo(o2.getCreatedAt()))
                .collect(Collectors.toList());
        System.out.println(sortedOrders);
    }
}
