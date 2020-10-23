package com.example.customrefreshlayout.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.customrefreshlayout.R;
import com.example.customrefreshlayout.adapter.FruitAdapter;
import com.example.customrefreshlayout.view.MyCustomRefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<String> mFruitList = new ArrayList<>();
    private FruitAdapter mFruitAdapter;
    private MyCustomRefreshLayout mCustomRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initFruitData();
        mFruitAdapter.addFruitData(mFruitList);
        initListener();
    }

    private void initListener() {
        mCustomRefreshLayout.setOnRefreshListener(new MyCustomRefreshLayout.OnRefreshListener() {
            @Override
            public void downRefresh() {
                mFruitList.add(0,"下拉刷新的pig");
                mFruitAdapter.addFruitData(mFruitList);
            }

            @Override
            public void upRefresh() {
                mFruitList.add("上拉加载的banana");
                mFruitAdapter.addFruitData(mFruitList);
                mRecyclerView.scrollToPosition(mFruitList.size()-1);
                mFruitAdapter.notifyDataSetChanged();

            }
        });
    }

    private void initFruitData() {
        for (int i = 0; i < 50; i++) {
            mFruitList.add("apple");
        }
    }

    private void initView() {
        mCustomRefreshLayout = findViewById(R.id.refresh_layout);
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mFruitAdapter = new FruitAdapter();
        mRecyclerView.setAdapter(mFruitAdapter);
    }
}
