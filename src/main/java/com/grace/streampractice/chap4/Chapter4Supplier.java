package com.grace.streampractice.chap4;

import java.util.function.Supplier;

public class Chapter4Supplier {
    public static void main(String[] args) {

        // Supplier 인터패이스는 파라미터가 없음. 리턴값만 지정해주면 된다.
        Supplier<String> myStringSupplier = () -> "Hello World!";
        System.out.println("myStringSupplier > " + myStringSupplier.get());

        Supplier<Double> myRandomDoubleSupplier = () -> Math.random();
        System.out.println("myRandomDoubleSupplier > " +  myRandomDoubleSupplier.get());

        printRandomDoubles(myRandomDoubleSupplier, 5);
    }

    public static void printRandomDoubles (Supplier<Double> myRandomDoubleSupplier, int count) {
        for(int i=0; i<count; i++){
            System.out.println("printRandomDoubles" + i +" > " +  myRandomDoubleSupplier.get());
        }
    }
}
