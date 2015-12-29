package com.zhg.api.samples.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.util.ArrayMap;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nyzhang on 2015/12/29.
 */
public class ScreenUtils {
    public static Map<String,Integer> getScreenSize(Context context){
        WindowManager wm= (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm=new DisplayMetrics();
        Display d=wm.getDefaultDisplay();
        d.getMetrics(dm);
        int widthPixels=dm.widthPixels;
        int heightPixels=dm.heightPixels;
        Log.e("info","widthPx="+widthPixels+",heightPx="+heightPixels);
        if(Build.VERSION.SDK_INT>=17){
            Point realSize=new Point();
            try {
                Display.class.getMethod("getRealSize",Point.class).invoke(d,realSize);
                widthPixels=realSize.x;
                heightPixels=realSize.y;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }else if(Build.VERSION.SDK_INT>=14){
            try {
                widthPixels= (int) Display.class.getMethod("getRawWidth").invoke(d);
                heightPixels= (int) Display.class.getMethod("getRawHeight").invoke(d);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        Map<String,Integer> ret= new HashMap<String,Integer>();
        ret.put("width",widthPixels);
        ret.put("height",heightPixels);
        return ret;
    }


    public static void getScreenHeight(Activity context){
        Rect outRect=new Rect();
        context.getWindow().getDecorView().getWindowVisibleDisplayFrame(outRect);//应用区域
        Log.e("info", "WindowVisibleDisplayFrame: top=" + outRect.top + ",bottom=" + outRect.bottom + ",left=" + outRect.left + ",right=" + outRect.right);
        Rect drawRect=new Rect();
        context.getWindow().findViewById(Window.ID_ANDROID_CONTENT).getDrawingRect(drawRect);//绘制区域
        Log.e("info", "ID_ANDROID_CONTENT: top=" + drawRect.top + ",bottom=" + drawRect.bottom + ",left=" + drawRect.left + ",right=" + drawRect.right);

    }

    public static int getStatusBarHeight(Context context){
        int result=0;
        int resouceId=context.getResources().getIdentifier("status_bar_height","dimen","android");
        if(resouceId>0){
            result=context.getResources().getDimensionPixelSize(resouceId);
        }
        return result;
    }

    public static int getStatusBarHeightByInvoke(Context context){
        int statusBarHeight=-1;
        try {
            Class clazz=Class.forName("com.android.internal.R$dimen");
            Object obj=clazz.newInstance();
            int id=Integer.parseInt(clazz.getField("status_bar_height").get(obj).toString());
            statusBarHeight=context.getResources().getDimensionPixelSize(id);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return statusBarHeight;
    }
}
