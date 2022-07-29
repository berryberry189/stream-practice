package com.grace.streampractice.chap8;

import com.grace.streampractice.chap6.model.Order;
import com.grace.streampractice.chap6.model.User;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.grace.streampractice.chap6.model.Order.OrderStatus.*;

public class Chapter8AllMatchAnyMatch {

    // allMatch : Stream 안의 모든 데이터가 predicate 를 만족하면 true
    // anyMatch : Stream 안의 데이터중 하나라도 predicate 를 만족하면 true
    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(5, -7, 8, 2, 4, 9);
        // 전부 양수 인지 확인
        boolean allPositive = numbers.stream()
                .allMatch(number -> number > 0);
        System.out.println("Are all numbers positive : " + allPositive);

        // 음수가 하나라도 있는지
        boolean anyNegative = numbers.stream()
                .anyMatch(number -> number < 0);
        System.out.println("Is any numbers negative : " + anyNegative);

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
        // user 가 전부 검증이 되었는지
        boolean areAllUserVerified = users.stream()
                .allMatch(User::isVerified);
        System.out.println(areAllUserVerified);

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
        // order 중 에러상태가 하나라도 있으면 true 리턴
        boolean isAnyOrderInErrorStatus = orders.stream()
                .anyMatch(o -> ERROR.equals(o.getStatus()));
        System.out.println(isAnyOrderInErrorStatus);


        // any match 테스트
        // A목록 B목록 있을 때, A와 B목록에서 겹치는 항목을 제외한 A목록의 남은 요소 구하기

        List<Long> A = List.of(1L, 2L, 3L, 4L, 5L);
        List<Long> B = List.of(4L, 5L, 6L, 7L, 8L);

        List<Long> noneMatchList =
                A.stream()
                        .filter(i -> B.stream().noneMatch(Predicate.isEqual(i)))
                        .collect(Collectors.toList());

        System.out.println("");
        System.out.println("=============== noneMatchList =================");
        System.out.println(noneMatchList);

        List<Long> anyMatch =
            A.stream()
                .filter(i -> B.stream().anyMatch(Predicate.isEqual(i)))
                .collect(Collectors.toList());

        System.out.println("");
        System.out.println("================ anyMatch ================");
        System.out.println(anyMatch);

    }
}
