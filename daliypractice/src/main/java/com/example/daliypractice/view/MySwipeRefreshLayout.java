package com.example.daliypractice.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

/**
 * @author by chenlp
 * @date 2020/8/27
 * @describe 解决下拉刷新和viewPager的冲突
 * 思路
 * 1. 因为下拉刷新，只有纵向滑动的时候才有效，那么我们就判断此时是纵向滑动还是横向滑动就可以了。
 * 2. 纵向滑动就拦截事件，横向滑动不拦截。
 * 3. 怎么判断是纵向滑动还是横向滑动，只要判断Y轴的移动距离大于X轴的移动距离那么就判定为纵向滑动就行了。
 */
public class MySwipeRefreshLayout extends SwipeRefreshLayout {

    private float mStartX;
    private float mStartY;
    private boolean isDrag;

    public MySwipeRefreshLayout(@NonNull Context context) {
        this(context, null);
    }

    public MySwipeRefreshLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mStartX = ev.getRawX();
                mStartY = ev.getRawY();
                isDrag = false;
                break;
            case MotionEvent.ACTION_MOVE:
                if (isDrag) {//如果正在拖拽 不拦截事件 直接返回false
                    return false;
                }
                float endX = ev.getRawX();
                float endY = ev.getRawY();
                // 如果X轴位移大于Y轴位移，那么将事件交给viewPager处理。
                if (Math.abs(mStartX - endX) > Math.abs(mStartY - endY)) {
                    isDrag = true;
                    return false;
                }
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                isDrag = false;
        }

        return super.onInterceptTouchEvent(ev);
    }
}
