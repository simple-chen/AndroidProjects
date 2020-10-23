package com.example.daliypractice.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;

/**
 * @author by chenlp
 * @date 2020/8/27
 * @describe
 */
public class MyDrawerLayout extends DrawerLayout {
    public MyDrawerLayout(@NonNull Context context) {
        this(context, null);
    }

    public MyDrawerLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyDrawerLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("clp", "onInterceptTouchEvent: downX" + ev.getX());
                if (ev.getX() < 30) {
                    return true;
                }
                break;
        }
        return false;
    }
}
