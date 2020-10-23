package com.example.mysharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author by chenlp
 * @date 2020/9/21
 * @describe
 */
public class TestActivity2 extends AppCompatActivity {
    private SharedPreferences mSharedPreferences;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity2);
        mSharedPreferences = getSharedPreferences("count", Context.MODE_PRIVATE);
        int count = mSharedPreferences.getInt("count",0);
        Toast.makeText(this,"程序以前被使用了" + count + "次",Toast.LENGTH_SHORT).show();
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt("count",++count);
        editor.apply();
    }
}
