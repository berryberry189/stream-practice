package com.grace.streampractice.chap4;

import com.grace.streampractice.chap4.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Chapter4Comparator {
    // Comparator : 비교를 위한 인터페이스
    // input2개를 받아서 어떤것이 더 앞으로 가야하는지 integer를 리턴하며 알려주는 interface

    public static void main(String[] args) {

        List<User> users = new ArrayList<>();
        users.add(new User(3, "Alice"));
        users.add(new User(1, "Charlie"));
        users.add(new User(5, "Bob"));
        System.out.println(users);

        // id 순서대로 sort
        Comparator<User> idComparator = (u1, u2) -> u1.getId() - u2.getId();
        Collections.sort(users, idComparator);
        System.out.println(users);

        // 이름 순서대로 sort
        Collections.sort(users, (u1, u2) -> u1.getName().compareTo(u2.getName()));
        System.out.println(users);
    }
}
