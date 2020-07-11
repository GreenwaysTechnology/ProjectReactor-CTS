package com.cts.reactor;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscription;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Mono;

@Slf4j
public class PublisherSubscriber {
    public static void main(String[] args) {

        Flux<Integer> evenNumbers = Flux
                .range(1, 10)
                .filter(x -> x % 2 == 0); // i.e. 2, 4

        Flux<Integer> oddNumbers = Flux
                .range(1, 10)
                .filter(x -> x % 2 > 0);  // ie. 1, 3, 5

        Flux<Integer> fluxOfIntegers = Flux.zip(
                evenNumbers,
                oddNumbers,
                (a, b) ->{
                    System.out.println(a + "-----" + b);
                    return a + b;});
        fluxOfIntegers.subscribe(System.out::println);

        Mono.just(10).log()
                .subscribe();

        Flux.just(10,12,67)
                .log()
                .subscribe(new CoreSubscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {
                    s.request(2);
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println(integer);
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t);
            }

            @Override
            public void onComplete() {
                System.out.println("complete");
            }
        });

        Flux.error(new RuntimeException("something went wrong"))
                .log()
                .subscribe();
    }
}
