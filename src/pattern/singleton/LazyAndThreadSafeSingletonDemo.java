package pattern.singleton;

public class LazyAndThreadSafeSingletonDemo {
}

class LazySingleton {
    private static LazySingleton instance;

    private LazySingleton() {
        System.out.println("Initializing a lazy singleton");
    }

    //If there are more than one thread calling getInstance, it can be unsafe, cause, it can create more than one instance
    //We can using keyword synchronized to avoid this case
    public static synchronized LazySingleton getInstance() {
        if(instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }

    //One more way is implement double-check
    public static LazySingleton getInstanceDoubleCheck() {
        if (instance ==null) {
            synchronized (instance) {
                if (instance == null) {
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }
}
