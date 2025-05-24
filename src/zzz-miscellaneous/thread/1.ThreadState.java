//NEW,
//RUNNABLE,
//BLOCKED,
//WAITING,
//TIMED_WAITING,
//TERMINATED

// -------------------------------------------------------------------------------------------------
//A thread is in the Blocked state when it is waiting to acquire a monitor lock (e.g., trying to enter a synchronized block or method but another thread already holds the lock).
//It cannot proceed until the lock is released by the other thread

class SharedResource {
    synchronized void access() {
        System.out.println(Thread.currentThread().getName() + " is accessing the resource...");
        try {
            Thread.sleep(3000); // Simulating work
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class BlockedExample {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();

        Runnable task = () -> {
            synchronized (resource) {// optional - required if access method is not synchronized
                resource.access();
            }
        };

        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");

        t1.start();
        t2.start();

        try {
            Thread.sleep(500); // Ensure Thread-1 locks first
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("State of " + t2.getName() + ": " + t2.getState()); // BLOCKED
    }
}

// ---------------------------------------------------------------------------------------------------------------------

//A thread is in the Waiting state when it voluntarily waits for another thread to signal it to continue.
//It remains in waiting indefinitely until another thread notifies it using notify() or notifyAll()

class WaitingThread extends Thread {
    public void run() {
        synchronized (this) {
            try {
                System.out.println(Thread.currentThread().getName() + " is waiting...");
                wait(); // Releases lock and enters WAITING state
                System.out.println(Thread.currentThread().getName() + " resumed!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class WaitingExample {
    public static void main(String[] args) throws InterruptedException {
        WaitingThread thread = new WaitingThread();
        thread.start();

        Thread.sleep(2000); // Give time for the thread to enter waiting state

        System.out.println("State of " + thread.getName() + ": " + thread.getState()); // WAITING

        synchronized (thread) {
            thread.notify(); // Notify the waiting thread to continue
        }
    }
}