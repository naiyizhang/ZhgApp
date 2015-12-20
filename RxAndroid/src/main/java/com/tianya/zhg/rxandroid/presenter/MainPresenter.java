package com.tianya.zhg.rxandroid.presenter;

import com.tianya.zhg.rxandroid.MainView;

/**
 * Created by nyzhang on 2015/12/20.
 */
public class MainPresenter {
    private MainView mainView;
    public MainPresenter(MainView mainView){
        this.mainView=mainView;
    }
    public void clickBtn(){
        mainView.updateTips("");
    }
}
