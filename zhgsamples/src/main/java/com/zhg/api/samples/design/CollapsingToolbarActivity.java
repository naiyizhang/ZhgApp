package com.zhg.api.samples.design;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.zhg.api.samples.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CollapsingToolbarActivity extends AppCompatActivity {

    @Bind(R.id.id_toolBar)
    Toolbar mToolBar;
    @Bind(R.id.id_collapsingToolbar)
    CollapsingToolbarLayout mCollapsingToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing_toolbar);
        ButterKnife.bind(this);
        setSupportActionBar(mToolBar);
        mCollapsingToolbar.setExpandedTitleColor(Color.BLACK);
        mCollapsingToolbar.setCollapsedTitleTextColor(Color.GREEN);
    }
}
