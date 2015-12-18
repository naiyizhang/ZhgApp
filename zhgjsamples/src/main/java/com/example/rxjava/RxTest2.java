package com.example.rxjava;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by nyzhang on 2015/12/17.
 */
public class RxTest2 {
    public static void main(String[]args){
        test4();
    }
    private static void test3(){
        query("test3").flatMap(urls->Observable.from(urls)).flatMap(new Func1<String, Observable<String>>() {
            @Override
            public Observable<String> call(String s) {
                return getTitle(s);
            }
        }).subscribe(s -> System.out.println(s));
    }
    private static void test4(){
        query("test4").flatMap(urls->Observable.from(urls)).flatMap(url->getTitle(url))
                .filter(title->title!=null).take(2).doOnNext(title->saveTitle(title))
                .subscribe(s -> System.out.println(s));
    }
    private static void test2(){
        query("test2").flatMap(list->Observable.from(list)).subscribe(s -> System.out.println(s));
    }

    private static void test1(){
        query("test1").flatMap(new Func1<List<String>, Observable<String>>() {
            @Override
            public Observable<String> call(List<String> strings) {
                return Observable.from(strings);
            }
        }).subscribe(s -> System.out.println(s));
    }

    private static Observable<List<String>> query(String str){
        List<String> array=new ArrayList<>();
        array.add("title1");
        array.add("title2");
        array.add("title3");
        array.add("title4");
        return Observable.create(subscriber -> {subscriber.onNext(array);subscriber.onCompleted();});
    }
    private static Observable<String> getTitle(String str){
        if(str.equals("title1"))
            return Observable.just(null);
        return Observable.just(str);
    }
    private static void saveTitle(String title){
        System.out.println("save "+title);
    }
}
