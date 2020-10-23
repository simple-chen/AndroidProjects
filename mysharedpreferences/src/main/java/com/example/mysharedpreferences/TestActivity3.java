package com.example.mysharedpreferences;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * @author by chenlp
 * @date 2020/9/21
 * @describe
 */
public class TestActivity3 extends AppCompatActivity implements View.OnClickListener {

    public static final String FILE_NAME = "hello";

    private Button mRead;
    private Button mWrite;
    private EditText mEdit1;
    private EditText mEdit2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity3);
        initView();
        initListener();
    }

    private void initListener() {
        mRead.setOnClickListener(this);
        mWrite.setOnClickListener(this);
    }

    private void initView() {
        mRead = findViewById(R.id.read);
        mWrite = findViewById(R.id.write);
        mEdit1 = findViewById(R.id.edit1);
        mEdit2 = findViewById(R.id.edit2);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.read:
                mEdit1.setText(read());
                break;

            case R.id.write:
                write(mEdit2.getText().toString());
                mEdit2.setText("");
                break;
        }


    }

    private void write(String content) {
        try {
            FileOutputStream fos = openFileOutput(FILE_NAME, Context.MODE_APPEND);
            PrintStream ps = new PrintStream(fos);
            ps.println(content);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private String read() {
        try {
            FileInputStream fis = openFileInput(FILE_NAME);
            byte[] buff = new byte[1024];
            int hasRead = 0;
            StringBuilder sb = new StringBuilder("");
            while ((hasRead = fis.read(buff)) > 0) {
                sb.append(new String(buff, 0, hasRead));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
