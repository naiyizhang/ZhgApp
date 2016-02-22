package com.tianya.zhg.rxandroid.rx;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tianya.zhg.rxandroid.databinding.RxFragmentItemBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nyzhang on 2016/1/15.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context mContext;
    private List<AppInfo> mDataList;

    public MyAdapter(Context mContext, List<AppInfo> mDataList) {
        this.mContext = mContext;
        this.mDataList = mDataList;
    }

    public void addApp(List<AppInfo> appInfos){
        mDataList.addAll(appInfos);
        notifyItemInserted(mDataList.size());
    }public void addApp(AppInfo appInfo){
        mDataList.add(appInfo);
        notifyItemInserted(mDataList.size());
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RxFragmentItemBinding binding=RxFragmentItemBinding.inflate(LayoutInflater.from(mContext),parent,false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.rxFragmentItemBinding.setData(mDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        RxFragmentItemBinding rxFragmentItemBinding;
        public MyViewHolder(RxFragmentItemBinding binding) {
            super(binding.getRoot());
            this.rxFragmentItemBinding=binding;
        }
    }
}
