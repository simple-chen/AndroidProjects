package com.example.animationtest.adapter;

import com.example.animationtest.R;

import java.util.List;

import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;

/**
 * @author by chenlp
 * @date 2020/10/23
 * @describe
 */
public class MyTabAdapter implements TabAdapter {
    private List<String> mTitleList;

    public MyTabAdapter(List<String> titleList) {
        mTitleList = titleList;
    }

    @Override
    public int getCount() {
        return mTitleList.size();
    }

    @Override
    public ITabView.TabBadge getBadge(int position) {
        return null;
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
    public int getBackground(int position) {
        return R.drawable.selected_background;
    }
}
