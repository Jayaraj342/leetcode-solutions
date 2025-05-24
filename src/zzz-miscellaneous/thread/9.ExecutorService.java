import java.util.*;
import java.util.concurrent.*;

class Solution {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(Thread.currentThread());

        Callable<String> callable1 = () -> fnThatTakes3Secs();
        // same call using method reference
        Callable<String> callable2 = Solution::fnThatTakes3Secs;

        Runnable runnable = () -> fnThatTakes3Secs();

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<String> f1 = executorService.submit(callable1);
        Future<String> f2 = executorService.submit(callable2);

        Future<String> f3 = executorService.submit(callable1);

        // if there is a void method
        Future<?> f4 = executorService.submit(runnable);

        System.out.println(f1.get());
        System.out.println(f2.get());
        // any of 2 threads that completed will take below 2 tasks
        System.out.println(f3.get());
        System.out.println(f4.get() + " " + new Date(System.currentTimeMillis()));

        executorService.shutdown();
    }

    private static String fnThatTakes3Secs() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Thread.currentThread() + " " + new Date(System.currentTimeMillis());
    }
}

// --------------------------------------------------------------------------------- list of tasks
class SampleThread implements Callable<String> {

    private final String name;

    public SampleThread(String name) {
        this.name = name;
    }

    @Override
    public String call() throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        Thread.sleep(1000);
        return name + name;
    }
}

class ThreadPriorityDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Future<String>> futures = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            SampleThread sampleThread = new SampleThread("t" + i);
            Future<String> future = executorService.submit(sampleThread);
            futures.add(future);
        }

        List<String> res = new ArrayList<>();
        for (Future<String> future : futures) {
            res.add(future.get());
        }

        System.out.println("Result : " + res);
        executorService.shutdown();
    }
}