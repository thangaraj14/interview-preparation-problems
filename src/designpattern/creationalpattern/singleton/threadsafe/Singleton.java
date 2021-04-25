package designpattern.creationalpattern.singleton.threadsafe;

public class Singleton {

    private volatile static Singleton uniqueInstance;
    private int value;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (uniqueInstance == null) {
            synchronized (Singleton.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new Singleton();
                }
            }
        }
        return uniqueInstance;
    }

    public String getDescription() {
        return "I'm a thread safe Singleton!";
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
