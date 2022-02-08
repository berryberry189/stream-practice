package com.grace.streampractice.chap5;

import com.grace.streampractice.chap4.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;

public class Chapter5MethodReference2 {
    public static void main(String[] args) {
        // 3. ClassName::instanceMethodName 객체의 instance method 를 지정할 때
        // 클래스의 인스턴스를 매개변수로 넘겨 메서드르 실행해 주는 함수
        Function<String, Integer> strLength = String::length;
        System.out.println(strLength.apply("hello"));

        BiPredicate<String, String> strEquals = String::equals;
        String hello = "hello";
        System.out.println(strEquals.test(hello, "hello"));

        List<User> users = new ArrayList<>();
        users.add(new User(3, "Alice"));
        users.add(new User(1, "Charlie"));
        users.add(new User(5, "Bob"));
        System.out.println(users);

        printUserField(users, User::getId);


        // 4. ClassName::new 클래스의 constructor 를 지정할 때

    }

    public static void printUserField(List<User> users, Function<User, Object> getter){
        for (User user : users) {
            System.out.println(getter.apply(user));
        }
    }
}
