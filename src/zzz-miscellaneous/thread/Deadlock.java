// Thread.State
// https://www.geeksforgeeks.org/lifecycle-and-states-of-a-thread-in-java/
// https://stackoverflow.com/questions/15680422/difference-between-wait-and-blocked-thread-states
// https://www.geeksforgeeks.org/deadlock-in-java-multithreading/
// https://www.baeldung.com/cs/race-conditions
// https://www.geeksforgeeks.org/what-is-java-executor-framework/
// https://www.codepedia.org/ama/how-to-make-parallel-calls-in-java-with-completablefuture-example
// https://stackoverflow.com/questions/19744508/volatile-vs-atomic
class Example {
    public static void main(String[] args) {
        final String r1 = "eureka";
        final String r2 = "java";

        Thread t1 = new Thread(() -> {
            synchronized (r2) {
                System.out.println("Thread 1: Locked r1");
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                synchronized (r1) {
                    System.out.println("Thread 1: Locked r2");
                }
            }
        });
        Thread t2 = new Thread(() -> {
            synchronized (r1) {
                System.out.println("Thread 2: Locked r1");
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                synchronized (r2) {
                    System.out.println("Thread 2: Locked r2");
                }
            }
        });

        t1.start();
        t2.start();
    }
}