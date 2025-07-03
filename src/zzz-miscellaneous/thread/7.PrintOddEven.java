class TwoThreads {
    private volatile int cnt = 1;
    private final Object object = new Object();

    public static void main(String[] args) {
        TwoThreads twoThreads = new TwoThreads();
        Thread t1 = new Thread(twoThreads.new Printer(true), "EvenThread");
        Thread t2 = new Thread(twoThreads.new Printer(false), "OddThread");
        t1.start();
        t2.start();
    }

    class Printer implements Runnable {

        private final boolean evenThread;

        Printer(boolean evenThread) {
            this.evenThread = evenThread;
        }

        @Override
        public void run() {
            while (cnt <= 100) {
                synchronized (object) {
                    if (evenThread != (cnt % 2 == 0)) {
                        try {
                            object.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        System.out.println(Thread.currentThread().getName() + " " + cnt);
                        cnt++;
                        object.notify();
                    }
                }
            }
        }
    }
}

class TwoThreads {
    private volatile int cnt = 1;

    public static void main(String[] args) {
        TwoThreads twoThreads = new TwoThreads();
        Thread t1 = new Thread(twoThreads.new Printer(true), "EvenThread");
        Thread t2 = new Thread(twoThreads.new Printer(false), "OddThread");
        t1.start();
        t2.start();
    }

    class Printer implements Runnable {

        private final boolean evenThread;

        Printer(boolean evenThread) {
            this.evenThread = evenThread;
        }

        @Override
        public void run() {
            while (cnt <= 100) {
                synchronized (TwoThreads.class) {
                    if (evenThread != (cnt % 2 == 0)) {
                        try {
                            TwoThreads.class.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        System.out.println(Thread.currentThread().getName() + " " + cnt);
                        cnt++;
                        TwoThreads.class.notify();
                    }
                }
            }
        }
    }
}

// --------------------------------------------------------------------------------------------------------------------

class Print {
    int count = 1; // volatile is not required as its ot a global variable from different class that is shared
    int max = 20;

    public synchronized void printOdd() throws InterruptedException {
        while (count < max) {
            if (count % 2 == 0) {
                wait();
            }
            System.out.println(Thread.currentThread() + " :" + count++);
            notify();
        }
    }

    public synchronized void printEven() throws InterruptedException {
        while (count < max) {
            if (count % 2 == 1) {
                wait();
            }
            System.out.println(Thread.currentThread() + " :" + count++);
            notify();
        }
    }
}

class ThreadingDemo {
    public static void main(String[] args) {
        Print print = new Print();
        Thread t1 = new Thread(() -> {
            try {
                print.printEven();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        t1.start();
        Thread t2 = new Thread(() -> {
            try {
                print.printOdd();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        t2.start();
    }
}