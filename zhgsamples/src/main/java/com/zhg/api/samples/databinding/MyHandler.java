package com.zhg.api.samples.databinding;

import android.util.Log;
import android.view.View;

import com.zhg.api.samples.BR;

/**
 * Created by nyzhang on 2015/12/17.
 */
public class MyHandler {
    private User user;
    public void setUser(User user){
        this.user=user;
    }
    public void onClickFriend(View view){
        Log.e("info","=========onClickFriend=======");
    }
    public void onClickEnemy(View view){
        Log.e("info","=======onClickEnemy=========");
    }
    public void onClickMe(View view){
        Log.e("info","onclickme============");
        user.setFirstName("machael");
        user.setLastName("jackson");
    }
}
