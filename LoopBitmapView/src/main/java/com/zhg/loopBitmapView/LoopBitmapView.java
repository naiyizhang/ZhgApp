package com.zhg.loopBitmapView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


/**
 * Created by nyzhang on 2016/7/13.
 *
 * LoopBitmapView，可以循环显示长图的view，采取把源bitmap裁剪成两个bitmap然后拼接方式循环展示；
 * 参考：http://blog.csdn.net/sahadev_/article/details/51694026，感谢原作者的辛苦付出
 */
public class LoopBitmapView extends View {
    private Bitmap mSourceBitmap;//源bitmap

    private int mBitmapWidth, mBitmapHeight;//源Bitmap的宽高

    private boolean mRunningFlag=false;//是否循环展示的标志

    private Paint mPaint;//绘制bitmap时的画笔

    private int offset=0;//偏移量

    private int mMoveUnit=1;

    private Bitmap mRawBitmap,mClipBitmap;//rawBitmap是源Bitmap显示在View上的部分，clipBitmap则是源Bitmap滑动到接近尾部，截取源Bitmap头部衔接起来的部分（有点拗口）
    public LoopBitmapView(Context context) {
        super(context);
        init();
    }

    public LoopBitmapView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }



    public LoopBitmapView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a=null;
        Drawable src=null;
        try {
            a=context.obtainStyledAttributes(attrs, R.styleable.LoopBitmapView);
            src=a.getDrawable(R.styleable.LoopBitmapView_src);
        } finally {
            if(a!=null)a.recycle();
        }
        if(src!=null){
            if(src instanceof BitmapDrawable){
                BitmapDrawable d= (BitmapDrawable) src;
                mSourceBitmap=d.getBitmap();
            }else{
                throw new IllegalArgumentException("src must be a Bitmap!");
            }
        }
        init();
    }

    //设置源Bitmap
    public void setBitmap(Bitmap bitmap){
        this.mSourceBitmap=bitmap;
        init();
    }
    //初始化数据
    private void init() {
        if(mSourceBitmap!=null){
            mBitmapWidth =mSourceBitmap.getWidth();
            mBitmapHeight =mSourceBitmap.getHeight();
            mRunningFlag=true;
            offset=0;
            mPaint=new Paint();
            setFocusableInTouchMode(true);
            requestFocus();
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMeasureMode=MeasureSpec.getMode(widthMeasureSpec);
        int heightMeasureMode=MeasureSpec.getMode(heightMeasureSpec);
        if(mBitmapWidth >0&& mBitmapHeight >0&&(widthMeasureMode!=MeasureSpec.EXACTLY||heightMeasureMode!=MeasureSpec.EXACTLY)){
            int width= mBitmapWidth,height= mBitmapHeight;//计算最终的view的宽高
            if(widthMeasureMode==MeasureSpec.AT_MOST){
                width=Math.min(width,MeasureSpec.getSize(widthMeasureSpec));
            }
            if(heightMeasureMode==MeasureSpec.AT_MOST){
                height=Math.min(height,MeasureSpec.getSize(heightMeasureSpec));
            }
            setMeasuredDimension(width,height);
        }else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    private Handler mHander=new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0){
                invalidate();
            }
        }
    };
    //重要方法，几乎所有的逻辑都在这里
    @Override
    protected void onDraw(Canvas canvas) {
        recyclerTempBitmap();

        //View的坐标
        int left=getLeft();
        int right=getRight();
        int top=getTop();
        int bottom=getBottom();

        Log.e("info","left="+left+",right="+right+",top="+top+",bottom="+bottom+",bitmapWidth="+mBitmapWidth+",mBitmapHeight="+mBitmapHeight);
        int tempWidth=right-left;
        //bitmap最右侧滑至左侧不见时，重置offset进行第二次循环展示
        if(offset>= mBitmapWidth){
            offset=0;
        }
        //重新计算tempWidth，如果bitmap不够显示，则计算rawBitmap的width
        tempWidth=tempWidth+offset> mBitmapWidth ? mBitmapWidth -offset:tempWidth;
        mRawBitmap=Bitmap.createBitmap(mSourceBitmap,offset,0,tempWidth,Math.min(mBitmapHeight,bottom-top));
        canvas.drawBitmap(mRawBitmap,getMatrix(),mPaint);
        if(tempWidth<right-left){
            Rect dst=new Rect(tempWidth,0,right,Math.min(mBitmapHeight,bottom-top));
            mClipBitmap=Bitmap.createBitmap(mSourceBitmap,0,0,right-left-tempWidth,Math.min(bottom-top, mBitmapHeight));
            canvas.drawBitmap(mClipBitmap,null,dst,mPaint);
        }
        offset+=mMoveUnit;
        if(mRunningFlag){
            mHander.sendEmptyMessageDelayed(0,1);
        }
    }


    public void onPause(){
        mRunningFlag=false;
    }
    public void onResume(){
        mRunningFlag=true;
        invalidate();
    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        onPause();
        recyclerTempBitmap();
        mSourceBitmap.recycle();
    }

    //回收临时产生的bitmap
    private void recyclerTempBitmap() {
        if(mRawBitmap!=null){
            mRawBitmap.recycle();
            mRawBitmap=null;
        }
        if(mClipBitmap!=null){
            mClipBitmap.recycle();
            mClipBitmap=null;
        }
    }
}
