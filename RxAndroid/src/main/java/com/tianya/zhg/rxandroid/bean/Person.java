package com.tianya.zhg.rxandroid.bean;

/**
 * Created by nyzhang on 2016/1/15.
 */
public class Person {
    private String mName;
    private int mAge;

    public Person(String mName, int mAge) {
        this.mName = mName;
        this.mAge = mAge;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public int getAge() {
        return mAge;
    }

    public void setAge(int mAge) {
        this.mAge = mAge;
    }
}
