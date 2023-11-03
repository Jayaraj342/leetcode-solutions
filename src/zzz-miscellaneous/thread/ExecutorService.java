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
