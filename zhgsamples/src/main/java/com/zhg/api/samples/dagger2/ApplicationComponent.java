package com.zhg.api.samples.dagger2;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by nyzhang on 2016/1/11.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {


}
