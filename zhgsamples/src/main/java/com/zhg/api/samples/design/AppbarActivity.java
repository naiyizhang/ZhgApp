package com.zhg.api.samples.design;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.zhg.api.samples.R;
import com.zhg.api.samples.databinding.MyRecycleviewAdapter;
import com.zhg.api.samples.databinding.User;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AppbarActivity extends AppCompatActivity {

    @Bind(R.id.id_toolBar)
    Toolbar mToolbar;
    @Bind(R.id.id_recyclerView)
    RecyclerView mRecyclerView;
    @Bind(R.id.id_floatingActionBar)
    FloatingActionButton mFloatingActionBar;
    private MyRecyclerViewAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appbar);
        ButterKnife.bind(this);
        mFloatingActionBar.setOnClickListener(v -> {
            Snackbar.make(v, "this is a snackbar toast", Snackbar.LENGTH_SHORT)
                    .setAction("click me", view -> Log.e("info", "click me do what?!!!"))
                    .show();
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        mRecyclerView.addItemDecoration();
        mRecyclerView.setAdapter(mAdapter=new MyRecyclerViewAdapter(this,new ArrayList<Entity>(){
            {
                add(Entity.builder().name("tom").age(10).mobile("123").build());
                add(Entity.builder().name("hack").age(20).mobile("123").build());
                add(Entity.builder().name("hack").age(30).mobile("123").build());
                add(Entity.builder().name("hack").age(40).mobile("123").build());
                add(Entity.builder().name("hack").age(50).mobile("123").build());
                add(Entity.builder().name("hack").age(60).mobile("123").build());
                add(Entity.builder().name("hack").age(70).mobile("123").build());
                add(Entity.builder().name("hack").age(80).mobile("123").build());
                add(Entity.builder().name("hack").age(90).mobile("123").build());
            }
        }));
    }
}
