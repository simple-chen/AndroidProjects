package com.example.firstprovider;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;

/**
 * @author by chenlp
 * @date 2020/9/15
 * @describe
 */
public class MainActivity extends Activity {
    private Uri uri = Uri.parse("content://com.example.firstprovider/");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    public void query(View resource){
        Cursor c = getContentResolver().query(uri,null,"query_where",null,null);
        Toast.makeText(this,"远程ContentProvider返回的Cursor为:" + c,Toast.LENGTH_SHORT).show();
    }

    public void insert(View source){
        ContentValues values = new ContentValues();
        values.put("name","fkjava");
        Uri newUri = getContentResolver().insert(uri,values);
        Toast.makeText(this,"远程ContentProvider新插入记录Uri "+newUri,Toast.LENGTH_SHORT).show();
    }
    public void update(View source){
        ContentValues values = new ContentValues();
        values.put("name","fkjava");
        int count = getContentResolver().update(uri,values,"update_where",null);
        Toast.makeText(this,"远程ContentProvider更新的条数 "+count,Toast.LENGTH_SHORT).show();
    }
    public void delete(View source){
        int count = getContentResolver().delete(uri,"delete_where",null);
        Toast.makeText(this,"远程ContentProvider删除记录数为 "+count,Toast.LENGTH_SHORT).show();
    }
}
