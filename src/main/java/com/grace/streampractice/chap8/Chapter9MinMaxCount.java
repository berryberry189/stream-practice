package com.grace.streampractice.chap8;

import com.grace.streampractice.chap6.model.Order;
import com.grace.streampractice.chap6.model.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static com.grace.streampractice.chap6.model.Order.OrderStatus.*;

public class Chapter9MinMaxCount {

    public static void main(String[] args) {

        // 가장 큰 integer
        Optional<Integer> max = Stream.of(5, 7, 8, 2, 4, 9)
                .max(Integer::compareTo); //(x, y) -> x - y
        System.out.println(max.get());

        // 이름 내림차순 정렬 맨 앞
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

        User firstUser = users.stream()
                .min((u1, u2) -> u1.getName().compareTo(u2.getName()))
                .get();
        System.out.println(firstUser);


        // 양수의 갯수
        long positiveIntegerCount = Stream.of(5, -3, -4, 7, 4, 6)
                .filter(x -> x > 0)
                .count();
        System.out.println(positiveIntegerCount);


        // 최근 24시간 이내에 가입한 유저 가운데 검증이 안된 유저 구하기
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        user1.setCreatedAt(now.minusHours(2));
        user2.setCreatedAt(now.minusHours(10));
        user3.setCreatedAt(now.minusHours(1));
        User user4 = new User()
                .setId(104)
                .setName("David")
                .setVerified(true)
                .setEmailAddress("david@email.com")
                .setCreatedAt(now.minusHours(27));
        users = Arrays.asList(user1, user2, user3, user4);

        long unverifiedUsersIn24h = users.stream()
                .filter(user -> user.getCreatedAt().isAfter(now.minusDays(1)))
                .filter(user -> !user.isVerified())
                .count();
        System.out.println(unverifiedUsersIn24h);


        // 에러 상태의 주문 중 amount 가 가장 큰 주문 찾기
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

        Order errorStatusOrderWithMaxAmount = orders.stream()
                .filter(order -> ERROR.equals(order.getStatus()))
                .max((o1, o2) -> o1.getAmount().compareTo(o2.getAmount()))
                .get();
        System.out.println(errorStatusOrderWithMaxAmount);

        BigDecimal maxErrorAmount = orders.stream()
                .filter(order -> ERROR.equals(order.getStatus()))
                .map(Order::getAmount)
                .max(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);
        System.out.println(maxErrorAmount);
    }
}
