package com.example.daliypractice.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.example.daliypractice.R;
import com.example.daliypractice.adapter.MyViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private SwipeRefreshLayout mSwipe_layout;
    private String[] mTitles = {"今天", "明天","张三","李四","王五"};
    private TabLayoutMediator mTabLayoutMediator;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mSwipe_layout.setEnabled(false);
        initListener();





//        TabFragmentAdapter tabFragmentAdapter = new TabFragmentAdapter(getSupportFragmentManager(), getLifecycle(), this);
//        mViewPager.setAdapter(tabFragmentAdapter);
//
//        mTabLayoutMediator = new TabLayoutMediator(mTabLayout, mViewPager, new TabLayoutMediator.TabConfigurationStrategy() {
//            @Override
//            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
//                    tab.setText(mTitles[position]);
//            }
//        });
//        mTabLayoutMediator.attach();
//
//        mViewPager.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                return false;
//            }
//        }
//
//
//        );
    }

    private void initView() {
        mTabLayout = findViewById(R.id.tab_layout);
        mViewPager = findViewById(R.id.view_pager2);
        mSwipe_layout = findViewById(R.id.swipe_layout);
        mViewPager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager(),this,mTitles,mSwipe_layout));
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initListener() {
        mSwipe_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipe_layout.setRefreshing(false);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

