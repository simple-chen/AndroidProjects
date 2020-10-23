package com.example.myapplication;

import static com.example.myapplication.SerializableTest.deserializeFlyPig;

public class STest {
    public static void main(String[] args) throws Exception {
        FlyPig flyPig = deserializeFlyPig();
        System.out.println("flyPig.toString() = " + flyPig.toString());
    }
}
