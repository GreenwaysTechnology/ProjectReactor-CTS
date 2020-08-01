package com.cts.operators.delay;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class SimpleThreading {
    public static void main(String[] args) {
        Scheduler mys = Schedulers.newSingle("test");
        Mono.just("Hello, I am delayed")
                .delayElement(Duration.ofSeconds(2),Schedulers.newElastic("xxx"))
                .doOnSuccess(System.out::println)
                .log()
                .block(Duration.ofSeconds(10)); //block this thread until result is available.

    }
}
