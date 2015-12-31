package com.zhg.api.samples.design;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.zhg.api.samples.R;

/**
 * Created by nyzhang on 2015/12/30.
 */
public class LeftMenuFragment extends ListFragment {
    private MenuItem[] menuItems;
    private ArrayAdapter<MenuItem> mAdapter;
    private static final String KEY_POS="key_position";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        String[] arrays=getResources().getStringArray(R.array.menu_array);
        menuItems=new MenuItem[arrays.length];
        for(int i=0;i<arrays.length;i++){
            MenuItem item=new MenuItem(false,arrays[i],R.mipmap.music_normal,R.mipmap.music_selected);
            menuItems[i]=item;
        }

        currentMenuItem=menuItems[0];
        currentMenuItem.isSelected=true;

        Log.e("info","LeftMenuFragment.onCreate()");

//        if(savedInstanceState!=null)
//            mCurrentPosition=savedInstanceState.getInt(KEY_POS,0);
    }

    public void updateSelectedPostionByPostion(String title){
        int index= getTitleIndex(title);
        if(index!=-1){
            mCurrentPosition=index;
        }

    }

    private int getTitleIndex(String title) {
        String[]arrays=getResources().getStringArray(R.array.menu_array);
        for(int i=0;i<arrays.length;i++){
            String str=arrays[i];
            if(str.equals(title)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_POS,mCurrentPosition);
    }



    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if(savedInstanceState!=null)
            mCurrentPosition=savedInstanceState.getInt(KEY_POS,0);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setListAdapter(mAdapter=new ArrayAdapter<MenuItem>(getActivity(),0,menuItems){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if(convertView==null){
                    TextView textView=new TextView(getActivity());
                    int padding= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getActivity().getResources().getDisplayMetrics());
                    textView.setPadding(padding,padding,padding,padding);
                    textView.setTextColor(Color.parseColor("#00FF00"));
                    convertView=textView;
                }
                TextView textView= (TextView) convertView;
                MenuItem data=getItem(position);
                textView.setText(data.text);
                if(mCurrentPosition==position){
                    textView.setCompoundDrawablesWithIntrinsicBounds(data.iconSelected,0,0,0);
                    textView.setBackgroundColor(Color.BLUE);
                    currentTextView=textView;
                    currentMenuItem=data;
                }else if(data.isSelected){
                    textView.setCompoundDrawablesWithIntrinsicBounds(data.iconSelected,0,0,0);
                    textView.setBackgroundColor(Color.BLUE);
                    if(currentTextView==null) {//第一次初始化，初始化view和position的值
                        currentTextView = textView;
                        mCurrentPosition=position;
                    }
                }else{
                    textView.setCompoundDrawablesWithIntrinsicBounds(data.icon,0,0,0);
                    textView.setBackgroundColor(Color.TRANSPARENT);
                }
                return convertView;
            }
        });
    }

    private MenuItem currentMenuItem;
    private TextView currentTextView;
    private int mCurrentPosition=0;
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        MenuItem item = setMenuItemSelected( v, position);

        if(mListener!=null){
            mListener.onMenuItemSelect(item.text);
        }
    }

    @NonNull
    public MenuItem setMenuItemSelected(View v, int position) {

        MenuItem item= menuItems[position];
        item.isSelected=true;
        if(currentMenuItem!=null){
            currentMenuItem.isSelected=false;
        }
        if(currentTextView!=null){
            currentTextView.setCompoundDrawablesWithIntrinsicBounds(currentMenuItem.icon, 0, 0, 0);
            currentTextView.setBackgroundColor(Color.TRANSPARENT);
        }
        TextView textView= (TextView) v;
        textView.setCompoundDrawablesWithIntrinsicBounds(item.iconSelected,0,0,0);
        textView.setBackgroundColor(Color.BLUE);
        currentMenuItem=item;
        currentTextView=textView;
        mCurrentPosition=position;
        return item;
    }


    public interface  OnMenuItemSelectedListener{
        public void onMenuItemSelect(String title);
    }
    public OnMenuItemSelectedListener mListener;
    public void setOnMenuItemSelectedListener(OnMenuItemSelectedListener l){
        mListener=l;
    }
}
