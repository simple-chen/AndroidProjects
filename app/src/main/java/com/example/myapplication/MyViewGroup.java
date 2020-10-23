package com.example.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

/**
 * @author by chenlp
 * @date 2020/10/13
 * @describe
 */
public class MyViewGroup extends LinearLayout {

    private int mLastRawY;

    public MyViewGroup(Context context) {
        super(context);
    }

    public MyViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewGroup(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int mRawY = (int) event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastRawY = mRawY;
                break;

            case MotionEvent.ACTION_MOVE:
                int offset = mRawY - mLastRawY;
                layout(getLeft(), getTop() + offset, getRight(), getBottom() + offset);
                mLastRawY = mRawY;
                break;

        }
      return true;
    }
}
