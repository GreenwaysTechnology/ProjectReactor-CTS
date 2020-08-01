package com.cts.reactor.operators;

import reactor.core.publisher.Flux;

public class FlatMapOperator {
    public static void main(String[] args) {
        Flux<String> stream1 = Flux.just("Hello");
        stream1.map(item -> item).log().subscribe();
        stream1.flatMap(item -> {
            return Flux.just(item + "World");
        }).log().subscribe(System.out::println);
    }
}
