package com.cts.reactor;

import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

public class SubscriptionDemo {
    public static void main(String[] args) {
        Flux<Integer> producer = Flux.create(fluxSink -> {
            //push values into sink :
            fluxSink.next(1);
            fluxSink.next(2);
            fluxSink.next(3);
            fluxSink.next(4);
            //throw new RuntimeException("something went wrong!!");
            fluxSink.complete();
        });
        //subscriber
        // producer.subscribe();
        //only data ; lambda expression
//        producer.log().subscribe(data->{
//            System.out.println(data);
//        });
        //data and error
//        producer.subscribe(data->{
//            System.out.println(data);
//        },err->{
//            System.out.println(err);
//        });
        //data , error, complete signal
//        producer.subscribe(data->{
//            System.out.println(data);
//        },err->{
//            System.out.println(err);
//        },()-> System.out.println("Stream Completed"));

        //method reference
        //producer.subscribe(System.out::println, System.out::println, () -> System.out.println("Stream Completed"));
        //Subscriber interface : anonymous way

//        producer.log().subscribe(new Subscriber<Integer>() {
//            @Override
//            public void onSubscribe(Subscription s) {
//                System.out.println("Subscription is done");
//                //ask the upstream how many elements you want
//                //s.request(3); //requests only 3 elements
//                //request all elements
//                s.request(Long.MAX_VALUE); // request(unbound)
//            }
//
//            @Override
//            public void onNext(Integer value) {
//                System.out.println("Data " + value);
//
//            }
//
//            @Override
//            public void onError(Throwable t) {
//                System.out.println("Error  " + t.getMessage());
//
//            }
//
//            @Override
//            public void onComplete() {
//                System.out.println("Completed ");
//
//            }
//        });
        //lambda with subscription
//        producer.subscribe(data -> {
//            System.out.println(data);
//        }, err -> {
//            System.out.println(err);
//        }, () -> System.out.println("Stream Completed"),subscription -> {
//            subscription.request(2);
//        });

        //lifecycle methods via utility methods

//        producer.doOnSubscribe(subscription -> {
//            System.out.println("doOn Subscription");
//        }).doOnNext(data -> {
//            System.out.println("doOnNext " + data);
//        }).doOnRequest(n->{
//            System.out.println(Long.MAX_VALUE == n);
//        }).doOnError(err -> {
//            System.out.println("doOnError " + err);
//        }).doOnComplete(() -> {
//            System.out.println("doOnComplete");
//        }).log().subscribe();

        Flux.range(1,10).doOnSubscribe(sub -> {
            System.out.println("doOn Subscription");
        }).doOnNext(data -> {
            System.out.println("doOnNext " + data);
        }).doOnRequest(n->{
            System.out.println( n);
        }).doOnError(err -> {
            System.out.println("doOnError " + err);
        }).doOnComplete(() -> {
            System.out.println("doOnComplete");
        }).log().subscribe();


    }
}
