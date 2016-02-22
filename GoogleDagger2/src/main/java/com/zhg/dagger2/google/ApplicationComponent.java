package com.zhg.dagger2.google;

import android.app.Activity;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by nyzhang on 2016/1/12.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(AndroidApplication application);

    Context context();
}
