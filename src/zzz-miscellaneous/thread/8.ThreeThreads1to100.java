class ThreeThreads {

    private volatile int count = 1;
    private volatile int threadIdToRun = 1;
    private final Object object = new Object();

    public static void main(String[] args) {

        ThreeThreads threeThreads = new ThreeThreads();
        Thread t1 = new Thread(threeThreads.new Printer(1));
        Thread t2 = new Thread(threeThreads.new Printer(2));
        Thread t3 = new Thread(threeThreads.new Printer(3));

        t1.start();
        t2.start();
        t3.start();
    }

    class Printer implements Runnable {

        private final int threadId;

        public Printer(int threadId) {
            super();
            this.threadId = threadId;
        }

        @Override
        public void run() {
            while (count <= 100) {
                synchronized (object) {
                    if (threadId != threadIdToRun) {
                        try {
                            System.out.println("Thread " + threadId + " is going to waiting state");
                            object.wait();// when 1 thread is in waiting state, it releases monitor.
                            System.out.println("Thread " + threadId + " is notified");
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        System.out.println("Thread " + threadId + " printed " + count);
                        count += 1;

                        threadIdToRun = (threadId % 3) + 1;
                        object.notifyAll();
                    }
                }
            }
        }
    }
}