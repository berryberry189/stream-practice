package com.grace.streampractice.chap6;

import com.grace.streampractice.chap6.model.Order;
import com.grace.streampractice.chap6.model.User;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Chapter6Map {
    // map : 데이터를 변형하는데 사용
    public static void main(String[] args) {
        List<Integer> numberList = Arrays.asList(3, 6, -4);
        List<Integer> numberListX2 = numberList.stream()
                .map(x -> x * 2)
                .collect(Collectors.toList());
        System.out.println(numberListX2);

        List<String> stringList = numberList.stream()
                .map(x -> "Number is " + x)
                .collect(Collectors.toList());
        System.out.println(stringList);

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

        List<String> emailList = users.stream()
                .map(User::getEmailAddress)
                .collect(Collectors.toList());
        System.out.println(emailList);

        Order order1 = new Order()
                .setId(101)
                .setStatus(Order.OrderStatus.CREATED)
                .setCreatedByUserId(101);
        Order order2 = new Order()
                .setId(102)
                .setStatus(Order.OrderStatus.ERROR)
                .setCreatedByUserId(103);
        Order order3 = new Order()
                .setId(103)
                .setStatus(Order.OrderStatus.PROGRESSED)
                .setCreatedByUserId(102);
        Order order4 = new Order()
                .setId(104)
                .setStatus(Order.OrderStatus.IN_PROGRESS)
                .setCreatedByUserId(104);
        Order order5 = new Order()
                .setId(105)
                .setStatus(Order.OrderStatus.ERROR)
                .setCreatedByUserId(101);
        List<Order> orders = Arrays.asList(order1, order2, order3, order4, order5);

        List<Long> createdUserIdList = orders.stream()
                .map(Order::getCreatedByUserId)
                .collect(Collectors.toList());
        System.out.println(createdUserIdList);

    }
}
