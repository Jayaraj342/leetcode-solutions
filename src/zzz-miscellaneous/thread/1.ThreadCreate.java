// java.lang.Thread.State enum

class Thread1WithThread extends Thread {
    @Override
    public void run() {
        try {
            Thread.sleep(3000);
            System.out.println(Thread.currentThread());
            System.out.println("Thread1 is running");
        } catch (Exception e) {
            System.out.println("Exception raised in thread1" + e);
        }
    }
}

class Thread2WithRunnable implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(2000);
            System.out.println(Thread.currentThread());
            System.out.println("Thread 2 is running");
        } catch (Exception e) {
            System.out.println("Exception raised in thread 2" + e);
        }
    }
}

class ThreadingDemo {
    public static void main(String[] args) {
        Thread t1 = new Thread1WithThread();
        t1.start();
        Thread t2 = new Thread(new Thread2WithRunnable());
        t2.start();
        Thread t3 = new Thread(() -> {
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread());
                System.out.println("Thread 3 is running");
            } catch (Exception e) {
                System.out.println("Exception raised in thread 2" + e);
            }
        });
        t3.start();
        System.out.println("Main thread!");
    }
}

// -----------------------------------------------------------------------------------Daemon thread : terminated if user threads finish executing

class DaemonThread extends Thread {
    @Override
    public void run() {
        try {
            System.out.println("DaemonThread is running");
            for(int i=0; i<10000; i++) {
                System.out.println(i);
            }
            System.out.println("DaemonThread ended");
        } catch (Exception e) {
            System.out.println("Exception raised in DaemonThread" + e);
        }
    }
}

class DaemonThreadingDemo {
    public static void main(String[] args) {
        Thread t1 = new DaemonThread();
        t1.setDaemon(true); // must be set before start()
        t1.start();
        System.out.println("main ended!"); // Daemon thread might not print till 9999
    }
}

// ---------------------------------------------------------------------------------- Callable
import java.util.concurrent.*;

class Solution {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        FactorialTask task = new FactorialTask(10);
        Future<Integer> future = executorService.submit(task);
        System.out.println(future.get());
        executorService.shutdown();
    }
}

class FactorialTask implements Callable<Integer> {
    int number;

    FactorialTask(int number) {
        this.number = number;
    }

    public Integer call()  {
        int fact = 1;
        for(int count = number; count > 1; count--) {
            fact = fact * count;
        }

        return fact;
    }
}

// example 2
class Task implements Callable<String> {
    private final int taskId;

    public Task(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public String call() throws Exception {
        System.out.println("Task " + taskId + " is running on " + Thread.currentThread().getName());
        Thread.sleep(2000); // Simulating work
        return "Task " + taskId + " completed";
    }
}

class FixedThreadPoolCallableExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Submit multiple Callable tasks and collect Future results
        Future<String> future1 = executor.submit(new Task(1));
        Future<String> future2 = executor.submit(new Task(2));
        Future<String> future3 = executor.submit(new Task(3));
        Future<String> future4 = executor.submit(new Task(4));
        Future<String> future5 = executor.submit(new Task(5));

        try {
            // Retrieving results from Future
            System.out.println(future1.get());
            System.out.println("------------------------------"); // blocks main thread until future1 is complete
            System.out.println(future2.get());
            System.out.println(future3.get());
            System.out.println(future4.get());
            System.out.println(future5.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        executor.shutdown();
    }
}

// --- with runnable

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Task implements Runnable {
    private final int taskId;

    public Task(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run() {
        System.out.println("Task " + taskId + " is running on " + Thread.currentThread().getName());
        try {
            Thread.sleep(2000); // Simulating work
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Task " + taskId + " is completed on " + Thread.currentThread().getName());
    }
}

public class FixedThreadPoolExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Submitting multiple tasks
        for (int i = 1; i <= 6; i++) {
            executor.submit(new Task(i));
        }

        executor.shutdown(); // Initiates an orderly shutdown (no new tasks accepted)
    }
}
