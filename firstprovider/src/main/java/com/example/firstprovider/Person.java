package com.example.firstprovider;

import android.util.Log;

import androidx.annotation.NonNull;

/**
 * @author by chenlp
 * @date 2020/9/17
 * @describe
 */
public class Person implements Comparable<Person> {
    private int id;
    private String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

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

    @Override
    public int compareTo(Person o) {
        int num = this.id - o.getId();
        System.out.println("compareTo:  this.id" + this.id);
        System.out.println("compareTo:  o.getId()" +  o.getId());
        System.out.println("compareTo:  num" +  num);
        return (num == 0) ? this.name.compareTo(o.getName()) :
                this.id - o.getId();
    }


    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

