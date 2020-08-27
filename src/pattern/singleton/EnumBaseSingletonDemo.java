package pattern.singleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class EnumBaseSingletonDemo {
    public static void saveToFile(EnumBaseSingleton singleton, String fileName) throws Exception {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);) {
            objectOutputStream.writeObject(singleton);
        }
    }

    public static EnumBaseSingleton readObjectFromFile(String fileName) throws Exception {
        try (FileInputStream fileInputStream = new FileInputStream(fileName);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            return (EnumBaseSingleton) objectInputStream.readObject();
        }
    }

    public static void main(String[] args) throws Exception {
        String fileName = "instance.bin";
//        EnumBaseSingleton enumBaseSingleton = EnumBaseSingleton.INSTANCE;
//        enumBaseSingleton.setValue(222);
//        saveToFile(enumBaseSingleton, fileName);
        //value is not be serialized
        EnumBaseSingleton enumBaseSingleton1 = readObjectFromFile(fileName);
        System.out.println(enumBaseSingleton1.getValue());
    }

}


//This implementation has a limitation that when serialize enum, it's properties is don't serializable
enum EnumBaseSingleton {
    INSTANCE;

    EnumBaseSingleton() {
        this.value = 111;
    }

    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
