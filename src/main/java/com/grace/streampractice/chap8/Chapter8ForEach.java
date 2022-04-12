package com.grace.streampractice.chap8;

import com.grace.streampractice.chap7.model.User;
import com.grace.streampractice.chap8.service.EmailService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Chapter8ForEach {
    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(3, 5, 8, 2);
        // 위 아래 동일
        numbers.stream().forEach(number -> System.out.println("The number is " + number));
        numbers.forEach(number -> System.out.println("The number is " + number));

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

        EmailService emailService = new EmailService();
        users.stream()
                .filter(user -> !user.isVerified())
                .forEach(emailService::sendVerifyYourEmail);

        // index 를 사용하는 경우는?
        System.out.println("============================");
        for(int i = 0; i < users.size(); i++){
            System.out.println(i + 1 + "번째 user : " + users.get(i).getName());
        }
        // 아래로 대체 가능!
        System.out.println("============================");
        IntStream.range(0, users.size()).forEach(i -> System.out.println(i + 1 + "번째 user : " + users.get(i).getName()));

    }
}
