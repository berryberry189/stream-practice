package com.grace.streampractice.chap8;

import java.util.Optional;
import java.util.stream.Stream;

public class Chapter8FindFirstFindAny {
    // findFirst : Stream 안의 첫번째 데이터를 반환. 비어있다면 비어있는 Optional 을 반환
    // findAny : Stream 안의 아무 데이터나 반환. 순서가 중요하지 않고 Parallel Stream 을 사용할 때 최적화를 할 수 있다.
    // => 보통 Filter 와 연계해서 많이 사용함
    public static void main(String[] args) {
        Optional<Integer> anyNegativeInteger = Stream.of(5, 7, 8, 2, -4, 9)
                .filter(x -> x < 0)
                .findAny();
        System.out.println(anyNegativeInteger);

        Optional<Integer> firstPositiveInteger = Stream.of(5, 7, 8, 2, -4, 9)
                .filter(x -> x > 0)
                .findFirst();
        System.out.println(firstPositiveInteger);
    }
}
