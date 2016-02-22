package com.zhg.android.annotations;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;


import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ItemLongClick;
import org.androidannotations.annotations.ItemSelect;
import org.androidannotations.annotations.res.StringArrayRes;

/**
 * Created by nyzhang on 2016/1/11.
 */
@EActivity
public class MyListActivity extends ListActivity {

    @StringArrayRes(R.array.bestfoods)
    String[] bestFoods;
    private ListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,bestFoods);
        setListAdapter(adapter);
    }
    @ItemClick
    void listItemClicked(String food){
        Snackbar.make(getListView(), "click food :" + food, Snackbar.LENGTH_SHORT).show();
    }

    @ItemLongClick
    void listItemLongClicked(String food){
        Snackbar.make(getListView(),"long click food:"+food,Snackbar.LENGTH_SHORT).show();
    }

    @ItemSelect
    void listItemSelected(boolean somethingSelected,String food){
        if(somethingSelected){
            Snackbar.make(getListView(),"selected:"+food,Snackbar.LENGTH_SHORT).show();
        }else{
            Snackbar.make(getListView(),"nothing selected "+food,Snackbar.LENGTH_SHORT).show();
        }
    }

}
