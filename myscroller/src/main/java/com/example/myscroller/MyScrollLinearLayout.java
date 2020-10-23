package com.example.myscroller;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.Scroller;

import androidx.annotation.Nullable;

/**
 * @author by chenlp
 * @date 2020/9/25
 * @describe
 */
public class MyScrollLinearLayout extends LinearLayout {


    private Scroller mScroller;
    private int mLastScrollY;
    private int mOffsetY;
    private int mLastRawX;
    private int mLastRawY;
    private int mLastScrollX;


    public MyScrollLinearLayout(Context context) {
        this(context,null);
    }

    public MyScrollLinearLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyScrollLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new Scroller(context);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        Log.e("clp", "onLayout: ");

    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e("clp", "onMeasure: ");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

    public int setOffsetParam(int offsetY){
        return offsetY;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int rawY = (int) event.getRawY();
        int rawX = (int) event.getRawX();
        int scrollX = getScrollX();
        int scrollY = getScrollY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mLastRawY = rawY;
                mLastRawX = rawX;
                mLastScrollX = scrollX;
                mLastScrollY = scrollY;
                break;

            case MotionEvent.ACTION_MOVE:
                int offsetX = rawX - mLastRawX;
                int offsetY = rawY - mLastRawY;
//                scrollBy(-offsetX,-offsetY);
                scrollTo(mLastScrollX-offsetX,mLastScrollY-offsetY);
                break;

        }
        return true;
    }


}
