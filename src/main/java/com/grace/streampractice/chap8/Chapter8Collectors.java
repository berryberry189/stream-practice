package com.grace.streampractice.chap8;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Chapter8Collectors {
    public static void main(String[] args) {
        List<Integer> numberList = Stream.of(3, 4, 5, 7, -2, 4)
                .collect(Collectors.toList());
        System.out.println(numberList);

        Set<Integer> numberSet = Stream.of(3, 4, 5, 7, -2, 4)
                .collect(Collectors.toSet());
        System.out.println(numberSet);

        // 절대값을 찾은 (map) 후 List 형태로 변경
        List<Integer> numberList2 = Stream.of(3, 4, 5, 7, -2, 4)
                .collect(Collectors.mapping(x -> Math.abs(x), Collectors.toList()));
        System.out.println(numberList2);


        // reduce
        int sum = Stream.of(3, 4, 5, 7, -2, 4)
                .collect(Collectors.reducing(0, (x, y) -> x + y));
        System.out.println(sum);

    }
}
