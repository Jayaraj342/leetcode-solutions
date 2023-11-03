class Print {
    int count = 1;
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