package com.example.java8new.cons;

import java.util.function.Predicate;

/**
 * Created by nyzhang on 2016/1/15.
 */
public class Test {
    public static void main(String[]args){
        PersonFactory<Person> factory=Person::new;
        Person p=factory.create("tom","click");
        System.out.println(p);

    }
}
