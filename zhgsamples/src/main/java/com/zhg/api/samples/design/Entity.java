package com.zhg.api.samples.design;

import android.os.Parcelable;

import auto.parcel.AutoParcel;

/**
 * Created by nyzhang on 2015/12/31.
 */
@AutoParcel
public abstract class Entity implements Parcelable {
    public abstract String name();
    public abstract int age();
    public abstract String mobile();

    @AutoParcel.Builder
    public abstract static class Builder{
        public abstract Builder name(String name);
        public abstract Builder age(int age);
        public abstract Builder mobile(String mobile);
        public abstract Entity build();
    }

    public static Builder builder(){
        return new AutoParcel_Entity.Builder();
    }

    public static Entity create(String name,int age,String mobile){
        return builder().name(name).age(age).mobile(mobile).build();
    }

}
