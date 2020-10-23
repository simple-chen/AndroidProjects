package com.example.handlertest;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by chenlp
 * @date 2020/8/21
 * @describe
 */
public class FirstActivity extends AppCompatActivity {

    public static final String UPPER_NUM = "upper_num";
    private EditText mEdit_input_num;
    //    private CalThread mCalThread;
    private HandlerThread mHandlerThread;
    private Handler mHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        mEdit_input_num = findViewById(R.id.edit_input_num);
        Button btn_calculate = findViewById(R.id.btn_calculate);
//        mCalThread = new CalThread();
//        mCalThread.start();
        initHandlerThread();


        btn_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cal(mEdit_input_num);
            }
        });
    }

    private void initHandlerThread() {
        //线程开启时也就是run方法运行起来后，线程同时创建一个含有消息队列的looper，并对外提供自己这个对象的get方法，
        // 这就是和普通的Thread不一样的地方。可用于多个耗时任务要串行执行。
        mHandlerThread = new HandlerThread("handlerThread");
        mHandlerThread.start();
        Looper looper = mHandlerThread.getLooper();
        mHandler = new Handler(looper) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                if (msg.what == 0x123) {
                    int upper = msg.getData().getInt(UPPER_NUM);
                    List<Integer> numList = new ArrayList<>();
                    outer:
                    for (int i = 2; i <= upper; i++) {
                        int j = 2;
                        while (j < Math.sqrt(i)) {
                            if (i != 2 && i % j == 0) {
                                continue outer;
                            }
                            j++;
                        }
                        numList.add(i);
                    }
                    Toast.makeText(FirstActivity.this, numList.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    private void cal(EditText edit_input_num) {
        Message msg = Message.obtain();
        msg.what = 0x123;
        Bundle bundle = new Bundle();
        bundle.putInt(UPPER_NUM, Integer.parseInt(edit_input_num.getText().toString()));
        msg.setData(bundle);
        mHandler.sendMessage(msg);
    }
//
//     class CalThread extends Thread {
//        private MyHandler mHandler;
//
//        @Override
//        public void run() {
//            Looper.prepare();
//            mHandler = new MyHandler(new WeakReference<>(FirstActivity.this));
//            Looper.loop();
//
//        }
//    }

//
//    static class MyHandler extends Handler{
//        private WeakReference<FirstActivity> mActivityWeakReference;
//        public MyHandler(WeakReference<FirstActivity> weakReference){
//            mActivityWeakReference = weakReference;
//        }
//
//        @Override
//        public void handleMessage(@NonNull Message msg) {
//            if (msg.what == 0x123) {
//                int upper = msg.getData().getInt(UPPER_NUM);
//                List<Integer> numList = new ArrayList<>();
//                outer:
//                for (int i = 2; i <= upper; i++) {
//                    int j = 2;
//                    while (j < Math.sqrt(i)) {
//                        if (i != 2 && i % j == 0) {
//                            continue outer;
//                        }
//                        j++;
//                    }
//                    numList.add(i);
//                }
//                Toast.makeText(mActivityWeakReference.get(), numList.toString(), Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
}



