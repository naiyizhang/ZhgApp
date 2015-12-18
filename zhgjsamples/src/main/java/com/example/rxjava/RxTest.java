package com.example.rxjava;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Action2;
import rx.functions.Func1;

/**
 * Created by nyzhang on 2015/12/17.
 */
public class RxTest {
    public static void main(String[]args){
        test5();
    }


    private static void test5(){
        Observable.just("you are a boy").map(s -> s+"--dan").map(s1 -> s1.hashCode())
                .map(integer -> Integer.toString(integer)).subscribe(str->System.out.println(str));
    }
    private static void test4(){
        Observable.just("you are good").map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                return s+"--dan";
            }
        }).subscribe(s -> System.out.println(s));
    }
    private static void test3(){
        Observable.just("==HelloWorld!==").subscribe(s -> System.out.println(s));
    }
    private static void test2(){
        Observable.just("Hello World!").subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });
    }
    private static  void test1(){
        Observable<String> observable=Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("hello world!");
                subscriber.onCompleted();
            }
        });
        Subscriber<String> subscriber=new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                System.out.println("======"+s+"============");
            }
        };
        observable.subscribe(subscriber);
    }
}
