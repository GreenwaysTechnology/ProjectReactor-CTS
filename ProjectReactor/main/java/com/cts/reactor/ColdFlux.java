package com.cts.reactor;

import reactor.core.publisher.Flux;

public class ColdFlux {

    public static void delay(String message, long timer) {
        try {
            System.out.println(message);
            Thread.sleep(timer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //producer
        Flux<Integer> producer = Flux.create(fluxSink -> {
            for (int i = 1; i <= 10; i++) {
                fluxSink.next(i);
            }
            fluxSink.complete();
        });
        //subscriber 1
        producer.doOnSubscribe(subscription -> {
            System.out.println("Subramanian has subscribed");
        }).doOnNext(data -> {
            System.out.println("Subramanian's " + data);
        }).doOnComplete(() -> {
            System.out.println("Subramanian done!!");
        }).subscribe();

        //susbcriber 2
        producer.doOnSubscribe(subscription -> {
            System.out.println("James has subscribed");
        }).doOnNext(data -> {
            System.out.println("James's " + data);
        }).doOnComplete(() -> {
            System.out.println("james done!!");
        }).subscribe();

        //simulate delay of joining
        //
        delay("waiting to join", 5000);

        producer.doOnSubscribe(subscription -> {
            System.out.println("Karthik has subscribed");
        }).doOnNext(data -> {
            System.out.println("Karthik's " + data);
        }).doOnComplete(() -> {
            System.out.println("Karthik done!!");
        }).subscribe();

    }
}
