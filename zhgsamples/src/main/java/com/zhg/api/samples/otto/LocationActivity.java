package com.zhg.api.samples.otto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.squareup.otto.Produce;
import com.zhg.api.samples.R;

import java.util.Random;

public class LocationActivity extends AppCompatActivity {
    private static final float DEFAULT_LAT=100.2321f;
    private static final float DEFAULT_LON=-12.123123f;
    private static final float OFFERSET=0.1f;
    private float lastLon=DEFAULT_LON;
    private float lastLat =DEFAULT_LAT;
    private Random random=new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        findViewById(R.id.id_clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastLon = DEFAULT_LON;
                lastLat = DEFAULT_LAT;
                BusProvider.getInstance().post(produceLocationEvent());
                Log.e("info","onclick1");
            }
        });
        findViewById(R.id.id_move).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastLat+=random.nextFloat()*OFFERSET*2-OFFERSET;
                lastLon=random.nextFloat()*OFFERSET*2-OFFERSET;
                BusProvider.getInstance().post(produceLocationEvent());
                Log.e("info", "onclick2");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        BusProvider.getInstance().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
    }

    @Produce
    public LocationChangeEvent produceLocationEvent(){
        Log.e("info","==========produceLocationEvent=============");
        return new LocationChangeEvent(lastLat,lastLon);
    }
}
