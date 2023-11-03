// Not thread safe
class Singleton {
    private static Singleton singleton;

    private Singleton() {
    }

    // lazy init
    public static Singleton getInstance() {
        if (singleton == null) {
            singleton = new Singleton();
        }

        return singleton;
    }
}

// thread safe, but using synchronized every time while creating singleton object is expensive, decreases the performance of program
class ThreadSafe {
    private static ThreadSafe singleton;

    private ThreadSafe() {
    }

    public synchronized static ThreadSafe getInstance() {
        if (singleton == null) {
            singleton = new ThreadSafe();
        }

        return singleton;
    }
}

// thread safe, eager init is expensive as JVM creates object all the time
class EagerInit {
    private static final EagerInit singleton = new EagerInit();

    private EagerInit() {
    }

    public static EagerInit getInstance() {
        return singleton;
    }
}

// thread safe, expensive as JVM creates object all the time
class DoubleCheckedLocking {
    // volatile makes sure value cached in thread cache/stack is written to main memory asap, or else only static means it might take time to write
    // https://www.geeksforgeeks.org/volatile-keyword-in-java/
    private static volatile DoubleCheckedLocking singleton;

    private DoubleCheckedLocking() {
    }

    public static DoubleCheckedLocking getInstance() {
        // synchronized only if singleton is null, or else all objects will wait for nothing
        if (singleton == null) {
            synchronized (DoubleCheckedLocking.class) {
                if (singleton == null) {
                    singleton = new DoubleCheckedLocking();
                }
            }
        }
        return singleton;
    }
}

class Solution {
    public static void main(String[] args) {
        var obj1 = Singleton.getInstance();
        var obj2 = Singleton.getInstance();
        System.out.println(obj1.equals(obj2));

        var obj3 = ThreadSafe.getInstance();
        var obj4 = ThreadSafe.getInstance();
        System.out.println(obj3.equals(obj4));

        var obj5 = EagerInit.getInstance();
        var obj6 = EagerInit.getInstance();
        System.out.println(obj5.equals(obj6));

        var obj7 = DoubleCheckedLocking.getInstance();
        var obj8 = DoubleCheckedLocking.getInstance();
        System.out.println(obj7.equals(obj8));
    }
}
// https://www.geeksforgeeks.org/singleton-design-pattern/