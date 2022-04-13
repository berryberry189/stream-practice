package com.grace.streampractice.chap9;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class Chapter9ScopeClosureCurry {

    // Scope : 변수에 접근할 수 있는 유효 범위
    // Closure : 내부 함수가 사용한 외부함수의 변수는 final 로 선언되지 않더라도 암묵적으로 final 로 취급한다.
    // Curry : 여러개의 매개변수를 받는 함수를 증첩된 여러 함수로 쪼개어 매개변수를 한번에 받지않고 여러 단계에 걸쳐 나눠받을 수 있게 하는 기술

    public static void main(String[] args) {

        Supplier<String> stingSupplier = getStingSupplier();
        System.out.println(stingSupplier.get());

        BiFunction<Integer, Integer, Integer> add = (x, y) -> x + y;
        // 위의 함수 curry
        Function<Integer, Function<Integer, Integer>> curriedAdd = x -> y -> x + y;

        Function<Integer, Integer> assThree = curriedAdd.apply(3);
        int result = assThree.apply(10); // -> 3
        System.out.println(result);

    }

    public static Supplier<String> getStingSupplier(){
        String hello = "Hello";
        Supplier<String> supplier = () -> {
            String world = "World";
            return hello + world;
        };
        return supplier;
    }
}
