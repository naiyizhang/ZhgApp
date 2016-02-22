package com.zhg.api.samples.review.design;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhg.api.samples.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainDesignActivity extends AppCompatActivity {

    @Bind(R.id.id_toolBar)
    Toolbar mToolBar;
    @Bind(R.id.id_recyclerView)
    RecyclerView mRecyclerView;
    @Bind(R.id.id_collapsingToolbarLayout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_design);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.BLACK);
        mCollapsingToolbarLayout.setExpandedTitleColor(Color.GREEN);
        mRecyclerView.setLayoutManager(new GridLayoutManager(MainDesignActivity.this, 3));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(new RecyclerView.Adapter<MyViewHolder>() {
            String[] arrays = new String[120];

            {
                for (int i = 0; i < 119; i++) {
                    arrays[i] = "test" + i;
                }
            }

            @Override
            public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new MyViewHolder(new TextView(parent.getContext()));
            }

            @Override
            public void onBindViewHolder(MyViewHolder holder, int position) {
                holder.textView.setText(arrays[position]);
            }

            @Override
            public int getItemCount() {
                return arrays.length;
            }
        });
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView;
        }
    }
}
