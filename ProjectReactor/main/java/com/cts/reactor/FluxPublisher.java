package com.cts.reactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class FluxPublisher {
    public static void main(String[] args) {
        //create Producer from scratch
        Flux<String> producer = Flux.create(fluxSink -> {
            fluxSink.next("Hello reactor");
            fluxSink.next("Hello reactor");
            fluxSink.next("Hello reactor");
            fluxSink.next("Hello reactor");
            fluxSink.next("Hello reactor");
            fluxSink.next("Hello reactor");
            fluxSink.complete();

        });
        producer.log().subscribe();

        //Create Producer from biz logic
        Flux<String> loginProducer = Flux.create(fluxSink->{
            String username ="admin";
            String password = "adminxx";
            if(username.equals("admin") && password.equals("admin")){
                fluxSink.next("Login Success");
            }else{
                fluxSink.error(new RuntimeException("Login Failed"));
            }
            fluxSink.complete();
        });

        loginProducer.log().subscribe();


    }
}
