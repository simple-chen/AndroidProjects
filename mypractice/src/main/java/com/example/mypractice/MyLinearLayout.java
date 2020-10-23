package com.example.mypractice;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * @author by chenlp
 * @date 2020/9/23
 * @describe
 */
public class MyLinearLayout extends LinearLayout {

    private Scroller mScroller;
    private int mLastScrollY;

    public MyLinearLayout(Context context) {
        super(context);
        mScroller = new Scroller(context);
    }

    public MyLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);
    }

    public MyLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new Scroller(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int scrollY = getScrollY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mLastScrollY = scrollY;
                break;
            case MotionEvent.ACTION_MOVE:
//                mScroller.startScroll(mLastScrollY,);


        }

        return true;
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()){
            scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            postInvalidate();
        }
    }
}
