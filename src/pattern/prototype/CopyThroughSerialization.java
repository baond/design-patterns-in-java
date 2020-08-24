package pattern.prototype;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class CopyThroughSerialization {
    public static void main(String[] args) throws Exception {
        Foo foo = new Foo("test", "abc");
        Foo foo2 = foo.deepCopy();
        foo2.whatever = "xyz";
        System.out.println(foo);
        System.out.println(foo2);
    }
}

class Foo implements Serializable {
    public String stuff, whatever;

    public Foo(String stuff, String whatever) {
        this.stuff = stuff;
        this.whatever = whatever;
    }

    @Override
    public String toString() {
        return "Foo{" +
                "stuff='" + stuff + '\'' +
                ", whatever='" + whatever + '\'' +
                '}';
    }

    public Foo deepCopy() throws Exception
    {
        //Serialization of object
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bos);
        out.writeObject(this);

        //De-serialization of object
        ByteArrayInputStream bis = new   ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream in = new ObjectInputStream(bis);
        Foo copied = (Foo) in.readObject();

        return copied;
    }
}