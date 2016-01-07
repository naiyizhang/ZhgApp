package com.zhg.api.samples.design;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by nyzhang on 2015/12/31.
 */
public class NewEntity implements Parcelable {

    private String name;
    private int age;

    protected NewEntity(Parcel in) {
        name = in.readString();
        age = in.readInt();
    }

    public static final Creator<NewEntity> CREATOR = new Creator<NewEntity>() {
        @Override
        public NewEntity createFromParcel(Parcel in) {
            return new NewEntity(in);
        }

        @Override
        public NewEntity[] newArray(int size) {
            return new NewEntity[size];
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
    }

}
