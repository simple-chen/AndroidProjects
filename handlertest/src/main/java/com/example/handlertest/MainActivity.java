package com.example.handlertest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private ImageView mImg_show;
    MyHandler mMyHandler = new MyHandler(new WeakReference<MainActivity>(this));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImg_show = findViewById(R.id.show);
        Button btn_next = findViewById(R.id.next);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                mMyHandler.sendEmptyMessage(0X1233);
            }
        },0,1200);//0s后执行，每隔1.2s执行一次


        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this,FirstActivity.class);
                startActivity(intent);
            }
        });
    }


    static class MyHandler extends Handler{
        private WeakReference<MainActivity> activity;
        //由于仅有一条来自Handler的弱引用指向Activity，所以GC仍然会在检查的时候把Activity回收掉。这样，内存泄露的问题就不会出现了。
        public MyHandler(WeakReference<MainActivity> activity){
            this.activity = activity;
        }

        private int[] imageIds = new int[]{R.mipmap.ic_launcher,R.drawable.home_bg,R.drawable.home_icon_alarm,R.drawable.home_logo};
        private int currentImageId = 0;

        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what == 0X1233){
                activity.get().mImg_show.setImageResource(imageIds[currentImageId++%imageIds.length]);
            }
        }
    }
}
