package com.zhg.api.samples.androidannotations;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.zhg.api.samples.R;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.LongClick;
import org.androidannotations.annotations.NoTitle;
import org.androidannotations.annotations.SystemService;
import org.androidannotations.annotations.Touch;
import org.androidannotations.annotations.Transactional;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.BooleanRes;
import org.androidannotations.annotations.res.ColorRes;
import org.androidannotations.annotations.res.StringRes;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by nyzhang on 2016/1/11.
 */
@EActivity(R.layout.my_activity)
public class MyActivity extends AppCompatActivity {

    @ViewById(R.id.myEditText)
    EditText myEditText;

    @ViewById(R.id.myTextView)
    TextView myTextView;

    @StringRes(R.string.hello)
    String helloFormat;

    @ColorRes(R.color.androidColor)
    int androidColor;
    @BooleanRes(R.bool.somebool)
    boolean someBoolean;

    @SystemService
    NotificationManager notificationManager;
    @SystemService
    WindowManager windowManager;

    @Override
    public void onBackPressed() {
        Snackbar.make(myTextView,"backKeyPressed!",Snackbar.LENGTH_SHORT).setAction("click", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyActivity.super.onBackPressed();
            }
        }).show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        windowManager.getDefaultDisplay();
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
    }

    @Click(value = {R.id.myButton})
    void myButtonClick(){
        String name=myEditText.getText().toString();
        setProgressBarIndeterminateVisibility(true);
        someBackgroundWork(name, 5);

    }

    @Background
    void someBackgroundWork(String name, int timeToDoSomeLongComputation) {

        try {
            TimeUnit.SECONDS.sleep(timeToDoSomeLongComputation);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String message=String.format(helloFormat, name);
        updateUI(message, androidColor);
        showNotificationDelayed();
    }

    @UiThread(delay = 5000)
    void showNotificationDelayed() {
        Notification notification=new Notification(R.mipmap.ic_launcher,"Hello",0);
        PendingIntent contentIntent=PendingIntent.getActivity(this, 0, new Intent(), 0);
        notification.contentIntent=contentIntent;
        notificationManager.notify(1, notification);
    }
    @LongClick(R.id.startExtraActivity)
    void startExtraActivity(View v){
    }
    @Click(R.id.startListActivity)
    void startListActivity(){
    }

    @Touch(R.id.myTextView)
    void myTextView(MotionEvent motionEvent){
        Log.e("info","myTextView is touched :"+motionEvent.getX());
    }

    @UiThread
    void updateUI(String message, int color) {
        setProgressBarIndeterminateVisibility(false);
        myTextView.setText(message);
        myTextView.setTextColor(color);
    }

    @Transactional
    int transactionalMethod(SQLiteDatabase db,int someParam){
       return 42;
    }
}
