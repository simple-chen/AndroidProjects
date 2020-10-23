package com.example.mysharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


    }

    private void initView() {
        Button test1 = findViewById(R.id.test1);
        Button test2 = findViewById(R.id.test2);
        Button test3 = findViewById(R.id.test3);
        test1.setOnClickListener(this);
        test2.setOnClickListener(this);
        test3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.test1:
                Intent intent1 = new Intent(this, TestActivity1.class);
                startActivity(intent1);
                break;

            case R.id.test2:
                Intent intent2 = new Intent(this,TestActivity2.class);
                startActivity(intent2);
                break;

            case R.id.test3:
                Intent intent3 = new Intent(this,TestActivity3.class);
                startActivity(intent3);
                break;
        }
    }
}
