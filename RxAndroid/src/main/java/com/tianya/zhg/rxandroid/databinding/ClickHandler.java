package com.tianya.zhg.rxandroid.databinding;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.f2prateek.rx.preferences.Preference;
import com.f2prateek.rx.preferences.RxSharedPreferences;
import com.jakewharton.rxbinding.widget.RxCompoundButton;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewAfterTextChangeEvent;
import com.tianya.zhg.rxandroid.bean.ObjData;

import java.util.Random;

import de.greenrobot.event.EventBus;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.android.schedulers.HandlerScheduler;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by nyzhang on 2015/12/20.
 */
public class ClickHandler {
    public void clickBtn(View v) {
        Observable.create(subscriber -> {
            Log.e("info", "thread run in " + Thread.currentThread().getName());
            subscriber.onNext("hello world ");
            subscriber.onCompleted();
        }).map(s -> {
            Log.e("info", "thread run in " + Thread.currentThread().getName());
            return s.toString();

        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    Log.e("info", "subscribe thread run in " + Thread.currentThread().getName());
                    EventBus.getDefault().post(s);
                });
    }

    public void clickMe(View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Handler handler = new Handler();
                Observable.create(subscriber -> {
                    Log.e("info", "==observable run in thread " + Thread.currentThread().getName());
                    subscriber.onNext("this is a java");
                    subscriber.onCompleted();
                }).subscribeOn(Schedulers.newThread())
                    .observeOn(HandlerScheduler.from(handler))
                        .subscribe(s -> {
                            System.out.println(s);
                            EventBus.getDefault().post(new ObjData(s.toString()));
                            Log.e("info", "==subscribe run in thread " + Thread.currentThread().getName());
                        });
                Looper.loop();
            }
        }, "ZhgThread--1").start();

    }
    private CheckBox checkBox;
    public void setCheckBox(CheckBox b){
        this.checkBox=b;
    }
    public void save(View v){
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(v.getContext());
        RxSharedPreferences rxSharedPreferences=RxSharedPreferences.create(sharedPreferences);
        Preference<String> username=rxSharedPreferences.getString("username");
        Preference<Boolean> checked=rxSharedPreferences.getBoolean("show",true);
        RxCompoundButton.checkedChanges(checkBox).subscribe(checked.asAction());
        username.set("username " + new Random().nextInt());

    }
    public void show(View view){
        SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(view.getContext());
        RxSharedPreferences rxSharedPreferences=RxSharedPreferences.create(sharedPreferences);
        Preference<String> username=rxSharedPreferences.getString("username");
        Preference<Boolean> checked=rxSharedPreferences.getBoolean("show",true);
        username.asObservable().subscribe(s -> EventBus.getDefault().post(s));
//        checked.asObservable().subscribe(b-> Toast.makeText(view.getContext(),"==="+b,Toast.LENGTH_SHORT).show());
        Toast.makeText(view.getContext(),"==="+checked.get(),Toast.LENGTH_SHORT).show();
    }
}
