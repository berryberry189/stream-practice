package com.grace.streampractice.chap3;

import java.util.function.BiFunction;

public class Chapter3BiFunction {

    public static void main(String[] args) {

        // BiFunction 파라미터 2일 경우 <input1, input2, return type>
        BiFunction<Integer, Integer, Integer> add1 = (Integer x, Integer y) -> {
            return x + y;
        };
        // add1 과 add2 는 동일
        BiFunction<Integer, Integer, Integer> add2 = (x, y) -> x + y;
        int result = add1.apply(3, 5);
        System.out.println("result > "+result);
    }
}
