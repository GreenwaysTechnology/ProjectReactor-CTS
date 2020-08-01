package com.cts.reactor.errhandling;

import reactor.core.publisher.Flux;

public class SimpleErrorHandling {
    private static String doSomething(int i) {
        // System.out.println(i);
        if (i == 5) {
            throw new RuntimeException("sorry");
        }
        return Integer.toString(i);
    }

    public static void main(String[] args) {
        //legacy code
        try {
            for (int i = 1; i < 10; i++) {
                String v1 = doSomething(i);
                System.out.println(v1);
            }

        } catch (Throwable a) {
            System.out.println("CAUGHT" + a);
        }
        //Reactive way
        Flux<String> f = Flux.range(1, 10)
                .map(v -> doSomething(v));
        f.subscribe(v -> System.out.println(v), err -> System.out.println("CAUGHT" + err.getMessage()));

        //STATIC FALLBACK VALUE
        /**
         *
         try {
         for (int i = 1; i < 10; i++) {
         String v1 = doSomething(i);
         System.out.println(v1);
         }

         } catch (Throwable a) {
         return "RECOVERED";
         }
         */
        //return static fallback error message
        Flux.range(1, 10)
                .map(v -> doSomething(v))
                .onErrorReturn("RECOVERED")
                .subscribe(v -> System.out.println(v), err -> System.out.println(err));

        Flux.range(1, 10)
                .map(v -> doSomething(v))
                .onErrorReturn("FALLBACK") // FALLBACK MESSAGE CAN BE RETURNED BASED ON CONDITION
                .doFinally((e) -> {
                    System.out.println("finally");
                })
                .subscribe(v -> System.out.println(v), err -> System.out.println(err));

        /**
         * Fallback method
         *   try {
         *          for (int i = 1; i < 10; i++) {
         *          String v1 = doSomething(i);
         *          System.out.println(v1);
         *          }
         *
         *          } catch (Throwable a) {
         *             result = getFromCache("key1");
         *          }
         */
//        Flux.range(1, 10)
//              .flatMap(value->doSomething(value)).onErrorResume(er->getFromCache(e));

        //catch and rethrow
        // Flux.range(1, 10).flatMap(value->doSomething(value)).onErrorResume(err->
        //   Flux.error(new CustomException("sss",err))
        // );
    }


}
