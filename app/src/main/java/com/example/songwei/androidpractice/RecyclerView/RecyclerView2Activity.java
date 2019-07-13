package com.example.songwei.androidpractice.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.songwei.androidpractice.R;
import com.example.songwei.androidpractice.adapters.HomeAdapter;

import java.util.ArrayList;
import java.util.List;

public class RecyclerView2Activity extends AppCompatActivity {

    private List<String> mList;
    private RecyclerView mRecyclerView;
    private HomeAdapter mHomeAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview2);
        initData();
        initView();
    }

    private void initData() {
        mList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mList.add(String.valueOf(i));
        }
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
        //设置ListView
        setListView();
        //设置GridView
//        setGridView();
        //设置瀑布流
//        setWaterfallView();
    }

    private void setListView() {
        //设置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //设置item增加和删除时的动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //设置分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(RecyclerView2Activity.this, DividerItemDecoration.VERTICAL_LIST));
//        mRecyclerView.addItemDecoration(new android.support.v7.widget.DividerItemDecoration(this, LinearLayout.VERTICAL));
        //创建Adapter
        mHomeAdapter = new HomeAdapter(this, mList);
        //item设置监听
        mHomeAdapter.setOnItemClickListener(new HomeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(RecyclerView2Activity.this, "点击第" + (position + 1) + "条", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                new AlertDialog.Builder(RecyclerView2Activity.this)
                        .setTitle("确认删除嘛")
                        .setNegativeButton("取消", null)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mHomeAdapter.removeData(position);
                            }
                        })
                        .show();
            }
        });
        mRecyclerView.setAdapter(mHomeAdapter);
    }

    private void setGridView() {
        //设置布局管理器
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));

        //设置item增加和删除时的动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //设置分割线
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(RecyclerView2Activity.this, DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.addItemDecoration(new android.support.v7.widget.DividerItemDecoration(this, LinearLayout.VERTICAL));
        //创建Adapter
        mHomeAdapter = new HomeAdapter(this, mList);
        //item设置监听
        mHomeAdapter.setOnItemClickListener(new HomeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(RecyclerView2Activity.this, "点击第" + (position + 1) + "条", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                new AlertDialog.Builder(RecyclerView2Activity.this)
                        .setTitle("确认删除嘛")
                        .setNegativeButton("取消", null)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mHomeAdapter.removeData(position);
                            }
                        })
                        .show();
            }
        });
        mRecyclerView.setAdapter(mHomeAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
