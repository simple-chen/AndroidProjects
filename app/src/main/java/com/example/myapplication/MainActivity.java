package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private LinearLayout mLl_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLl_layout = findViewById(R.id.ll_layout);
        FrameLayout.LayoutParams layoutParams  = (FrameLayout.LayoutParams) mLl_layout.getLayoutParams();
        Log.e("clp------", "onCreate:layoutParams.height" + layoutParams.height + "height" + mLl_layout.getHeight());
        Log.e("clp------", "onCreate:mLl_layout.getHeight" + mLl_layout.getHeight() + "getMeasuredHeight" + mLl_layout.getMeasuredHeight());

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        FrameLayout.LayoutParams layoutParams  = (FrameLayout.LayoutParams) mLl_layout.getLayoutParams();
        Log.e("clp------", "onWindowFocusChanged:layoutParams.height" + layoutParams.height + "height" + mLl_layout.getHeight());
        Log.e("clp------", "onWindowFocusChanged:mLl_layout.getHeight" + mLl_layout.getHeight() + "getMeasuredHeight" + mLl_layout.getMeasuredHeight());
    }
}