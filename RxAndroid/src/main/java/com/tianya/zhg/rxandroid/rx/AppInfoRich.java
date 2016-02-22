package com.tianya.zhg.rxandroid.rx;

import android.content.Context;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;

import com.tianya.zhg.rxandroid.R;

/**
 * Created by nyzhang on 2016/1/15.
 */
public class AppInfoRich {
    ResolveInfo mResolveInfo;
    Context mContext;

    private String label;
    public void setLabel(String label){
        this.label=label;
    }
    private Drawable drawable;

    public void setIcon(Drawable d){
        this.drawable=d;
    }
    public String getName() {
        return label;
    }

    public Drawable getIcon() {
        return drawable;
    }

    public long getLastUpdateTime() {
        return System.currentTimeMillis();
    }

    public AppInfoRich(ResolveInfo mResolveInfo, Context mContext) {
        this.mResolveInfo = mResolveInfo;
        this.mContext = mContext;
    }

    public ResolveInfo getResolveInfo() {
        return mResolveInfo;
    }

    public void setResolveInfo(ResolveInfo mResolveInfo) {
        this.mResolveInfo = mResolveInfo;
    }

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }
}
