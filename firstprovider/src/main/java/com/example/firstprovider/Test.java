package com.example.firstprovider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @author by chenlp
 * @date 2020/9/17
 * @describe
 */
public class Test {
    public static void main(String[] args) {
        TreeSet<Person> treeSet = new TreeSet<>();
        treeSet.add(new Person(24,"张三"));
        treeSet.add(new Person(28,"张三"));
        treeSet.add(new Person(25,"张三"));
        treeSet.add(new Person(26,"张三"));
        treeSet.add(new Person(27,"张三"));
        System.out.println("treeSet = " + treeSet.toString());
    }
}
