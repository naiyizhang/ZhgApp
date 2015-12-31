package com.zhg.api.samples.design;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhg.api.samples.databinding.ItemAppbarBinding;

import java.util.List;

/**
 * Created by nyzhang on 2015/12/31.
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>{
    private Context mContext;
    private List<Entity> mDataList;

    public MyRecyclerViewAdapter(Context mContext, List<Entity> mDataList) {
        this.mContext = mContext;
        this.mDataList = mDataList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemAppbarBinding binding=ItemAppbarBinding.inflate(LayoutInflater.from(mContext), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mBinding.setEntity(mDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ItemAppbarBinding mBinding;
        public ViewHolder(ItemAppbarBinding binding) {
            super(binding.getRoot());
            mBinding=binding;
        }
    }
}
