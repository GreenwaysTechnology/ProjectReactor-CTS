package com.cts.reactor;

import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

public class FluxCollections {
    public static void main(String[] args) {
        //List as data Source
        List<Integer> numList = Arrays.asList(1,2,3,4,5,57);
        Flux<Integer> producerList= Flux.fromIterable(numList);
        producerList.log().subscribe();

        Integer [] data = {1,2,3,4,5};
        Flux<Integer> producerArray= Flux.fromArray(data);
        producerArray.log().subscribe();

        //sequence of numbers from start to end : range
        Flux<Integer> rangeProducer = Flux.range(1,100);
        rangeProducer.log().subscribe();


    }
}
