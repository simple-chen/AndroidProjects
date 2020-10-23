package com.example.daliypractice.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.daliypractice.fragment.MainFragment;
import com.example.daliypractice.fragment.ViewPagerFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by chenlp
 * @date 2020/8/19
 * @describe
 */
public class MyViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragmentList = new ArrayList<>();
    private Context mContext;
    private String[] mTitles;

    public MyViewPagerAdapter(@NonNull FragmentManager fm, Context context, String[] titles, SwipeRefreshLayout swipeRefreshLayout) {
        super(fm);
        mContext = context;
        mTitles = titles;
        mFragmentList.add(new ViewPagerFragment(mContext,swipeRefreshLayout));
        mFragmentList.add(new MainFragment());
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
    }

}
