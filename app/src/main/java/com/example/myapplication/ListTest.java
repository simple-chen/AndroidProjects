package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by chenlp
 * @date 2020/8/14
 * @describe
 */
public class ListTest {
    public static void main(String[] args) {
        List<Integer> arr1 = new ArrayList<>();
        List<Integer> arr2 = new ArrayList<>();
//        arr1.add(1);
//        arr1.add(2);
//        arr2.add(3);
//        arr2.add(4);
////        arr1.addAll(arr2);
//        arr1.addAll(arr2);
//        System.out.println("arr1 = " + arr1);
//        System.out.println("arr2 = " + arr2);
        for (Integer aList : arr1) {
            System.out.println("aList = " + aList);
        }
    }
}
