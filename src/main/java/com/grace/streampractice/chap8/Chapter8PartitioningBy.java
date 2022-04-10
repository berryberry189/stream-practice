package com.grace.streampractice.chap8;

import com.grace.streampractice.chap7.model.User;
import com.grace.streampractice.chap8.service.EmailService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Chapter8PartitioningBy {
    // partitioning by : grouping by 와 유사하지만 function 대신 predicate 를 받아 true 와 false 두 key 가 존재하는 map 을 반환
    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(13, 2, 104, 213, 402, 349, 649, 675, 208, 213);
        // 짝수인지 홀수인지 체크
        Map<Boolean, List<Integer>> numberPartitions = numbers.stream()
                .collect(Collectors.partitioningBy(number -> number % 2 == 0));
        System.out.println("짝수" + numberPartitions.get(true));
        System.out.println("홀수" + numberPartitions.get(false));


        User user1 = new User()
                .setId(101)
                .setName("Alice")
                .setFriendUserIds(Arrays.asList(201, 202, 203, 204, 205, 206, 207, 208, 209, 300, 301, 302));
        User user2 = new User()
                .setId(102)
                .setName("Bob")
                .setFriendUserIds(Arrays.asList(204, 205, 206));
        User user3 = new User()
                .setId(103)
                .setName("Charlie")
                .setFriendUserIds(Arrays.asList(204, 205, 207));
        List<User> users = Arrays.asList(user1, user2, user3);

        // 친구 숫자 5 초과 -> A 이메일 \ 5 이하 B -> 이메일
        Map<Boolean, List<User>> userPartitions = users.stream()
                .collect(Collectors.partitioningBy(user -> user.getFriendUserIds().size() > 5));
        EmailService emailService = new EmailService();
        for (User user : userPartitions.get(true)) {
            emailService.sendPlayWithFriendsEmail(user);
        }
        for (User user : userPartitions.get(false)) {
            emailService.sendMakeMoreFriendsEmail(user);
        }

    }
}
