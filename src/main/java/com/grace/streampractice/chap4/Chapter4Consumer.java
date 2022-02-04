package com.grace.streampractice.chap4;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Chapter4Consumer {
    public static void main(String[] args) {
        // Consumer: 리턴 값 없음
        Consumer<String> myStringConsumer = (String str) ->
            System.out.println(str);

        myStringConsumer.accept("hello!");

        //
        List<Integer> integerInputs = Arrays.asList(4,2,3);
        Consumer<Integer> myIntegerProcessor = x -> System.out.println("Processing Integer " + x);
        process(integerInputs, myIntegerProcessor);
        Consumer<Integer> myDifferentIntegerProcessor = x ->
                System.out.println("Processing Integer in different way " + x);
        process(integerInputs, myDifferentIntegerProcessor);

        Consumer<Double> myDoubleProcessor = x ->
                System.out.println("Processing Double " + x);
        List<Double> doubleInputs = Arrays.asList(1.1, 2.2, 3.3);
        process(doubleInputs, myDoubleProcessor);
    }

    public static <T> void process(List<T> inputs, Consumer<T> processor){
        for (T input : inputs) {
            processor.accept(input);
        }
    }
}
