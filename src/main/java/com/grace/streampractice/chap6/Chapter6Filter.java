package com.grace.streampractice.chap6;

import com.grace.streampractice.chap6.model.Order;
import com.grace.streampractice.chap6.model.User;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Chapter6Filter {
    public static void main(String[] args) {

        Stream<Integer> numberStream = Stream.of(2,-5,7,10,-3);
        Stream<Integer> filteredNumberStream = numberStream.filter(x -> x > 0);
        List<Integer> filteredNumbers = filteredNumberStream.collect(Collectors.toList());

        List<Integer> filteredNumbers2 = Stream.of(2,-5,7,10,-3)
                .filter(x -> x > 0)
                .collect(Collectors.toList());
        System.out.println(filteredNumbers);
        System.out.println(filteredNumbers2);

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
                .setVerified(true)
                .setEmailAddress("charlie@email.com");

        List<User> users = Arrays.asList(user1, user2, user3);

        List<User> verifiedUsers = users.stream()
                .filter(User::isVerified)
                .collect(Collectors.toList());
        System.out.println("===== verifiedUsers =====");
        System.out.println(verifiedUsers);

        List<User> unVerifiedUsers = users.stream()
                .filter(user -> !user.isVerified())
                .collect(Collectors.toList());
        System.out.println("===== unVerifiedUsers =====");
        System.out.println(unVerifiedUsers);


        Order order1 = new Order()
                .setId(101)
                .setStatus(Order.OrderStatus.CREATED);

        Order order2 = new Order()
                .setId(102)
                .setStatus(Order.OrderStatus.ERROR);

        Order order3 = new Order()
                .setId(103)
                .setStatus(Order.OrderStatus.PROGRESSED);

        Order order4 = new Order()
                .setId(104)
                .setStatus(Order.OrderStatus.IN_PROGRESS);

        Order order5 = new Order()
                .setId(105)
                .setStatus(Order.OrderStatus.ERROR);

        List<Order> orders = Arrays.asList(order1, order2, order3, order4, order5);

        List<Order> filterOrders = orders.stream()
                .filter(order -> order.getStatus() == Order.OrderStatus.ERROR)
                .collect(Collectors.toList());
        System.out.println("===== error 상태인 order =====");
        System.out.println(filterOrders);

    }
}
