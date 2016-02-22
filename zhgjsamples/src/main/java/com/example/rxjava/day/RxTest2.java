package com.example.rxjava.day;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.observables.GroupedObservable;
import rx.schedulers.Schedulers;
import sun.rmi.runtime.Log;

/**
 * Created by nyzhang on 2016/1/18.
 */
public class RxTest2 {
    public static void main(String[]args){
        test6();

    }

    private static void test6(){
        Observable<Integer> appObservable=Observable.interval(1,TimeUnit.SECONDS).subscribeOn(Schedulers.newThread()).map((aLong -> {
            return Integer.valueOf(aLong.toString());
        }));
        Observable<Long> observable1=Observable.interval(1,TimeUnit.SECONDS);
        appObservable.join(observable1, new Func1<Integer, Observable<Long>>() {
            @Override
            public Observable<Long> call(Integer integer) {
                return Observable.timer(2,TimeUnit.SECONDS);
            }
        }, new Func1<Long, Observable<Long>>() {
            @Override
            public Observable<Long> call(Long aLong) {
                return Observable.timer(0,TimeUnit.SECONDS);
            }
        }, new Func2<Integer, Long, Object>() {
            @Override
            public Object call(Integer integer, Long aLong) {
                return integer+":"+aLong;
            }
        }).subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {
                System.out.println(o);
            }
        });
    }

    private static void test5(){
        Entity e1=new Entity(1,"test1");
        Entity e2=new Entity(2,"test2");
        Entity e3=new Entity(3,"test3");
        Entity e4=new Entity(4,"test4");
        Entity e5=new Entity(5,"test5");
        List<Entity> list=new ArrayList<Entity>();
        list.add(e1);
        list.add(e2);
        list.add(e3);
        list.add(e4);
        list.add(e5);
        List<Entity> reverseList=new ArrayList<Entity>();
        reverseList.add(e5);
        reverseList.add(e4);
        reverseList.add(e3);
        reverseList.add(e2);
        reverseList.add(e1);
        Subscription subscription=Observable.merge(Observable.from(list), Observable.from(reverseList))
                .subscribe(entity -> System.out.println(entity));
    }

    private static void test4(){

        Entity e1=new Entity(1,"test1");
        Entity e2=new Entity(2,"test1");
        Entity e3=new Entity(3,"test1");
        Entity e4=new Entity(4,"test1");
        Entity e5=new Entity(5,"test1");
        Observable.just(e1, e2, e3, e4, e5).buffer(1, TimeUnit.SECONDS,2).subscribe(entities -> {

            System.out.println("time="+System.currentTimeMillis()+":"+entities);
        });
    }

    private static void test3(){
        Entity e1=new Entity(1,"test1");
        Entity e2=new Entity(2,"test1");
        Entity e3=new Entity(3,"test1");
        Entity e4=new Entity(4,"test1");
        Entity e5=new Entity(5,"test1");
       Observable<GroupedObservable<String,Entity>> groupedObservableObservable= Observable.just(e1, e2, e3, e4, e5).groupBy(new Func1<Entity, String>() {
            @Override
            public String call(Entity entity) {
                if(entity.id==1||entity.id==2)return "1";
                return "2";
            }
        });

        Observable.concat(groupedObservableObservable).subscribe(entity -> {
            System.out.println(entity.id + ":" + entity.name);
        });
    }
     static  class Entity{
        long id;
        String name;
        public Entity(long id,String name){
            this.id=id;
            this.name=name;
        }

         @Override
         public String toString() {
             return "Entity{" +
                     "id=" + id +
                     ", name='" + name + '\'' +
                     '}';
         }
     }
    private static void test2(){
        Observable.just(1,2,3,4,5).scan("string", new Func2<String, Integer, String>() {
            @Override
            public String call(String s, Integer integer) {
                return  s+integer;
            }
        }).subscribe(s->{System.out.println(s);});
//        Observable.just(1,2,3,4,5).scan((i,j)->i+j).subscribe(integer -> {System.out.println(integer);});
    }
    private static void test1() {
        List<String> list=new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        Observable.from(list).toSortedList().flatMapIterable(new Func1<List<String>, Iterable<?>>() {
            @Override
            public Iterable<?> call(List<String> strings) {
                return strings;
            }
        }).subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {
                System.out.println(o);
            }
        });
    }
}
