class MyThread implements Runnable {
    int wait;

    MyThread(int wait) {
        this.wait = wait;
    }

    @Override
    public void run() {
        Thread thread = Thread.currentThread();
        System.out.println("Thread running, name of the thread is:  " + thread.getName());
        try {
            Thread.sleep(wait * 1000L);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
        System.out.println("Thread ended, name of the thread is: " + thread.getName());

    }
}

class ThreadJoinDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new MyThread(2), "t1");
        Thread t2 = new Thread(new MyThread(2), "t2");
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        // main thread will wait for t1 and t2 to finish
        System.out.println("All the user threads have finished execution");
    }
}