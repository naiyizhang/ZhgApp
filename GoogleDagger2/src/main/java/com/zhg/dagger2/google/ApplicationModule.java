package com.zhg.dagger2.google;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by nyzhang on 2016/1/12.
 */
@Module
public class ApplicationModule {
    private final AndroidApplication androidApplication;
    public ApplicationModule(AndroidApplication androidApplication){
        this.androidApplication=androidApplication;
    }

    @Provides
    @Singleton
    Context provideApplicationContext(){
        return androidApplication;
    }
}
