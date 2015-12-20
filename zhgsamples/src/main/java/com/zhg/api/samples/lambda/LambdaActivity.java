package com.zhg.api.samples.lambda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.zhg.api.samples.R;

public class LambdaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lambda);
        findViewById(R.id.id_button).setOnClickListener(v-> Toast.makeText(this,"呵呵哒",Toast.LENGTH_SHORT).show());
    }
}
