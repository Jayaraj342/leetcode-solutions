// https://chatgpt.com/share/67cd4399-50dc-800e-9816-94ccc7d51cf6

// https://leetcode.com/tag/concurrency/
// https://drive.google.com/drive/folders/1XM4WE9Tva067ZUZYx4CCX_jmTBRB2Uvb

class SemaphoreExample {
    public static void main(String[] args) {
        // Creating a semaphore with 2 permits
        Semaphore semaphore = new Semaphore(2);

        // Creating multiple worker threads
        for (int i = 1; i <= 5; i++) {
            new Worker(semaphore, "Worker-" + i).start();
        }

        System.out.println("Main thread scheduled and done! ------------------------------------------------------------");
    }
}

class Worker extends Thread {
    private Semaphore semaphore;

    public Worker(Semaphore semaphore, String name) {
        super(name);
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            System.out.println(getName() + " is waiting for a permit");
            semaphore.acquire();  // Acquiring a permit
            System.out.println(getName() + " got a permit and is working...");
            Thread.sleep(5_000); // Simulating work
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(getName() + " has finished work and released a permit _-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_");
            semaphore.release();  // Releasing a permit
        }
    }
}
