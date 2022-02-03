package com.grace.streampractice.chap3;

import com.grace.streampractice.chap3.util.TriFunction;

public class Chapter3TriFunction {

    public static void main(String[] args) {

        TriFunction<Integer, Integer, Integer, Integer> addThreeNumbers1 =
                (Integer x, Integer y, Integer z) -> {
            return x + y + z;
        };

        TriFunction<Integer, Integer, Integer, Integer> addThreeNumbers2 = (x, y, z) -> x + y + z;

        int result = addThreeNumbers1.apply(3, 2, 5);
        System.out.println("result > "+ result);
    }
}
