package com.example.daliypractice.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.daliypractice.fragment.MainFragment;
import com.example.daliypractice.fragment.ViewPagerFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by chenlp
 * @date 2020/8/19
 * @describe
 */
public class TabFragmentAdapter extends FragmentStateAdapter {

    private List<Fragment> mFragmentList = new ArrayList<>();
    private Context mContext;



    public TabFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, Context context) {
        super(fragmentManager, lifecycle);
        mContext = context;
//        mFragmentList.add(new ViewPagerFragment(mContext));
        mFragmentList.add(new MainFragment());
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return mFragmentList.size();
    }


}
