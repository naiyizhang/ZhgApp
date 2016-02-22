package com.tianya.zhg.rxandroid.rx;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * Created by nyzhang on 2016/1/15.
 */
public class Utils {
    public static Bitmap drawableToBitmap(Drawable d){
        if(d instanceof BitmapDrawable){
            BitmapDrawable bitmapDrawable= (BitmapDrawable) d;
            return bitmapDrawable.getBitmap();
        }
        return null;
    }


    public static void storeBitmap(Context context,String file_name,Bitmap bitmap){
        Schedulers.io().createWorker().schedule(()->storeBitmapBlock(context,file_name,bitmap));
    }
    public static void storeBitmapBlock(Context context,String file_name,Bitmap bitmap){
        try {
//            File file=new File(AppMainFragment.ROOT_IMAGE_DIR+"/"+file_name);
//            if(!file.getParentFile().exists())file.getParentFile().mkdirs();
//            OutputStream out=new FileOutputStream(file);
            OutputStream out=context.openFileOutput(file_name,Context.MODE_PRIVATE);
            bitmap.compress(Bitmap.CompressFormat.PNG,100,out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
