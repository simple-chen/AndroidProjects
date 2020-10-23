package com.example.animationtest.activity;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.animationtest.R;
import com.example.animationtest.adapter.FruitAdapter;
import com.example.animationtest.adapter.MyTabAdapter;
import com.example.animationtest.view.MyRecyclerView;

import java.util.ArrayList;
import java.util.List;

import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.widget.TabView;

/**
 * @author by chenlp
 * @date 2020/10/22
 * @describe
 */
public class PullDownSelectedPopWindow extends PopupWindow implements VerticalTabLayout.OnTabSelectedListener{

    private Context mContext;
    private View mConvertView;
    private List<String> mFruitList = new ArrayList<>();
    private FruitAdapter mFruitAdapter;
    private OnSwitchListener mOnSwitchListener;
    private VerticalTabLayout mVerticalTabLayout;
    private List<String> mTitleList = new ArrayList<>();


    public PullDownSelectedPopWindow(Context context) {
        super(context);
        mContext = context;
        initPop();
    }

    private void initPop() {
        LinearLayout linearLayout = new LinearLayout(mContext);
        mConvertView = LayoutInflater.from(mContext).inflate(R.layout.pop_pull_down,linearLayout);
        setContentView(mConvertView);
        setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        setTouchable(true);
        setBackgroundDrawable(new ColorDrawable(0));
        setOutsideTouchable(true);
        initView();
    }

    private void initView() {
        initDatas();
        mVerticalTabLayout = mConvertView.findViewById(R.id.vertical_tab);
        MyRecyclerView recyclerView =mConvertView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mFruitAdapter = new FruitAdapter();
        recyclerView.setAdapter(mFruitAdapter);
        mFruitAdapter.addFruitAdapter(mFruitList);
        initListener();

    }

    private void initListener() {
        mFruitAdapter.setOnItemClickListener(new FruitAdapter.OnItemClickListener() {
            @Override
            public void itemClick(int position) {
                if (mOnSwitchListener!=null){
                    mOnSwitchListener.onSwitch(mFruitList.get(position));
                }
                mFruitAdapter.notifyDataSetChanged();
            }
        });

         mVerticalTabLayout.setTabAdapter(new MyTabAdapter(mTitleList));
         mVerticalTabLayout.addOnTabSelectedListener(this);

    }

    private void initDatas() {
        for (int i = 0; i < 5; i++) {
            mFruitList.add("apple" + i);
        }
        mTitleList.add("first");
        mTitleList.add("second");
        mTitleList.add("third");
    }

    public void showPopLocation(View view){
        showAsDropDown(view,0,0);
    }

    @Override
    public void onTabSelected(TabView tab, int position) {

    }

    @Override
    public void onTabReselected(TabView tab, int position) {

    }


    public interface OnSwitchListener{
        void onSwitch(String s);
    }

    public void setOnSwitchListener(OnSwitchListener onSwitchListener){
        mOnSwitchListener = onSwitchListener;
    }


}
