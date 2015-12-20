package com.zhg.api.samples.otto;


import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nyzhang on 2015/12/17.
 */
public class LocalHistoryFragment extends ListFragment {
    private List<String> locationEvents=new ArrayList<String>();
    private ArrayAdapter<String> adapter;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,locationEvents);
        setListAdapter(adapter);
    }
    @Subscribe
    public void onLocationChanged(LocationChangeEvent event){
        locationEvents.add(0, event.toString());
        if(adapter!=null){
            adapter.notifyDataSetChanged();
        }
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
    }

    @Subscribe
    public void onLocationClear(LocationClearEvent event){
        locationEvents.clear();
        if(adapter!=null){
            adapter.notifyDataSetChanged();
        }
    }
}
