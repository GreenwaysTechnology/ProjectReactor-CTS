package com.cts.reactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class MonoTest {
    public static void main(String[] args) {
        Mono<String> mono = Mono.just("Hello").log();
       mono.subscribe();

//        Flux.just(1000,900).log().subscribe();
//        Flux.range(1,10).log().subscribe(null,null,null,subscription -> {
//            subscription.request(Long.MAX_VALUE);
//        });

    }
}

