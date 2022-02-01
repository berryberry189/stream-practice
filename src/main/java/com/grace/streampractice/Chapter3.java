package com.grace.streampractice;

import com.grace.streampractice.util.Adder;

import java.util.function.Function;

public class Chapter3 {

    public static void main(String[] args) {
        Function<Integer, Integer> myAdder = new Adder();
        int result = myAdder.apply(5);
        System.out.println(result);
    }
}
