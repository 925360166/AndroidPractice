package com.example.songwei.androidpractice.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.songwei.androidpractice.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.VH> {

    public static class VH extends RecyclerView.ViewHolder{

        public final TextView title;
        public VH(View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.tv_title);
        }
    }

    private List<String> mDatas;
    public RecyclerViewAdapter(List<String> data){
        this.mDatas = data;
    }

    //在Adapter中实现3个方法

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        //LayoutInflater.from指定写法
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(final VH holder, int position) {
        holder.title.setText(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}
