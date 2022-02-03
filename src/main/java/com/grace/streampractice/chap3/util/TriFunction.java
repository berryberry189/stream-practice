package com.grace.streampractice.chap3.util;

// 파라미터 3개 이상을 받는 Function 인터페이스는 없으므로 직접 만들기
@FunctionalInterface
public interface TriFunction <T, U, V, R>{
    R apply(T t, U u, V v);
}
