package com.grace.streampractice.chap7;

import com.grace.streampractice.chap7.model.User;

import java.util.Optional;

public class Chapter7Optional2 {
    public static void main(String[] args) {

        Optional<User> maybeUser = Optional.ofNullable(maybeGetUser(false));
        // user 가 있다면 user를 출력
        maybeUser.ifPresent(user -> System.out.println(user));


        Optional<Integer> maybeId = Optional.ofNullable(maybeGetUser(false))
                .map(user -> user.getId());
        maybeId.ifPresent(System.out::println);

        String userName = Optional.ofNullable(maybeGetUser(true))
                .map(user -> user.getName())
                .map(name -> "The name is " + name)
                .orElse("Name is empty");
        System.out.println(userName);

        // optional 안에 optional 있을 겨우 flatmap 사용
        Optional<Optional<String>> optionalEmail = Optional.ofNullable(maybeGetUser(false))
                .map(user -> user.getEmailAddress());

        Optional<String> flatmapOptionalEmail = Optional.ofNullable(maybeGetUser(true))
                .flatMap(user -> user.getEmailAddress());
        flatmapOptionalEmail.ifPresent(System.out::println);

    }

    public static User maybeGetUser(boolean returnUser){
        if(returnUser){
            return new User()
                    .setId(1001)
                    .setName("Alice")
                    .setEmailAddress("alice@email")
                    .setVerified(true);
        }
        return null;
    }
}
