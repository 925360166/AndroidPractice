package com.example.songwei.androidpractice.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.songwei.androidpractice.R;
import com.example.songwei.androidpractice.adapters.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    private List<String> list = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        for(int i = 0; i< 100; i++){
            list.add(String.valueOf(i));
        }

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        adapter = new RecyclerViewAdapter(list);

        //设置布局管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //设置item增加和删除时的动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    public static void showRecyclerView(Context context) {
        context.startActivity(new Intent(context, RecyclerViewActivity.class));
    }
}
