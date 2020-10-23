package com.example.animationtest.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.animationtest.R;
import com.example.animationtest.fragment.FrameAnimationFragment;
import com.example.animationtest.fragment.TweenAnimationFragment;

/**
 * @author by chenlp
 * @date 2020/10/22
 * @describe
 */
public class MainActivity extends AppCompatActivity {

    private ImageView mImg_down;
    private PullDownSelectedPopWindow mPullDownSelectedPopWindow;
    private boolean isShowing;
    private LinearLayout mNavigation_bar;
    private FrameAnimationFragment mFrameAnimationFragment;
    private TweenAnimationFragment mTweenAnimationFragment;
    private FragmentTransaction mFragmentTransaction;
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }


//    @Override
//    public <T extends View> T findViewById(int id) {
//        if (id == R.id.ll_layout&&mPullDownSelectedPopWindow!=null){
//            return mPullDownSelectedPopWindow.getLayout().findViewById(id);
//        }
//            return super.findViewById(id);
//    }

    private void initView() {
        mImg_down = findViewById(R.id.img_down);
        mNavigation_bar = findViewById(R.id.navigation_bar);
        mFrameAnimationFragment = new FrameAnimationFragment();
        mTweenAnimationFragment = new TweenAnimationFragment();
        mFragmentManager = getSupportFragmentManager();

        if (mPullDownSelectedPopWindow == null) {
            mPullDownSelectedPopWindow = new PullDownSelectedPopWindow(MainActivity.this);
        }

        mImg_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShowing) {
                    isShowing = false;
                    mPullDownSelectedPopWindow.dismiss();
                } else {
                    isShowing = true;
                    mPullDownSelectedPopWindow.showPopLocation(mNavigation_bar);
                }
            }
        });

        mPullDownSelectedPopWindow.setOnSwitchListener(new PullDownSelectedPopWindow.OnSwitchListener() {
            @Override
            public void onSwitch(String s) {
                mFragmentTransaction = mFragmentManager.beginTransaction();//每次打算执行任何数量的Fragment操作时，都需要开始一个新的FragmentTransaction。
                switchFragment(s);
                mPullDownSelectedPopWindow.dismiss();
            }
        });
    }

    public void switchFragment(String s){
        if (s.equals("apple0")){
            mFragmentTransaction.replace(R.id.ll_container,mFrameAnimationFragment).commit();
        }else if (s.equals("apple1")){
            mFragmentTransaction.replace(R.id.ll_container,mTweenAnimationFragment).commit();
        }
    }
}
