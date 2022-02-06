package com.grace.streampractice.chap4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Chapter4Predicate {
    public static void main(String[] args) {

        // Predicate : true or false 반환
        Predicate<Integer> isPositive = x -> x>0;
        System.out.println(isPositive.test(10));
        System.out.println(isPositive.test(-10));

        List<Integer> inputs = Arrays.asList(1, -5, 10, 7, -3, 0);
        System.out.println("Positive numbers -> " + filter(inputs, isPositive));

        // negate 예제
        System.out.println("Non Positive numbers -> " + filter(inputs, isPositive.negate()));

        // or 예제 (양수와 0인 경우도 포함 하는 Predicate)
        System.out.println("Positive numbers -> " + filter(inputs, isPositive.or(x -> x == 0)));

        // and 예제 (양수 중 짝수만 리턴하는 Predicate)
        System.out.println("Even Positive numbers -> " + filter(inputs, isPositive.and(x -> x%2 == 0)));

    }

    public static <T> List<T> filter(List<T> inputs, Predicate<T> condition){
        List<T> output = new ArrayList<>();
        for (T input : inputs) {
            if(condition.test(input)){
                output.add(input);
            }
        }
        return output;
    }
}
