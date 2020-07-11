package com.cts.reactor;


import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import lombok.extern.slf4j.Slf4j;


class Subscriber implements Observer{
    @Override
    public void onSubscribe(@NonNull Disposable d) {
        System.out.println("On Subscribe");
      //  d.dispose();
    }

    //if publisher sends data event and data , onNext method is called
    @Override
    public void onNext(@NonNull Object o) {
        System.out.println("Data " + o);
    }

    //if publisher sends error , onError method is called
    @Override
    public void onError(@NonNull Throwable e) {
        System.out.println("Error" + e.getMessage());
    }

    //if publisher finishes sending data
    @Override
    public void onComplete() {
        System.out.println("done!");
    }
}
@Slf4j
public class RxjavaDemo {
    public static void main(String[] args) {
        //Create Publisher(Observable-Stream)

        Observable publisher = Observable.just("Hello","Hai","Welcome","How are you?");
        //Subscriber
        publisher.subscribe(new Subscriber());

        //functional style of subscriber
        Observable<Integer> numberPublisher = Observable.just(1,2,3,4,5,6,7,8);

        //Subscriber : annomous class
        numberPublisher.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("On Subscribe - Number Publisher");

            }
            @Override
            public void onNext(@NonNull Integer integer) {
                log.info("Nos :" +integer);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                log.error(e.getMessage());
            }

            @Override
            public void onComplete() {
                log.info("Done!!!");
            }
        });
        //lambdas
        Observable<Integer> numberPublisherLambda = Observable.just(1,2,3,4,5,6,7,8);
        numberPublisherLambda.subscribe(data->log.info("Lambda " + data),err->{log.error(err.getMessage());},()->log.info("lambda done!"));

        ///////Create Publisher , who pushes data,error,complete

        Observable<String> createPublisher=Observable.create(stream->{
            //push data into stream
            stream.onNext("Hello");
            stream.onNext("Hai");
            stream.onNext("welcome");
            stream.onError(new RuntimeException("something went wrong!!!!"));
            stream.onNext("greeter");
            stream.onNext("very good");
            stream.onComplete();
        });
        createPublisher.subscribe(data->log.info("Create " + data),err->{log.error(err.getMessage());},()->log.info("Create done!"));



    }
}
