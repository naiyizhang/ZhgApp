package com.example.java8new.cons;

/**
 * Created by nyzhang on 2016/1/15.
 */
public class Person {
    private String firstName;
    private String lastName;
    public Person(){}

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
