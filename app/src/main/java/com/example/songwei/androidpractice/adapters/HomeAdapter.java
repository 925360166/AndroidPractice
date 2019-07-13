package com.example.songwei.androidpractice.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.songwei.androidpractice.R;

import java.util.List;

/**
 * 自定义RecyclerView中的HomeAdapter，
 * 2继承自RecyclerView.ViewHolder
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

    private Context mContext;
    private List<String> mList;

    private OnItemClickListener mOnItemClickListener;

    public HomeAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.mList = list;
    }

    //继承RecyclerView中静态抽象内部类ViewHolder
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_item);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_recycler, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.tv.setText(mList.get(position));
        if(mOnItemClickListener != null){
            holder.tv.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.tv, pos);
                }
            });

            holder.tv.setOnLongClickListener(new View.OnLongClickListener(){
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemLongClick(holder.tv, pos);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public void removeData(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
    }


    /**
     * 4. 自定义点击事件
     * 列表中条目的点击事件需要我们自己来定义，
     * 这是一个不尽如人意的地方，但是自定义点击事件的话也并不是很难，
     * 在adaper中定义接口并提供回调：
     */
    public interface OnItemClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }
}
