package com.zhg.api.samples.otto;

import android.app.Fragment;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.otto.Subscribe;

import java.io.IOException;
import java.net.URL;

/**
 * Created by nyzhang on 2015/12/17.
 */
public class LocationMapFragment extends Fragment {
    private ImageView imageView;
    private String url="http://f.hiphotos.baidu.com/image/pic/item/faf2b2119313b07e97f760d908d7912396dd8c9c.jpg";
    private DownloadTask downloadTask;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        imageView=new ImageView(getActivity());
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        return imageView;
    }

    @Override
    public void onResume() {
        super.onResume();
        BusProvider.getInstance().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);

        if(downloadTask!=null){
            downloadTask.cancel(true);
            downloadTask=null;
        }
    }

    @Subscribe
    public void onLocationChanged(LocationChangeEvent event){
        if(downloadTask!=null){
            downloadTask.cancel(true);
        }
        downloadTask=new DownloadTask();
        downloadTask.execute(String.format(url, event.lat, event.lon));
    }

    @Subscribe
    public void onImageAvailable(ImageAvailableEvent event){
        if(imageView!=null){
            imageView.setImageDrawable(event.bitmap);
        }
    }
    class ImageAvailableEvent{
        private Drawable bitmap;
        ImageAvailableEvent(Drawable d){
            this.bitmap=d;
        }
        public void setBitmap(Drawable bitmap) {
            this.bitmap = bitmap;
        }
    }
    class DownloadTask extends AsyncTask<String,Void,Drawable>{

        @Override
        protected void onPostExecute(Drawable drawable) {
            if(!isCancelled()&&drawable!=null){
                BusProvider.getInstance().post(new ImageAvailableEvent(drawable));
            }
        }

        @Override
        protected Drawable doInBackground(String... params) {
            try {
               return  BitmapDrawable.createFromStream(new URL(params[0]).openStream(),"image.jpg");
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

        }
    }
}
