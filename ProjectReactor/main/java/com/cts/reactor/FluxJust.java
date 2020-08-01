package com.cts.reactor;

import reactor.core.publisher.Flux;

public class FluxJust {
    public static void main(String[] args) {
        Flux<Integer> producer=Flux.just(1,2,3,4,5,6,7,8,9,10);
        producer.log().subscribe();
    }
}
