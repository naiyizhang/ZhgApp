package com.zhg.api.samples.parcelable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by nyzhang on 2015/12/16.
 */
public class Person implements Parcelable {
    private String name;
    private int age;
    private String address;
    protected Person(Parcel in) {
        name=in.readString();
        age=in.readInt();
        address=in.readString();
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
        dest.writeString(address);
    }
}
