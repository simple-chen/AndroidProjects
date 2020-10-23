package com.example.myapplication;

import android.text.TextUtils;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author by chenlp
 * @date 2020/7/29
 * @describe
 */
public class User implements Serializable {
    private String name;
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&name.equals(user.name);
    }

}
