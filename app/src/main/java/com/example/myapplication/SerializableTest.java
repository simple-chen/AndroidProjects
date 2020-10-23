package com.example.myapplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializableTest {
    public static void main(String[] args) throws Exception {

        serializableFlyPig();
        deserializeFlyPig();
    }

    private static void serializableFlyPig() throws IOException {
        FlyPig flyPig = new FlyPig();
        flyPig.setId(5);
        flyPig.setName("xiaoming");
        flyPig.setColor("red");
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(new File("G:/flyPig.txt")));
        os.writeObject(flyPig);
        System.out.println(" FlyPig 对象序列化成功" + flyPig);
        os.close();
        System.out.println(flyPig);
    }

    public static FlyPig deserializeFlyPig() throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("G:/flyPig.txt")));
        FlyPig flyPig = (FlyPig) ois.readObject();
        System.out.println("FlyPig 对象反序列化成功" + flyPig);
        return flyPig;
    }


}
