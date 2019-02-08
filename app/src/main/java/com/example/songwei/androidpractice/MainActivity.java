package com.example.songwei.androidpractice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        findViewById(R.id.button1).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.button1:
                showRecyclerView();
                break;
            default:
                Toast.makeText(MainActivity.this, "无效跳转", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void showRecyclerView() {
        startActivity(new Intent(MainActivity.this, RecyclerViewActivity.class));
    }
}
