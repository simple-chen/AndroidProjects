package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<String> mList = new ArrayList<>();
    private Button mBtn_button;
    private MyAdapter mMyAdapter;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipe_layout;
    private TextView mTv_empty_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initListener();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mMyAdapter = new MyAdapter(mList);
        mRecyclerView.setAdapter(mMyAdapter);


    }

    private void initListener() {
        mBtn_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mList.clear();
                mMyAdapter.notifyDataSetChanged();
                setEmptyView();
            }
        });

    }


    private void setEmptyView() {
        mRecyclerView.setVisibility(mList.size() == 0 ? View.GONE : View.VISIBLE);
        mSwipe_layout.setVisibility(mList.size() == 0 ? View.GONE : View.VISIBLE);


    }

    private void initView() {
        mRecyclerView = findViewById(R.id.recycler_view);
        mBtn_button = findViewById(R.id.deleteAll);
//        mSwipe_layout = findViewById(R.id.swipe_layout);
//        mTv_empty_view = findViewById(R.id.tv_empty_view);
    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            mList.add("测试" + i);
        }

    }
}
