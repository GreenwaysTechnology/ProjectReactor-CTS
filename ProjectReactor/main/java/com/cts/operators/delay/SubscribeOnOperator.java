package com.cts.operators.delay;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

public class SubscribeOnOperator {
    public static void main(String[] args) {
        Scheduler s = Schedulers.newParallel("parallel-scheduler", 5);
        Flux<Integer> flux = Flux.range(1, 5)
                .map(i -> {
                    System.out.println(Thread.currentThread().getName() + " " + i);
                    return i * 10;
                })
                .subscribeOn(s)
                .map(i -> {
                    System.out.println(Thread.currentThread().getName());
                    return i + 2;
                });
        //new Thread(() -> flux.subscribe(System.out::println)).start();
        flux.subscribe(System.out::println);
    }
}
