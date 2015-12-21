package com.zhg.api.samples.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zhg.api.samples.R;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.CallAdapter;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class RetrofitActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.id_click)
    Button mClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        ButterKnife.bind(this);
        mClick.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.id_click:
                post();
                break;
        }
    }

    private void post() {
        Retrofit retrofit=new Retrofit.Builder().baseUrl("http://192.168.1.101:8080")
               .addConverterFactory(GsonConverterFactory.create()) .build();
        UserService userService=retrofit.create(UserService.class);
        Call<List<Result>> call= userService.listRepos("zhg");
        call.enqueue(new Callback<List<Result>>() {
            @Override
            public void onResponse(Response<List<Result>> response, Retrofit retrofit) {
                Log.e("info","response==="+response.body().toString());
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}
