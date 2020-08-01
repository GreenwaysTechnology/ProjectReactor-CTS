package com.cts.reactor;

import reactor.core.publisher.DirectProcessor;
import reactor.core.publisher.Flux;

public class HotStreamUsingDirectProcessor {
    public static void delay(String message, long timer) {
        try {
            System.out.println(message);
            Thread.sleep(timer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        //Hot Stream
        DirectProcessor hotSource = DirectProcessor.create();
        //Flux create
        Flux<String> hotFlux = hotSource;

        delay("delay",5000);

        //First subscriber
        hotFlux.doOnSubscribe(subscription -> {
            System.out.println("Subramanian Subscribed");
        }).doOnNext(data->{
            System.out.println("Subramaian's data " + data);
        }).doOnComplete(() -> {
            System.out.println("Subramanian done!!");
        }).log().subscribe();


        delay("delay",5000);

        //publish data
        hotSource.onNext("Hello");
        hotSource.onNext("Hai");

        //First subscriber
        hotFlux.doOnSubscribe(subscription -> {
            System.out.println("Karthik Subscribed");
        }).doOnNext(data->{
            System.out.println("Kathik data " + data);
        }).doOnComplete(() -> {
            System.out.println("Kathik done !!");
        }).log().subscribe();
        delay("delay",5000);

        hotSource.onNext("welcome");
        hotSource.onNext("how are you?");

    }
}
