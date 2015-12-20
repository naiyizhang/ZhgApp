package com.zhg.api.samples.parcelable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by nyzhang on 2015/12/16.
 */
public class User implements Parcelable {
    private String username;
    private String password;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.username);
        dest.writeString(this.password);
    }

    public User() {
    }

    protected User(Parcel in) {
        this.username = in.readString();
        this.password = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
