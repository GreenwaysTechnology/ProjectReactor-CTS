package com.cts.reactor.operators;

import reactor.core.publisher.Flux;

import java.util.function.Function;
import java.util.function.Predicate;

public class MapFilterOperator {
    public static void main(String[] args) {
        Function<Integer, Integer> multiplyByTen = (x) -> x * 10;
        Predicate<Integer> testEven = x -> x % 2 == 0;
        Flux<Integer> numbers = Flux.range(1, 10);
        //multiple operators
        numbers.map(multiplyByTen).filter(testEven).subscribe(System.out::println);


    }
}
