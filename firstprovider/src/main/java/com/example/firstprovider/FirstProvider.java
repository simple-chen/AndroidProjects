package com.example.firstprovider;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

public class FirstProvider extends ContentProvider {

    @Override
    public boolean onCreate() {
        System.out.println("===onCreate===");
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        System.out.println("uri.toString()" +"===query方法被调用========");
        System.out.println("where参数为："+ s);

        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        System.out.println("uri.toString() " + "===insert方法被调用===");
        System.out.println("ContentValues参数为 = " + contentValues);
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        System.out.println("uri.toString = " + "delete被调用======");
        System.out.println("where参数为 " + s);
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        System.out.println("uri.toString = " + "update被调用======");
        System.out.println("where参数为  " + s + "contentValues"+contentValues);
        return 0;
    }
}
