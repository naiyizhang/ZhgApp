package com.zhg.api.samples.bitmapregiondecoder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.zhg.api.samples.R;

import java.io.IOException;
import java.io.InputStream;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BitmapRegionDecoderActivity extends AppCompatActivity {

    @Bind(R.id.id_imageView)
    ImageView mImageView;
    @Bind(R.id.id_imageView2)
    ImageView mImageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap_region_decode);
        ButterKnife.bind(this);

        try {
            InputStream in=getAssets().open("tangyan.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
