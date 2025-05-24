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

// calling wait() on an object in Java (i.e., object.wait()) requires that the calling thread already holds the monitor lock on that object.
// This means that the thread must have synchronized on the object before calling wait()/notify(), or else it will throw an IllegalMonitorStateException

// --------------------------------------------------------------------------------------------------------------------- Better example
//Suppose we have an ArrayList of size 3. The first three threads should each take one value and remove it from the list.
//The fourth thread should then sum these three values. This scenario effectively demonstrates the use of wait() and notify() for thread coordination

class SharedList {
    private final List<Integer> list = new ArrayList<>();
    private final List<Integer> collectedValues = new ArrayList<>();

    public SharedList() {
        // Initialize the list with 3 values
        list.add(10);
        list.add(20);
        list.add(30);
    }

    public synchronized void takeValue() throws InterruptedException {
        Thread.sleep(2000);

        // Take and remove the first value from the list
        int value = list.remove(0);
        collectedValues.add(value);
        System.out.println(Thread.currentThread().getName() + " took value: " + value);

        notify();
    }

    public synchronized void sumValues() {
        while (collectedValues.size() < 3) {
            try {
                wait(); // Wait until all three values are collected
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Sum the collected values
        int sum = collectedValues.stream().mapToInt(Integer::intValue).sum();
        System.out.println(Thread.currentThread().getName() + " calculated sum: " + sum);
    }
}

class WaitNotifyExample {
    public static void main(String[] args) {
        SharedList sharedList = new SharedList();

        // Create and start 3 threads to take values
        for (int i = 1; i <= 3; i++) {
            new Thread(() -> {
                try {
                    sharedList.takeValue();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }, "Thread-" + i).start();
        }

        // Create and start the 4th thread to sum the values
        new Thread(sharedList::sumValues, "Sum-Thread").start();
    }
}

// https://stackoverflow.com/questions/2779484/why-must-wait-always-be-in-synchronized-block