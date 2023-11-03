class WaitingThread implements Runnable {
    private final Integer value;

    public WaitingThread(Integer value) {
        this.value = value;
    }

    @Override
    public void run() {
        synchronized (value) {
            System.out.println("waiting to get the lock");
            try {
                value.wait();
                System.out.println("waiting thread received notification by notifier thread");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class NotifierThread implements Runnable {
    private final Integer value;

    public NotifierThread(Integer value) {
        this.value = value;
    }

    @Override
    public void run() {
        synchronized (value) {
            try {
                Thread.sleep(1000);
                System.out.println("Now Notifier Thread begins notifying the waiting thread");
                value.notify();
                System.out.println("Notifier Thread finished Notifying the waiting threads.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

class WaitNotifyDemo {
    public static void main(String[] args) {
        Integer value = 10;

        Thread waitingThread = new Thread(new WaitingThread(value));
        waitingThread.start();

        Thread notifierThread = new Thread(new NotifierThread(value));
        notifierThread.start();
    }
}

// https://stackoverflow.com/questions/2779484/why-must-wait-always-be-in-synchronized-block