package com.zhg.api.samples.databinding;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by nyzhang on 2015/12/30.
 */
public class MyRecycleviewAdapter extends RecyclerView.Adapter<MyRecycleviewAdapter.MyHolder> {
    private Context mContext;
    private List<User> mDatalist;
    public MyRecycleviewAdapter(Context context,List<User> list){
        mContext=context;
        mDatalist=list;
    }
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ListItemBinding listItemBinding=ListItemBinding.inflate(LayoutInflater.from(mContext),parent,false);
        return new MyHolder(listItemBinding);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.binding.setUser(mDatalist.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatalist.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        ListItemBinding binding;
        public MyHolder(ListItemBinding itemView) {
            super(itemView.getRoot());
            View view=itemView.getRoot();
            Log.e("info", "root=" + view.getClass().getName());
            binding=itemView;
        }
    }
}
