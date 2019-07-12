package com.example.songwei.androidpractice.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

    private List<String> mList;
    private Context mContext;
    public HomeAdapter(Context context, List<String> list){
        this.mContext = context;
        this.mList = list;
    }

    public void removeData(int position){
        mList.remove(position);
        notifyItemRemoved(position);
    }

}
