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

// -----------------------------------------------------------------------------------Daemon thread

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
        t1.setDaemon(true);
        t1.start();
        System.out.println("main ended!");
    }
}