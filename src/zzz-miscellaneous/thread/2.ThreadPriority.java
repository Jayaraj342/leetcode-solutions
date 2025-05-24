class SampleThread implements Runnable {

    private final String name;

    public SampleThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        try {
            System.out.println(name + " Before sleeping");
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
        System.out.println("Inside SampleThread run method " + name);

    }
}

class ThreadPriorityDemo {

    public static void main(String[] args) {
        Thread t1 = new Thread(new SampleThread("t1"));
        Thread t2 = new Thread(new SampleThread("t2"));
        Thread t3 = new Thread(new SampleThread("t3"));

        System.out.println("t1 priority : " + t1.getPriority());
        System.out.println("t2 priority : " + t2.getPriority());
        System.out.println("t3 priority : " + t3.getPriority());
        System.out.println("----------------------------------");

        t1.setPriority(1);
        t2.setPriority(6);
        t3.setPriority(9);
        System.out.println("t1 new priority : " + t1.getPriority());
        System.out.println("t2 new priority : " + t2.getPriority());
        System.out.println("t3 new priority : " + t3.getPriority());

        //won't change anything
        t1.setDaemon(true);
        System.out.println("new t1 priority after making it daemon : " + t1.getPriority());

        // order will be random - as 3 won't make a difference (parallelism - many cpu's)
        t1.start();
        t2.start();
        t3.start();
        System.out.println("Currently Running Thread : " + Thread.currentThread().getName());
        System.out.println("Main Thread Priority : " + Thread.currentThread().getPriority());
    }
}

// executor service won't help is setting thread priority - this won't work too
class ThreadPriorityDemo {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        for (int i = 1; i <= 30; i++) {
            SampleThread sampleThread = new SampleThread("t" + i);
            Thread thread = new Thread(sampleThread);
            thread.setPriority(i % 10 + 1);
            executorService.execute(thread);
        }

        System.out.println("Currently Running Thread : " + Thread.currentThread().getName());
        System.out.println("Main Thread Priority : " + Thread.currentThread().getPriority());
    }
}