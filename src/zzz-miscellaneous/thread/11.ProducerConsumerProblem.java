// https://chatgpt.com/share/67cd4399-50dc-800e-9816-94ccc7d51cf6

// using wait() & notify()
class SharedBuffer {
    private final LinkedList<Integer> buffer = new LinkedList<>();
    private final int capacity = 5;

    public synchronized void produce(int value) throws InterruptedException {
        while (buffer.size() == capacity) {
            System.out.println("Buffer full. Producer waiting...");
            wait(); // Wait if the buffer is full
        }

        buffer.add(value);
        System.out.println("Produced: " + value);
        notify(); // Notify consumers that data is available
    }

    public synchronized int consume() throws InterruptedException {
        while (buffer.isEmpty()) {
            System.out.println("Buffer empty. Consumer waiting...");
            wait(); // Wait if the buffer is empty
        }

        int value = buffer.removeFirst();
        System.out.println("Consumed: " + value);
        notify(); // Notify producers that space is available
        return value;
    }
}

class Producer extends Thread {
    private final SharedBuffer buffer;

    public Producer(SharedBuffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        int value = 0;
        while (true) {
            try {
                buffer.produce(value++);
                Thread.sleep(1000); // Simulate time to produce
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer extends Thread {
    private final SharedBuffer buffer;

    public Consumer(SharedBuffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        while (true) {
            try {
                buffer.consume();
                Thread.sleep(1500); // Simulate time to consume
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class ProducerConsumerWaitNotify {
    public static void main(String[] args) {
        SharedBuffer buffer = new SharedBuffer();
        new Producer(buffer).start();
        new Consumer(buffer).start();
    }
}

// Using BlockingQueue
class ProducerBQ extends Thread {
    private final BlockingQueue<Integer> queue;

    public ProducerBQ(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    public void run() {
        int value = 0;
        while (true) {
            try {
                queue.put(value); // Blocks if queue is full
                System.out.println("Produced: " + value);
                value++;
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class ConsumerBQ extends Thread {
    private final BlockingQueue<Integer> queue;

    public ConsumerBQ(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    public void run() {
        while (true) {
            try {
                int value = queue.take(); // Blocks if queue is empty
                System.out.println("Consumed: " + value);
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class ProducerConsumerBlockingQueue {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);
        new ProducerBQ(queue).start();
        new ConsumerBQ(queue).start();
    }
}