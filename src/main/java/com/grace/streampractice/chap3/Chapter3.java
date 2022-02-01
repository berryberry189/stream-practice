package com.grace.streampractice.chap3;

import com.grace.streampractice.chap3.util.Adder;

import java.util.function.Function;

public class Chapter3 {

    public static void main(String[] args) {
        Function<Integer, Integer> myAdder = new Adder();

        // lambda expression (익명함수)
        Function<Integer, Integer> lambdaAdder = (Integer x) -> {
            return x + 10;
        };
        Function<Integer, Integer> lambdaAdder2 = x -> x + 10;

        int result = lambdaAdder2.apply(5);
        System.out.println(result);
    }
}
