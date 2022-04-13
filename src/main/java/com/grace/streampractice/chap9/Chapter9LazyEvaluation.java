package com.grace.streampractice.chap9;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Chapter9LazyEvaluation {
    // Lazy Evaluation : Lambda의 계산은 미루다가 그 결과값이 꼭 필요해 질 때가 되서야 계산한다.

    public static void main(String[] args) {

        if(true || returnFalse()){ // 앞의 조건이 true 이기 때문에 returnFalse 는 타지도 않음
            System.out.println("true");
        }

        if(or(returnTrue(), returnFalse())){ // 둘 다 체크해야함
            System.out.println("true");
        }

        // 최적화를 하고 싶은 경우에는!!
        if(lazyOr(()->returnTrue(), ()->returnFalse())){
            System.out.println("true");
        }

        Stream<Integer> integerStream = Stream.of(3, -4, 4, 8, -2)
                .filter(x -> x > 0)
                .peek(x -> System.out.println("peeking" + x)) // peek : 실행만하고 넘어감
                .filter(x -> x % 2 == 0);

        System.out.println(" === Before Collect === ");
        List<Integer> integerList = integerStream.collect(Collectors.toList());
        System.out.println(" === After Collect === ");
        System.out.println(integerList);

        // ** 결과 ** //
        // === Before Collect ===
        //peeking3
        //peeking4
        //peeking8
        // === After Collect ===
        //[4, 8]
        // => Stream 은 종결처리가 이루어 지기 전까지는 모든 계산을 미룬다. ( 콜렉트 후에 피킹이 일어남 )

    }

    public static boolean or(boolean x, boolean y){
        return x || y;
    }

    // 최적화
    public static boolean lazyOr(Supplier<Boolean> x, Supplier<Boolean> y){
        return x.get() || y.get();
    }

    public static boolean returnTrue(){
        System.out.println("returning true");
        return true;
    }
    public static boolean returnFalse(){
        System.out.println("returning false");
        return false;
    }
}
