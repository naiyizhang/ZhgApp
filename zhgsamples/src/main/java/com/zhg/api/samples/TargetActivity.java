package com.zhg.api.samples;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.zhg.api.samples.parcelable.City;
import com.zhg.api.samples.parcelable.Person;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TargetActivity extends AppCompatActivity {

    @Bind(R.id.id_tips)
    TextView mTips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target);
        ButterKnife.bind(this);
        Person p=getIntent().getParcelableExtra("person");
        City c=getIntent().getParcelableExtra("city");
        mTips.setText(p.getName()+","+p.getAge()+","+p.getAddress()+","+c.name()+","+c.number()+","+c.extra());
    }
}
