package com.tianya.zhg.rxandroid.rx;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/**
 * Created by nyzhang on 2016/1/15.
 */
public class AppInfo implements Comparable<Object>{
    long mLastUpdateTime;
    String mName;
    String mIcon;

    public AppInfo(long mLastUpdateTime, String mName, String mIcon) {
        this.mLastUpdateTime = mLastUpdateTime;
        this.mName = mName;
        this.mIcon = mIcon;
    }

    @Override
    public int compareTo(Object another) {
        AppInfo f= (AppInfo) another;
        return getName().compareTo(f.getName());
    }
    public Drawable getBitmap(){
        return new BitmapDrawable(mIcon);
    }

    public long getLastUpdateTime() {
        return mLastUpdateTime;
    }

    public void setLastUpdateTime(long mLastUpdateTime) {
        this.mLastUpdateTime = mLastUpdateTime;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String mIcon) {
        this.mIcon = mIcon;
    }
}
