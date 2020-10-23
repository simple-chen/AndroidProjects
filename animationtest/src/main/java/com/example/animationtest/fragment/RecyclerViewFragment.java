package com.example.animationtest.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.animationtest.R;
import com.example.animationtest.adapter.FruitAdapter;
import com.example.animationtest.view.MyRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by chenlp
 * @date 2020/10/22
 * @describe
 */
public class RecyclerViewFragment extends Fragment {
    private List<String> mFruitList = new ArrayList<>();
    private FruitAdapter mFruitAdapter;
    private Context mContext;
    private OnSwitchListener mOnSwitchListener;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_view_fragment,container,false);
        initView(view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFruitAdapter = new FruitAdapter();
        initData();

    }

    private void initView(View view) {
        mFruitAdapter.addFruitAdapter(mFruitList);
        MyRecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mFruitAdapter);
        mFruitAdapter.setOnItemClickListener(new FruitAdapter.OnItemClickListener() {
            @Override
            public void itemClick(int position) {
                if (mOnSwitchListener!=null){
                    mOnSwitchListener.onSwitch(mFruitList.get(position));
                }
                mFruitAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initData() {
        for (int i = 0; i < 5; i++) {
            mFruitList.add("apple" + i);
        }
    }

    public interface OnSwitchListener{
        void onSwitch(String s);
    }

    public void setOnSwitchListener(OnSwitchListener onSwitchListener){
        mOnSwitchListener = onSwitchListener;
    }
}
