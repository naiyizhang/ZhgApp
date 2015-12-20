package com.tianya.zhg.rxandroid;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.tianya.zhg.rxandroid.bean.ObjData;
import com.tianya.zhg.rxandroid.databinding.ActivityMainBinding;
import com.tianya.zhg.rxandroid.databinding.ClickHandler;
import com.tianya.zhg.rxandroid.presenter.MainPresenter;
import com.trello.rxlifecycle.ActivityEvent;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import rx.Observable;

public class MainActivity extends RxAppCompatActivity implements MainView {

    //    ActivityLifecycleProvider provider= NaviLifecycle.createActivityLifecycleProvider(this);
    @Bind(R.id.id_tips)
    TextView mTips;
    @Bind(R.id.id_check)
    CheckBox mCheck;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    private MainPresenter mainPresenter = new MainPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        ButterKnife.bind(this);
        ClickHandler handler = new ClickHandler();
        handler.setCheckBox(mCheck);
        binding.setMyHandler(handler);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        EventBus.getDefault().register(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        init();

        Log.d("info", "onCreate()");
        Observable.interval(1, TimeUnit.SECONDS)
                .doOnUnsubscribe(() -> Log.e("info", "Unsubcribing subcriptoin from onCreate()"))
                .compose(this.<Long>bindUntilEvent(ActivityEvent.PAUSE))
                .subscribe(aLong -> Log.e("info", "Start in onCreate,running until onPause() " + aLong));

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("info", "onStart()");
        Observable.interval(1, TimeUnit.SECONDS)
                .doOnUnsubscribe(() -> Log.e("info", "Unsubscribing subscription from onStart()"))
                .compose(this.<Long>bindToLifecycle())
                .subscribe(l -> Log.e("info", "Start in onStart(),running until onStop()" + l));
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("info", "onResume()");
        Observable.interval(1, TimeUnit.SECONDS).doOnUnsubscribe(() -> Log.e("info", "Unsubscribing subscription from onResume()"))
                .compose(this.<Long>bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(l -> Log.e("info", "Start in onResume(),running until onDestroy()" + l));
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("info", "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("info", "onStop()");
    }


    private void init() {
        mTips.setError("this is error");
//        RxTextView.textChanges(mTips).map(s->s+"--zhang").subscribe(RxTextView.text(mTips));
        RxView.clicks(mTips).subscribe(s -> Toast.makeText(this, "click me!!!!!", Toast.LENGTH_SHORT).show());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Subscribe
    public void clickMe(View view) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        Log.d("info", "onDestroy()");
    }

    @Subscribe
    public void onEventMainThread(String str) {
        mTips.setText(str);
    }

    @Subscribe
    public void test(final ObjData obj) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTips.setText(obj.getTxt());
            }
        });
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

    @Override
    public void updateTips(String txt) {
        mTips.setText(txt);
    }
}
