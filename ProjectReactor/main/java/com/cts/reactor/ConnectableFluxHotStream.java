package com.cts.reactor;

import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

public class ConnectableFluxHotStream {

    public static void delay(String message, long timer) {
        try {
            System.out.println(message);
            Thread.sleep(timer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        //Create Cold Flux
        Flux<Integer> source = Flux.range(1, 5).doOnSubscribe(s -> {
            System.out.println("Subscribed to Source");
        });
        //ConnectableFlux : bridge between subscribers
        ConnectableFlux<Integer> bridge = source.publish();
        //subscribe
        bridge.subscribe(data -> {
            System.out.println("Subscriber 1 " + data);
        });

        bridge.subscribe(data -> {
            System.out.println("Subscriber 2 " + data);
        });
        System.out.println("Subscription done");
        delay("waiting to connect...",5000);
        System.out.println("Going to Connect");

        //just connect with upstream source for data emmions
        bridge.connect(); // autoConnect,refCount


    }

}
