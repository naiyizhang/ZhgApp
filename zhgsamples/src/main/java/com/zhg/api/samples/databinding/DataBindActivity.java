package com.zhg.api.samples.databinding;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ContactItem;
import com.zhg.api.samples.R;

public class DataBindActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ContactItem binding=DataBindingUtil.setContentView(this, R.layout.activity_bind);
        User user=new User("Tom","Zhang");
        user.setTxt("this is a world");
        user.setIsAdult(false);
        binding.setUser(user);

        MyHandler handler=new MyHandler();
        handler.setUser(user);
        binding.setMyHandler(handler);
//    ContactItem bind=ContactItem.bind(ViewGroup);
//        DataBindingUtil.bind(ViewGroup);
//        ActivityBindBinding b1=ActivityBindBinding.inflate(getLayoutInflater());
//        ActivityBindBinding b=ActivityBindBinding.inflate(getLayoutInflater(),ViewGroup,false);
//        ActivityBindBinding b=DataBindingUtil.inflate(getLayoutInflater(),R.layout.activity_bind,ViewGroup,false);
    }
}
