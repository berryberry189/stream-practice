package com.grace.streampractice.chap10;

import com.grace.streampractice.chap10.model.User;
import java.time.LocalDateTime;

public class Chapter10BuilderPattern {

  // 대표적인 생성 패턴
  // 객체의 생성에대한 로직과 표현에 대한 로직을 분리해준다
  // Setter 는 원치않은 변경이 일어날 수 있기때문에 지양

  public static void main(String[] args) {
    // 기존 방법
    User user1 = User.builder(1, "Alice")
        .withEmailAddress("alice@email.com")
        .withVerified(true)
        .withCreatedAt(LocalDateTime.now())
        .build();

    // Consumer 방법
    User user2 = User.builder(1, "Alice")
        .with(builder -> {
          builder.emailAddress = "alice@email.com";
          builder.isVerified = true;
        }).build();

    System.out.println(" +++++++++++++++++++++++ 1");
    System.out.println(user1);
    System.out.println(" +++++++++++++++++++++++ 2");
    System.out.println(user2);


  }

}
