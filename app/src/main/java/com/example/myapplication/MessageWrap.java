package com.example.myapplication;

public class MessageWrap {
    public final String message;

    public MessageWrap(String message) {
        this.message = message;
    }

    public static MessageWrap getInstance(String message){
        return new MessageWrap(message);
    }

}
