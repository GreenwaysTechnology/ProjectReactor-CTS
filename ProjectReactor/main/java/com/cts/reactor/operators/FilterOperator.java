package com.cts.reactor.operators;

import reactor.core.publisher.Flux;

public class FilterOperator {
    public static void main(String[] args) {
        Flux<Integer> numbers = Flux.range(1, 10);
        //filter
//        numbers.filter(i->{
//            return i%2 ==0;
//        }).log().subscribe();
        numbers.filter(i -> i % 2 == 0).log().subscribe();

    }
}
