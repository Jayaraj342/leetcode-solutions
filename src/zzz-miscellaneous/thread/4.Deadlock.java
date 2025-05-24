// Thread.State
// https://stackoverflow.com/questions/32978574/monitor-in-java-threads
class SharedResource {
    public void normalMethod() {
        System.out.println("Thread " + Thread.currentThread().getName() + " is executing normalMethod.");
    }

    public synchronized void synchronizedMethod() {  // Locks on `this`
        System.out.println("Thread " + Thread.currentThread().getName() + " is executing synchronizedMethod.");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void synchronizedBlock() {
        synchronized (this) {  // Locks on `this`
            System.out.println("Thread " + Thread.currentThread().getName() + " is executing synchronizedBlock.");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class MonitorTest {
    public static void main(String[] args) {
        SharedResource obj = new SharedResource();

        Thread t1 = new Thread(() -> obj.synchronizedMethod(), "T1");
        Thread t2 = new Thread(() -> obj.synchronizedBlock(), "T2");
        Thread t3 = new Thread(() -> obj.normalMethod(), "T3");

        t1.start();
        t2.start();
        t3.start();
    }
}

class SharedResource {
    public static synchronized void staticMethod() {
        System.out.println("Thread " + Thread.currentThread().getName() + " is executing staticMethod.");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// if a method is static synchronized, then no two threads can execute it at the same time, even if they are working with different instances of the class
// static synchronized method locks on the class-level monitor, not on this

class StaticSyncExample {
    public static void main(String[] args) {
        SharedResource obj1 = new SharedResource();
        SharedResource obj2 = new SharedResource();

        Thread t1 = new Thread(() -> obj1.staticMethod(), "T1");
        Thread t2 = new Thread(() -> obj2.staticMethod(), "T2");

        t1.start();
        t2.start();
    }
}

// ---------------------------------------------------------------------------------------------------------------------- Deadlock
// https://chatgpt.com/share/67cbde8e-07ac-800e-986a-6bce7a3d90a8

class Resource {
    String name;

    Resource(String name) {
        this.name = name;
    }
}

class DeadlockExample implements Runnable {
    private final Resource resource1;
    private final Resource resource2;

    DeadlockExample(Resource resource1, Resource resource2) {
        this.resource1 = resource1;
        this.resource2 = resource2;
    }

    @Override
    public void run() {
        synchronized (resource1) {
            System.out.println(Thread.currentThread().getName() + " id : " + Thread.currentThread().getId() + " locked " + resource1.name);

            try { Thread.sleep(100); } catch (InterruptedException ex) {}

            synchronized (resource2) {
                System.out.println(Thread.currentThread().getName() + " locked " + resource2.name);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Resource resA = new Resource("Resource A");
        Resource resB = new Resource("Resource B");

        Thread t1 = new Thread(new DeadlockExample(resA, resB), "Thread-1");
        Thread t2 = new Thread(new DeadlockExample(resB, resA), "Thread-2");

        t1.start();
        t2.start();

        Thread.sleep(2000);

        System.out.println(t1.getState());
        System.out.println(t2.getState());

        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        long[] deadlockedThreads = threadMXBean.findDeadlockedThreads();
        if (deadlockedThreads != null) {
            System.out.println("Deadlock detected! " + Arrays.toString(deadlockedThreads));
        } else {
            System.out.println("No deadlocks detected.");
        }
    }
}

// ---------------------------------------------------------------------------------------------------------------------race conditions
// https://chatgpt.com/share/67cbde8e-07ac-800e-986a-6bce7a3d90a8
// multiple threads access and modify shared data concurrently, leading to inconsistent or incorrect results

class Counter {
    private int count = 0;

    public void increment() {
        count++;  // Not thread-safe!
    }

    public int getCount() {
        return count;
    }
}

class RaceConditionExample {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Final count: " + counter.getCount()); // Expected: 2000, but will likely be less
    }
}

// solution1
class Counter {
    private int count = 0;

    public synchronized void increment() {
        count++; // Now thread-safe!
    }

    public synchronized int getCount() {
        return count;
    }
}

// to be fast
class Counter {
    private final AtomicInteger count = new AtomicInteger(0);

    public void increment() {
        count.incrementAndGet();  // Thread-safe
    }

    public int getCount() {
        return count.get();
    }
}

// ---------------------------------------------------------------------------------------------------------------------- Executor framework
// https://chatgpt.com/share/67cbe722-b3b8-800e-82b9-740d6d00cdc1
// https://www.geeksforgeeks.org/what-is-java-executor-framework/

class ExecutorExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        Runnable task = () -> {
            System.out.println("Executing task in: " + Thread.currentThread().getName());
        };

        for (int i = 0; i < 5; i++) {
            executor.execute(task);
        }

        executor.shutdown();  // Properly shut down the executor
    }
}

class CallableExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Callable<Integer> task = () -> {
            return 10 * 2;
        };

        Future<Integer> future = executor.submit(task);

        try {
            System.out.println("Result: " + future.get()); // Blocks until result is available
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}

// Simplify thread management, better/optimal resource usage
// ---------------------------------------------------------------------------------------------------------------------
// https://www.codepedia.org/ama/how-to-make-parallel-calls-in-java-with-completablefuture-example

// ---------------------------------------------------------------------------------------------------------------------
// https://stackoverflow.com/questions/19744508/volatile-vs-atomic => visibility problem vs synchronization => more : https://www.youtube.com/watch?v=WH5UvQJizH0
// https://chatgpt.com/share/67cbf2de-f278-800e-99d7-373aebdfe17d

class SharedResource {
    volatile boolean running = true; // No volatile means t1 keeps on running - as its cached in t1

    void stop() {
        running = false;
    }
}

class NoVolatileExample {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();

        // Thread 1: Continuously runs until 'running' is false
        Thread t1 = new Thread(() -> {
            while (resource.running) {
                // Busy-wait (could optimize with Thread.sleep(1))
            }
            System.out.println("Thread stopped.");
        });

        // Start the thread
        t1.start();

        // Simulate some delay
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Thread 2: Stops the first thread
        resource.stop();
        System.out.println("Stop signal sent.");
    }
}

// ---------------------------------------------------------------------------------------------------------------------
// https://stackoverflow.com/questions/129329/optimistic-vs-pessimistic-locking
