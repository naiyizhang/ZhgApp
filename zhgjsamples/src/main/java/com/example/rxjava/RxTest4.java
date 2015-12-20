package com.example.rxjava;

import rx.Observable;

/**
 * Created by nyzhang on 2015/12/20.
 */
public class RxTest4 {
    public static void main(String[]args){
        Observable.just("one","two","three").subscribe(s -> System.out.println(s));
    }
}
