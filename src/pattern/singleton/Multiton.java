package pattern.singleton;

import java.util.HashMap;

public class Multiton {
    public static void main(String[] args) {
        Printer main = Printer.get(SubSystem.PRIMARY);
        Printer aux = Printer.get(SubSystem.AUXILIARY);
        Printer aux2 = Printer.get(SubSystem.AUXILIARY);
    }
}

enum SubSystem {
    PRIMARY,
    AUXILIARY,
    FALLBACK
}

class Printer {
    private static int instanceCount;

    private Printer(){
        instanceCount++;
        System.out.println("Initialize new printer, total instance: " + instanceCount);
    }

    private static HashMap<SubSystem, Printer> instances = new HashMap<>();

    public static Printer get(SubSystem ss) {
        if(instances.containsKey(ss)){
            return instances.get(ss);
        }
        Printer instance = new Printer();
        instances.put(ss, instance);
        return instance;
    }
}