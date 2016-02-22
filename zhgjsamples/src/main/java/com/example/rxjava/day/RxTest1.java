package com.example.rxjava.day;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.subjects.AsyncSubject;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import rx.subjects.ReplaySubject;

/**
 * Created by nyzhang on 2016/1/14.
 */
public class RxTest1 {
    public static void main(String[]args){
        subject4();
    }
    private static void subject5(){
        AsyncSubject<Integer> asyncSubject=AsyncSubject.create();
        asyncSubject.subscribe(integer -> {System.out.println("item is "+integer);});
        asyncSubject.onNext(1);
        asyncSubject.onNext(2);
        asyncSubject.onNext(3);
        asyncSubject.onCompleted();
    }

    private static void subject4(){
        ReplaySubject<Integer> subject=ReplaySubject.create();
        Subscription subscription=subject.subscribe(integer -> {System.out.println("item is "+integer);},error->{},()->{});
        subject.onNext(10);
        subject.onCompleted();
    }
    private static void subject3(){
        BehaviorSubject<Integer> subject=BehaviorSubject.create(10);
        Subscription subscription=subject.subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("complete");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("error occur");
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("item is "+integer);
            }
        });
        subject.onNext(1);
    }

    private static void subject2(){
        final PublishSubject<Boolean> subject=PublishSubject.create();
        subject.subscribe(new Observer<Boolean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Boolean aBoolean) {
                System.out.println("Observable complete");
            }
        });

        Observable.<Integer>create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for(int i=0;i<5;i++){
                    subscriber.onNext(i);
                }
                subscriber.onCompleted();;
            }
        }).doOnCompleted(new Action0() {
            @Override
            public void call() {
                subject.onNext(true);
            }
        }).subscribe();
    }
    private static void subject1(){
        PublishSubject<String> stringPublishSubject=PublishSubject.create();
        Subscription subscription=stringPublishSubject.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("Observer completed");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("oh on ,something wrong happend");
            }

            @Override
            public void onNext(String o) {
                System.out.println("item is " + o);
            }
        });
        stringPublishSubject.onNext("HelloWorld!");
    }
}
