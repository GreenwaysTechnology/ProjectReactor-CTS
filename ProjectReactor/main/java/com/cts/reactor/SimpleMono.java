package com.cts.reactor;

import reactor.core.publisher.Mono;

public class SimpleMono {
    public static void main(String[] args) {
        //Similar to Flux : it returns o--1
        Mono<String> single = Mono.create(monoSink -> {
            monoSink.success("Hello");
            // monoSink.success("Hai");
        });
        //single
        single.subscribe(data -> {
            System.out.println("data " + data);
        }, err -> System.out.println("Errror " + err.getMessage()));

        Mono.just("single Vale").subscribe(System.out::println);
        Mono.empty().log().subscribe();
        Mono.error(new RuntimeException("Hello")).log().subscribe();


    }

}
