package com.example.myapplication;

import java.io.Serializable;

public class FlyPig implements Serializable {
//    private static final long serialVersionUID=3L;
    private int id;
    private String name;
    private String color;
    private int ss;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

//    @Override
//    public String toString() {
//        return "FlyPig{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", color='" + color + '\'' +
//                '}';
//    }
}
