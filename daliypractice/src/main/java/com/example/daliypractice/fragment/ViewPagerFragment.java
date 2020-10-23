package com.example.daliypractice.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.example.daliypractice.R;
import com.example.daliypractice.adapter.MyViewPager2Adapter;
import com.example.daliypractice.bean.MessageWrap;
import com.example.daliypractice.view.MyDrawerLayout;
import com.example.daliypractice.view.MyNoScrollViewPager;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by chenlp
 * @date 2020/8/19
 * @describe
 */
public class ViewPagerFragment extends Fragment {
    private List<String> dataList = new ArrayList<>();
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ListView mListView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private MyDrawerLayout mMyDrawerLayout;
    private MyNoScrollViewPager mViewPager;

    public ViewPagerFragment(Context context, SwipeRefreshLayout swipeRefreshLayout) {
        mContext = context;
        mSwipeRefreshLayout = swipeRefreshLayout;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_viewpager_drawer, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mLayoutInflater = getLayoutInflater();
        mMyDrawerLayout = view.findViewById(R.id.myDrawerLayout);
        initData();
        initListView();
        initViewPager(view);
        initListener();
    }

    private void initViewPager(View view) {
        mViewPager = view.findViewById(R.id.view_pager);
        List<View> viewList = new ArrayList<>();
        viewList.add(mListView);
        viewList.add(mLayoutInflater.inflate(R.layout.two_item, null, false));
        viewList.add(mLayoutInflater.inflate(R.layout.three_item, null, false));
        mViewPager.setAdapter(new MyViewPager2Adapter(viewList));
    }

    private void initListView() {
        View fruitListView = mLayoutInflater.inflate(R.layout.one_item, null, false);
        mListView = fruitListView.findViewById(R.id.inner_viewpager_list_view);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, dataList);
        mListView.setAdapter(arrayAdapter);
    }

    private void initListener() {
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                mSwipeRefreshLayout.setEnabled(i == 0);
            }
        });



        mMyDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

                mViewPager.setScroll(false);
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                mViewPager.setScroll(true);
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });


    }

    private void initData() {
        for (int i = 0; i < 30; i++) {
            dataList.add("apple" + i);
        }
    }
}
