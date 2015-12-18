package com.example.rxjava;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;

/**
 * Created by nyzhang on 2015/12/18.
 */
public class RxTest3 {
    public static void main(String[]args){
        test3();
    }
    private static void test3(){
       Subscription subscription= Observable.just("test1").map(s->s+"--dan")
                .subscribe(s->System.out.println(s));
        subscription.unsubscribe();
        System.out.println("subscription status:"+subscription.isUnsubscribed());
    }
    private static void test2(){

        Observable.create(subscriber -> {System.out.println(Thread.currentThread().getName());subscriber.onNext("nihao");subscriber.onCompleted();})
                .subscribeOn(Schedulers.newThread()).observeOn(Schedulers.io()).subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                System.out.println(e.getMessage());
            }

            @Override
            public void onNext(Object o) {
                System.out.println(Thread.currentThread().getName() + ":" + o);
            }
        });
        System.out.println("first:" + Thread.currentThread().getName());
    }
    private static void test(){
        Observable.just("helloworld").map(s -> pointerExceptoin(s))
                .map(s -> anotherPointerException(s)).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("completed");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError:"+e.getMessage());
            }

            @Override
            public void onNext(String s) {
                System.out.println("onNext:"+s);
            }
        });
    }

    private static String anotherPointerException(String s) {
        throw new IllegalArgumentException("illegal exception");
    }

    private static String pointerExceptoin(String s){
        throw new RuntimeException("error occur");
    }

}
