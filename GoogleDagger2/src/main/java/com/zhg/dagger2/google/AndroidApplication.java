package com.zhg.dagger2.google;

import android.app.Application;

/**
 * Created by nyzhang on 2016/1/12.
 */
public class AndroidApplication extends Application{
    ApplicationComponent component;
    @Override
    public void onCreate() {
        super.onCreate();

    }
}
