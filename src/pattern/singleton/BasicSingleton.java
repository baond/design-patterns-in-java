package pattern.singleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class BasicSingleton {
    public static void main(String[] args) throws Exception {
        BSingleton bSingleton = BSingleton.getInstance();
        saveToFile(bSingleton, "/tmp/bSingleton.txt");
        BSingleton newSingleton = readObjectFromFile("/tmp/bSingleton.txt");
        newSingleton.value = 222;
        System.out.println(bSingleton);
        System.out.println(newSingleton);
    }

    public static void saveToFile(BSingleton basicSingleton, String fileName) throws Exception {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);){
            objectOutputStream.writeObject(basicSingleton);
        }
    }

    public static BSingleton readObjectFromFile(String fileName) throws Exception {
        try(FileInputStream fileInputStream = new FileInputStream(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){
            return (BSingleton) objectInputStream.readObject();
        }
    }
}

//If Singleton implement Serializable, we can work through to create new instance by saving object to file and load object from file
class BSingleton implements Serializable {
    private static BSingleton instance = new BSingleton();
    public int value = 111;

    public static BSingleton getInstance() {
        return instance;
    }

    @Override
    public String toString() {
        return "Singleton with value " + value;
    }

    //To resolve this problem, we can produce a method name readResolve
    protected Object readResolve(){
        return instance;
    }
}
