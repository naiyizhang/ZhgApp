package com.zhg.api.samples;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.ArrayMap;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.zhg.api.samples.parcelable.City;
import com.zhg.api.samples.parcelable.Person;
import com.zhg.api.samples.util.ScreenUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.id_button)
    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... params) {
                        Map<String,String> map=new HashMap<String, String>();
                        map.put("123","123");
                        map.put("1232","123");
                        map.put("12322","123");
                        map.put("12332","123");
                        map.put("1213","123");
                        Log.e("info",map.get("123"));
                        return null;
                    }
                }.execute();
            }
        });
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TargetActivity.class);
                Person p = new Person("Tom", 18, "NewYork");
                intent.putExtra("person", p);

                City city=City.builder().name("jack").number(19).extra("test").build();
                intent.putExtra("city",city);
                startActivity(intent);
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        TypedArray a=MainActivity.this.obtainStyledAttributes(new int[]{android.R.attr.colorPrimaryDark});
//                        int color=a.getColor(0, 0x00000000);
//                        Log.e("info","color="+color);
//                        a.recycle();
//                    }
//                });
            }
        });
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);//透明导航栏
        }
        Map<String,Integer> screenSize= ScreenUtils.getScreenSize(this);
        Log.e("info", "screenSize=" + screenSize);

        ScreenUtils.getScreenHeight(this);
        int h1=ScreenUtils.getStatusBarHeight(this);
        int h2=ScreenUtils.getStatusBarHeightByInvoke(this);
        Log.e("info","h1="+h1+",h2="+h2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        Log.e("info","onCreateView====3"+name);
        return super.onCreateView(name, context, attrs);
    }
    private void test(){}

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        Log.e("info","onCreateView====4"+name);
        return super.onCreateView(parent, name, context, attrs);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
