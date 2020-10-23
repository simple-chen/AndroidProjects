package com.example.mysharedpreferences;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author by chenlp
 * @date 2020/9/21
 * @describe
 */
public class TestActivity1 extends AppCompatActivity {

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity1);
        mSharedPreferences = getSharedPreferences("mysharedPreferences", Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        Button read = findViewById(R.id.read);
        Button write = findViewById(R.id.write);
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String time = mSharedPreferences.getString("time", null);
                int randNum = mSharedPreferences.getInt("random", 0);
                String result = time == null ? "您暂时还未写入数据:" : "写入时间为:" + time + "\n上次生成的随机数为:" + randNum;
                Toast.makeText(TestActivity1.this, result, Toast.LENGTH_SHORT).show();
            }
        });

        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日" + "hh:mm:ss");
                mEditor.putString("time", sdf.format(new Date()));
                mEditor.putInt("random", (int) (Math.random() * 100));
                mEditor.apply();
            }
        });
    }
}
