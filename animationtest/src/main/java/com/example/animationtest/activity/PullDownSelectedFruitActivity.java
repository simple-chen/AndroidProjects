package com.example.animationtest.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.animationtest.R;
import com.example.animationtest.adapter.ViewPagerAdapter;
import com.example.animationtest.fragment.FrameAnimationFragment;
import com.example.animationtest.fragment.RecyclerViewFragment;
import com.example.animationtest.fragment.TweenAnimationFragment;

import java.util.ArrayList;
import java.util.List;

import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.TabView;
@Deprecated
public class PullDownSelectedFruitActivity extends AppCompatActivity implements VerticalTabLayout.OnTabSelectedListener,ViewPager.OnPageChangeListener{
    private List<Fragment> mFragmentList = new ArrayList<>();
    private List<String> mTitleList = new ArrayList<>();
    private ViewPager mViewPager;
    private VerticalTabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_pull_down);
        initView();
        initData();
    }

    private void initData() {
        mTitleList.add("first");
        mTitleList.add("second");
        mTitleList.add("third");
        mTabLayout.setTabAdapter(new TabAdapter() {
            @Override
            public int getCount() {
                return mTitleList.size();
            }

            @Override
            public ITabView.TabBadge getBadge(int position) {
                return new ITabView.TabBadge.Builder().setBackgroundColor(0xff111111).build();
            }

            @Override
            public ITabView.TabIcon getIcon(int position) {
                return null;
            }

            @Override
            public ITabView.TabTitle getTitle(int position) {
                return new ITabView.TabTitle.Builder().
                        setContent(mTitleList.get(position)).setTextColor(0xFF000000,0xffffffff).build();
            }

            @Override
            public int getBackground(int position) {//选中的颜色
                return R.drawable.selected_background;
            }
        });
        mFragmentList.add(new FrameAnimationFragment());
        mFragmentList.add(new TweenAnimationFragment());
        mFragmentList.add(new RecyclerViewFragment());
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), mFragmentList);
        mViewPager.setAdapter(viewPagerAdapter);
        mViewPager.addOnPageChangeListener(this);
        mTabLayout.addOnTabSelectedListener(this);
    }

    private void initView() {
        mViewPager = findViewById(R.id.view_pager_animation);
        mTabLayout = findViewById(R.id.vertical_tab);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mTabLayout.setTabSelected(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onTabSelected(TabView tab, int position) {
        mViewPager.setCurrentItem(position);

    }

    @Override
    public void onTabReselected(TabView tab, int position) {

    }
}
