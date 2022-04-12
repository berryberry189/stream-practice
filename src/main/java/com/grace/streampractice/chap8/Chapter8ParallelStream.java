package com.grace.streampractice.chap8;

import com.grace.streampractice.chap7.model.User;
import com.grace.streampractice.chap8.service.EmailService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Chapter8ParallelStream {

    // 여러개의 스레드를 이용하여 stream 을 병렬로 처리한다
    // 종결처리가 순서가 유의미한 종결처리일 경우(ex: Collectors.toList()) 순서가 항상 올바르게 나온다.
    // 장점 : 간단하게 병렬처리 가능, 속도가 빨라질 수 있음
    // 단점 : 항상 속도가 빨라지는것은 아님, 공통으로 사용하는 리소스가 있을 경우 잘못된 결과가 나오거나 오류가 날 수 있다(deadlock)

    public static void main(String[] args) {
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
        User user4 = new User()
                .setId(104)
                .setName("David")
                .setVerified(true)
                .setEmailAddress("david@email.com");
        User user5 = new User()
                .setId(105)
                .setName("Eve")
                .setVerified(false)
                .setEmailAddress("eve@email.com");
        User user6 = new User()
                .setId(106)
                .setName("Frank")
                .setVerified(false)
                .setEmailAddress("frank@email.com");
        List<User> users = Arrays.asList(user1, user2, user3, user4, user5, user6);

        long startTime = System.currentTimeMillis();
        EmailService emailService = new EmailService();
        users.stream()
                .filter(user -> !user.isVerified())
                .forEach(emailService::sendVerifyYourEmail);
        long endTime = System.currentTimeMillis();
        System.out.println("Sequential: " + (endTime - startTime) + "ms");

        // 중간처리 과정이 순서가 유의미 하면 parallel 사용 뷸가 => 느려질 가능성이 있기때문
        startTime = System.currentTimeMillis();
        users.stream().parallel()
                .filter(user -> !user.isVerified())
                .forEach(emailService::sendVerifyYourEmail);
        endTime = System.currentTimeMillis();
        System.out.println("Parallel: " + (endTime - startTime) + "ms");

        List<User> processedUsers = users.parallelStream()
                .map(user -> {
                    System.out.println("Capitalize user name for user " + user.getId());
                    user.setName(user.getName().toUpperCase());
                    return user;
                })
                .map(user -> {
                    System.out.println("Set 'isVerified' to true for user " + user.getId());
                    user.setVerified(true);
                    return user;
                })
                .collect(Collectors.toList());
        System.out.println(processedUsers);

    }
}
