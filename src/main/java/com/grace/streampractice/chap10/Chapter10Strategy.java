package com.grace.streampractice.chap10;

import com.grace.streampractice.chap10.model.User;
import com.grace.streampractice.chap10.service.EmailProvider;
import com.grace.streampractice.chap10.service.EmailSender;
import com.grace.streampractice.chap10.service.MakeMoreFriendsEmailProvider;
import com.grace.streampractice.chap10.service.VerifyYourEmailProvider;

import java.util.Arrays;
import java.util.List;

public class Chapter10Strategy {
    // 대표적인 행동패턴
    // 런타임에 어떤 전략(알고리즘)을 사용할지 선택할 수 있게 해준다
    // 전략들을 캡슐화 하여 단단하게 교체할 수 있게 해준다
    public static void main(String[] args) {

        User user1 = User.builder(1, "Alice")
                .with(builder -> {
                    builder.emailAddress = "alice@email.com";
                    builder.isVerified = false;
                    builder.friendUserIds = Arrays.asList(201, 202, 203, 204, 205, 211, 223);
                }).build();
        User user2 = User.builder(2, "Bob")
                .with(builder -> {
                    builder.emailAddress = "bob@email.com";
                    builder.isVerified = true;
                    builder.friendUserIds = Arrays.asList(205, 211);
                }).build();
        User user3 = User.builder(3, "Charlie")
                .with(builder -> {
                    builder.emailAddress = "charlie@email.com";
                    builder.isVerified = true;
                    builder.friendUserIds = Arrays.asList(201, 205, 211, 223);
                }).build();
        List<User> users = Arrays.asList(user1, user2, user2);

        EmailSender emailSender = new EmailSender();
        EmailProvider verifyYourEmailProvider = new VerifyYourEmailProvider();
        EmailProvider makeMoreFriendsEmailProvider = new MakeMoreFriendsEmailProvider();

        // 이메일 검증 전략 선택
        emailSender.setEmailProvider(verifyYourEmailProvider);
        users.stream()
                .filter(user -> !user.isVerified())
                .forEach(emailSender::sendEmail);

        // 친구초대 이메일 전략 선택
        emailSender.setEmailProvider(makeMoreFriendsEmailProvider);
        users.stream()
                .filter(User::isVerified)
                .filter(user -> user.getFriendUserIds().size() <= 5)
                .forEach(emailSender::sendEmail);

        // EmailProvider 는 메소드가 1개뿐인 Functional Interface 이므로
        // emailSender 의 전략을 아래와 같이 실시간으로 생성할 수 있다
        emailSender.setEmailProvider(user -> "'Play with Friends' email for " + user.getName());

    }
}
