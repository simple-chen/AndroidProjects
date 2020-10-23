package com.example.mypractice;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

/**
 * @author by chenlp
 * @date 2020/9/23
 * @describe
 */
public class ViewGroupB extends LinearLayout {
    public ViewGroupB(Context context) {
        super(context);
    }

    public ViewGroupB(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewGroupB(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.e("ViewGroupB", "onInterceptTouchEvent_ACTION_DOWN");
                break;

            case MotionEvent.ACTION_MOVE:
                Log.e("ViewGroupB", "onInterceptTouchEvent_ACTION_MOVE");
                break;

            case MotionEvent.ACTION_UP:
                Log.e("ViewGroupB", "onInterceptTouchEvent_ACTION_UP");
                break;
        }
        Log.e("ViewGroupB", "onInterceptTouchEvent: true");
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.e("ViewGroupB", "onTouchEvent_ACTION_DOWN");
                break;

            case MotionEvent.ACTION_MOVE:
                Log.e("ViewGroupB", "onTouchEvent_ACTION_MOVE");
                break;

            case MotionEvent.ACTION_UP:
                Log.e("ViewGroupB", "onTouchEvent_ACTION_UP");
                break;
        }
        Log.e("ViewGroupB", "onTouchEvent: true");
        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.e("ViewGroupB", "dispatchTouchEvent_ACTION_DOWN");
                break;

            case MotionEvent.ACTION_MOVE:
                Log.e("ViewGroupB", "dispatchTouchEvent_ACTION_MOVE");
                break;

            case MotionEvent.ACTION_UP:
                Log.e("ViewGroupB", "dispatchTouchEvent_ACTION_UP");
                break;
        }
        Log.e("ViewGroupB", "dispatchTouchEvent: " + super.onInterceptTouchEvent(ev));
        return super.dispatchTouchEvent(ev);
    }
}
