package com.grace.streampractice.chap5;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class Chapter5MethodReference {

    public static int calculate(int x, int y, BiFunction<Integer, Integer, Integer> operator){
        return operator.apply(x, y);
    }

    public static int multiply(int x, int y){
        return x * y;
    }

    public int subtract(int x, int y){
        return x - y;
    }

    public void myMethod(){
        System.out.println(calculate(10, 3, this::subtract));
    }

    public static void main(String[] args) {
        // int a =  Integer.parseInt("15");
        Function<String, Integer> strToInt = Integer::parseInt;
        System.out.println(strToInt.apply("20"));

        String str = "hello";
        // boolean b = str.equals("hello");
        Predicate<String> equalsToStr = str::equals;
        System.out.println(equalsToStr.test("hello"));


        System.out.println(calculate(8,2, (x,y) -> x+y));
        // static 메소드를 호출하는 경우
        System.out.println(calculate(8, 2, Chapter5MethodReference::multiply));

        // static 이 아닌 메소드 호출하는 경우
        Chapter5MethodReference instance = new Chapter5MethodReference();
        System.out.println(calculate(8, 2, instance::subtract));

        instance.myMethod();

    }
}
