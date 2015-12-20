package com.zhg.api.samples.parcelable;

import android.os.Parcelable;

import auto.parcel.AutoParcel;

/**
 * Created by nyzhang on 2015/12/16.
 */
@AutoParcel
public abstract class City implements Parcelable {

    public static City create(String name,int number,String extra){
        return builder().name(name).number(number).extra(extra).build();
    }
    public abstract String name();
    public abstract int number();
    public abstract String extra();
    @AutoParcel.Builder
    public abstract static class Builder{
        public abstract Builder name(String s);
        public abstract Builder number(int n);
        public abstract Builder extra(String s);
        public abstract City build();
    }
    public static Builder builder(){
        return new AutoParcel_City.Builder();
    }

//    public abstract Builder toBuilder();

}
