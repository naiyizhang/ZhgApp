package com.zhg.android.annotations;

import android.app.Activity;
import android.widget.TextView;


import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.util.Date;

/**
 * Created by nyzhang on 2016/1/11.
 */
@EActivity(R.layout.activity_with_extra)
public class ActivityWithExtra extends Activity {
    public static final String MY_DATE_EXTRA="myDateExtra";
    public static final String MY_INT_EXTRA="myIntExtra";
    public static final String MY_STRING_EXTRA="myStringExtra";

    @ViewById(R.id.id_textView)
    TextView mTextView;

    @Extra(MY_STRING_EXTRA)
    String myMessage;

    @Extra(MY_DATE_EXTRA)
    Date myDate;
    @Extra("unboundExtra")
    String unboundData="unboundExtraDefaultValue";
    @Extra(MY_INT_EXTRA)
    String classCastException="myIntExtranDefaultValue";

    @AfterViews
    protected  void init(){
        mTextView.setText(myMessage+":"+myDate+":"+unboundData+":"+classCastException);
    }

}
