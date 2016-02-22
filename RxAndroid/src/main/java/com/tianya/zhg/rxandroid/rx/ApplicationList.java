package com.tianya.zhg.rxandroid.rx;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nyzhang on 2016/1/18.
 */
public class ApplicationList {
    private static ApplicationList instance;
    public static ApplicationList getInstance(){
        if(instance==null){
            synchronized (ApplicationList.class){
                if(instance==null){
                    instance=new ApplicationList();
                }
            }
        }
        return instance;
    }
    public List<AppInfo> getList(){
        List<AppInfo> list=new ArrayList<>();
        for(int i=0;i<20;i++){
            AppInfo a=new AppInfo(System.currentTimeMillis(),"name"+i,"");
            list.add(a);
        }
        return list;
    }
}
