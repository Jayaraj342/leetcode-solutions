class MathUtils {

    // Lock locker = new ReentrantLock();

    //	synchronized
    void getMultiples(int n) {
        System.out.println("Started :" + Thread.currentThread());;
        synchronized (this) {
            try {
                // locker.lock();
                for (int i = 1; i <= 10; i++) {
                    System.out.println(n * i);
                    try {
                        Thread.sleep(500);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            } finally {
                // locker.unlock();
            }
        }
    }
}

class Thread1 extends Thread {
    MathUtils mu;

    public Thread1(MathUtils mu) {
        this.mu = mu;
    }

    public void run() {
        try {
            mu.getMultiples(2);
        } catch (Exception e) {
            System.out.println("Exception raised" + e);
        }
    }
}

class Thread2 implements Runnable {
    MathUtils mu;

    public Thread2(MathUtils mu) {
        this.mu = mu;
    }

    public void run() {
        try {
            mu.getMultiples(3);
        } catch (Exception e) {
            System.out.println("Exception raised" + e);
        }
    }
}

class SynchronizationDemo {
    public static void main(String[] args) {
        MathUtils mu = new MathUtils();
        Thread t1 = new Thread1(mu);
        t1.setName("t1");
        Thread t2 = new Thread(new Thread2(mu), "t2");
        t1.start();
        t2.start();
    }
}