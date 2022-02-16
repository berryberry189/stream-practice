package com.grace.streampractice.chap6;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Chapter6Stream {
    public static void main(String[] args) {
        Stream<String> nameStream = Stream.of("Alice", "Bob", "Charlie");
        List<String> names = nameStream.collect(Collectors.toList());
        System.out.println(names);

        String[] cityArr = new String[]{"LA", "Seoul", "Tokyo"};
        Stream<String> cityStream = Arrays.stream(cityArr);
        List<String> citys = cityStream.collect(Collectors.toList());
        System.out.println(citys);

        Set<Integer> integerSet = new HashSet<>(Arrays.asList(3, 5, 7));
        Stream<Integer> integerStream = integerSet.stream();
        List<Integer> integers = integerStream.collect(Collectors.toList());
        System.out.println(integers);
    }
}
