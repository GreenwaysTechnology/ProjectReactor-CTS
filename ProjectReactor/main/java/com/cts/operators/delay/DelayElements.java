package com.cts.operators.delay;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class DelayElements {
    public static void main(String[] args) throws InterruptedException {
        //return element after 2 ms
        Mono.just("Hello, I am delayed")
                .delayElement(Duration.ofSeconds(2))
                .doOnSuccess(System.out::println)
                .log()
                .block(Duration.ofSeconds(10)); //block this thread until result is available.
        //here you have blocked main thread for 5ms.
        //  Thread.sleep(5000); //this is not good idea other envs.
        //emit value from 1 to 10 , in every 1 second
        Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(System.out::println)
                .blockLast();//wait until last element

        Flux.interval(Duration.ofSeconds(1))
                .doOnNext(i -> System.out.println("tick " + i))
                .blockLast();

    }
}
