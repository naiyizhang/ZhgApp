package com.example.java8new;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by nyzhang on 2016/1/15.
 */
public class Test2 {
    public static void main(String[]args){
        Predicate<String> predicate=(s)->s.length()>0;
        System.out.println(predicate.test("foo"));
        System.out.println(predicate.negate().test("foo"));

        Predicate<Boolean> nonull= Objects::nonNull;
        Predicate<Boolean> isNull=Objects::isNull;
        Predicate<String> isEmptry=String::isEmpty;
        Predicate<String> isNotEmpty=isEmptry.negate();

        Function<String,Integer> toInteger=Integer::valueOf;
        Function<String,String> backToString=toInteger.andThen(String::valueOf);
        System.out.println(backToString.apply("123"));
        Map<String,Object> map=new HashMap<>();
        map.computeIfAbsent("3", s -> null);
//        map.merge()
    }
}
