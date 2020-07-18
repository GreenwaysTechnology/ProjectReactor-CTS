package com.cts.reactor.operators;

import reactor.core.publisher.Flux;

import java.util.function.Function;

public class MapOperator {
    public static void main(String[] args) {

        //lambda functions
        Function<Integer, Integer> multiplyByTen = (x) -> x * 10;
        Function<Integer, Integer> addbyTwo = (x) -> x + 2;
        Flux<Integer> numbers = Flux.range(1, 10);
        //
//        numbers.map(i->{
//            return i * 10;
//        }).log().subscribe();
        //numbers.map(i -> i * 10).map(x -> x + 2).log().subscribe();
        numbers.map(multiplyByTen).map(addbyTwo).log().subscribe();
    }
}
