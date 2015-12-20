package com.zhg.api.samples.databinding;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

/**
 * Created by nyzhang on 2015/12/17.
 */
public class Student {

    public static final ObservableField<String> name=new ObservableField<String>("hehe");
    public static final ObservableInt age=new ObservableInt();

}
