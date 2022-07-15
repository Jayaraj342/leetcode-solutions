class Thread1WithThread extends Thread {
    @Override
    public void run() {
        try {
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
            System.out.println(Thread.currentThread());
            System.out.println("Thread 2 is running");
        } catch (Exception e) {
            System.out.println("Exception raised in thread 2" + e);
        }
    }
}

class ThreadingDemo {
    public static void main(String[] args) {
        int n = 10;
        for (int i = 0; i < n; i++) {
            Thread t1 = new Thread1WithThread();
            t1.start();
            Thread t2 = new Thread(new Thread2WithRunnable());
            t2.start();
        }
    }
}