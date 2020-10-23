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
public class ViewGroupA extends LinearLayout {
    public ViewGroupA(Context context) {
        super(context);
    }

    public ViewGroupA(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewGroupA(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.e("ViewGroupA", "onInterceptTouchEvent_ACTION_DOWN");
                break;

            case MotionEvent.ACTION_MOVE:
                Log.e("ViewGroupA", "onInterceptTouchEvent_ACTION_MOVE");
                break;

            case MotionEvent.ACTION_UP:
                Log.e("ViewGroupA", "onInterceptTouchEvent_ACTION_UP");
                break;
        }

        Log.e("ViewGroupA", "onInterceptTouchEvent: " + super.onInterceptTouchEvent(ev));
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.e("ViewGroupA", "onTouchEvent_ACTION_DOWN");
                break;

            case MotionEvent.ACTION_MOVE:
                Log.e("ViewGroupA", "onTouchEvent_ACTION_MOVE");
                break;

            case MotionEvent.ACTION_UP:
                Log.e("ViewGroupA", "onTouchEvent_ACTION_UP");
                break;
        }
        Log.e("ViewGroupA", "onTouchEvent: " + super.onInterceptTouchEvent(event));
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.e("ViewGroupA", "dispatchTouchEvent_ACTION_DOWN");
                break;

            case MotionEvent.ACTION_MOVE:
                Log.e("ViewGroupA", "dispatchTouchEvent_ACTION_MOVE");
                break;

            case MotionEvent.ACTION_UP:
                Log.e("ViewGroupA", "dispatchTouchEvent_ACTION_UP");
                break;
        }
        Log.e("ViewGroupA", "dispatchTouchEvent: " + super.onInterceptTouchEvent(ev));
        return super.dispatchTouchEvent(ev);
    }
}
