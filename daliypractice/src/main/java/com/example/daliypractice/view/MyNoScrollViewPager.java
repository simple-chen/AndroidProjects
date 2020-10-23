package com.example.daliypractice.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

/**
 * @author by chenlp
 * @date 2020/8/19
 * @descibe
 */
public class MyNoScrollViewPager extends ViewPager {

    private boolean mScrollable;

    public MyNoScrollViewPager(@NonNull Context context) {
        super(context);
    }

    public MyNoScrollViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScroll(boolean scrollable) {
        mScrollable = scrollable;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (mScrollable){
            return super.onInterceptTouchEvent(ev);
        }else{
            return false;
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mScrollable){
            return super.onTouchEvent(ev);
        }else{
            return false;
        }
    }
}
