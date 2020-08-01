package com.cts.reactor;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;


@Slf4j
class SampleSubscriber<T> extends BaseSubscriber<T> {

    public void hookOnSubscribe(Subscription subscription) {
        log.info("Subscribed");
        request(1);
        //request(Long.MAX_VALUE);

    }

    public void hookOnNext(T value) {
        System.out.println(value);
        request(Long.MAX_VALUE);
    }


}

@Slf4j
public class FluxDemo {
    private static void process(Object data) {
        log.info(data.toString());
    }

    public static void main(String[] args) {

        //Simple Flux
//        Flux.just(1, 2, 3, 4)
//                .subscribe(FluxDemo::process);
//
//        Flux.just(34, 78, 80).subscribe(new Consumer<Integer>() {
//            @Override
//            public void accept(Integer integer) {
//                log.info(integer.toString());
//            }
//        }).dispose();

//        Flux<Integer> ints = Flux.range(1, 3);
//        ints.subscribe();
//        Flux<Integer> ints = Flux.range(1, 4)
//                .map(i -> {
//                    if (i <= 3) return i;
//                    throw new RuntimeException("Got to 4");
//                });
//        ints.subscribe(i -> System.out.println(i),
//                error -> System.err.println("Error: " + error));

//        publisher.subscribe(new CoreSubscriber() {
//            @Override
//            public void onSubscribe(Subscription subscription) {
//                log.info("onSubscribe method");
//                subscription.request(6);
//            }
//
//            @Override
//            public void onNext(Object o) {
//                log.info(o.toString());
//            }
//
//            @Override
//            public void onError(Throwable throwable) {
//                log.error(throwable.getCause().getMessage());
//
//            }
//
//            @Override
//            public void onComplete() {
//              log.info("Completed");
//            }
//        });

//        Flux<Integer> ints = Flux.range(1, 15);
//        ints.subscribe(i -> System.out.println(i),
//                error -> System.err.println("Error " + error),
//                () -> System.out.println("Done"),
//                sub -> sub.request(13));

//        SampleSubscriber<Integer> ss = new SampleSubscriber<Integer>();
//        Flux<Integer> ints = Flux.range(1, 40);
////        ints.subscribe(i -> System.out.println(i),
////                error -> System.err.println("Error " + error),
////                () -> {System.out.println("Done");},
////                s -> s.request(10));
//        ints.subscribe(ss);

        Flux.range(1, 10)
                .doOnRequest(r -> System.out.println("request of " + r))
                .subscribe(new BaseSubscriber<Integer>() {

                    @Override
                    public void hookOnSubscribe(Subscription subscription) {
                        request(1);
                    }

                    @Override
                    public void hookOnNext(Integer integer) {
                        request(1);
                        System.out.println("Cancelling after having received " + integer);
                        cancel();
                    }
                });

    }
}
