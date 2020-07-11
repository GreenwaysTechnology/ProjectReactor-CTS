package com.cts.reactor;

import reactor.core.publisher.Flux;

public class FluxCreate {
    public static void main(String[] args) {
        Flux flux = Flux.create(sink->{
            sink.next(10);
            sink.next(12);
            throw new RuntimeException("err");
           // sink.next(80);
          //  sink.complete();
        });
        flux.subscribe(System.out::println,System.out::println,()->{
            System.out.println("done");
        });
    }
}
