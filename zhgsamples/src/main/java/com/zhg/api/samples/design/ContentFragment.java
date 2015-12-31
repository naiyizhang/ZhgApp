package com.zhg.api.samples.design;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by nyzhang on 2015/12/30.
 */
public class ContentFragment extends Fragment{
    private static final String KEY_TITLE="key_title";
    private String mTitle;
    public static ContentFragment getInstance(String title){
        ContentFragment fragment=new ContentFragment();
        Bundle bundle=new Bundle();
        bundle.putString(KEY_TITLE,title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_TITLE,mTitle);
    }


    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if(savedInstanceState!=null)
            mTitle=savedInstanceState.getString(KEY_TITLE);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if(getArguments()!=null){
            mTitle=getArguments().getString(KEY_TITLE);
        }
//        if(savedInstanceState!=null)
//            mTitle=savedInstanceState.getString(KEY_TITLE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView textView=new TextView(getActivity());
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
        if(!TextUtils.isEmpty(mTitle)){
            textView.setText(mTitle);
        }
        return textView;
    }
}
