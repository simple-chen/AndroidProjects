package com.example.mypractice;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author by chenlp
 * @date 2020/9/23
 * @describe
 */
public class ViewA extends View {
    public ViewA(Context context) {
        super(context);
    }

    public ViewA(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewA(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.e("ViewA", "onTouchEvent_ACTION_DOWN");
                break;

            case MotionEvent.ACTION_MOVE:
                Log.e("ViewA", "onTouchEvent_ACTION_MOVE");
                break;

            case MotionEvent.ACTION_UP:
                Log.e("ViewA", "onTouchEvent_ACTION_UP");
                break;
        }
        Log.e("ViewA", "onTouchEvent: "+ super.onTouchEvent(event));
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.e("ViewA", "dispatchTouchEvent_ACTION_DOWN");
                break;

            case MotionEvent.ACTION_MOVE:
                Log.e("ViewA", "dispatchTouchEvent_ACTION_MOVE");
                break;

            case MotionEvent.ACTION_UP:
                Log.e("ViewA", "dispatchTouchEvent_ACTION_UP");
                break;
        }
        Log.e("ViewA", "dispatchTouchEvent: "+ super.onTouchEvent(ev));
        return super.dispatchTouchEvent(ev);
    }
}
