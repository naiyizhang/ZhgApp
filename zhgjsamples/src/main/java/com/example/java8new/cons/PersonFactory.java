package com.example.java8new.cons;

/**
 * Created by nyzhang on 2016/1/15.
 */
public interface PersonFactory<P extends Person> {
    P create(String firstName,String lastName);
}
