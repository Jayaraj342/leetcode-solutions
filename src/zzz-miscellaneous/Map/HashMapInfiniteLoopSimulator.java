class HashMapInfiniteLoopSimulator {

    private static final int NB_THREADS = 3;
    private static final int NB_TEST_ITERATIONS = 10;

    private static Map<String, Integer> assignedMapForTest = null;
    private static Map<String, Integer> nonThreadSafeMap = null;
    private static Map<String, Integer> threadSafeMap1 = null;
    private static Map<String, Integer> threadSafeMap2 = null;
    private static Map<String, Integer> threadSafeMap3 = null;

    public static void main(String[] args) {
        for (int i = 0; i < NB_TEST_ITERATIONS; i++) {

            // Plain old HashMap (since JDK 1.2)
            nonThreadSafeMap = new HashMap<>(2);

            // Plain old Hashtable (since JDK 1.0)
            threadSafeMap1 = new Hashtable<>(2);

            // Fully synchronized HashMap
            threadSafeMap2 = new HashMap<>(2);
            threadSafeMap2 = Collections.synchronizedMap(threadSafeMap2);

            // ConcurrentHashMap (since JDK 1.5)
            threadSafeMap3 = new ConcurrentHashMap<>(2); // ConcurrentHashMap

            /*** Assign map at your convenience ****/
            assignedMapForTest = nonThreadSafeMap;

            long timeBefore = System.currentTimeMillis();
            long timeAfter = 0;
            Float totalProcessingTime = null;

            ExecutorService executor = Executors.newFixedThreadPool(NB_THREADS);
            for (int j = 0; j < NB_THREADS; j++) {
                /** Assign the Map at your convenience **/
                Runnable worker = new WorkerThread(assignedMapForTest);
                executor.execute(worker);
            }

            // This will make the executor accept no new threads
            // and finish all existing threads in the queue
            executor.shutdown();
            // Wait until all threads are finish
            while (!executor.isTerminated()) {
            }

            timeAfter = System.currentTimeMillis();
            totalProcessingTime = (float) (timeAfter - timeBefore) / (float) 1000;

            System.out.println("All threads completed in " + totalProcessingTime + " seconds");
        }
    }
}

class WorkerThread implements Runnable {
    private Map<String, Integer> map = null;

    public WorkerThread(Map<String, Integer> assignedMap) {
        this.map = assignedMap;
    }

    @Override
    public void run() {
        for (int i = 0; i < 500000; i++) {
            // Return 2 integers between 1-1000000 inclusive
            Integer newInteger1 = (int) Math.ceil(Math.random() * 1000000);
            Integer newInteger2 = (int) Math.ceil(Math.random() * 1000000);

            // 1. Attempt to retrieve a random Integer element
            Integer retrievedInteger = map.get(String.valueOf(newInteger1));

            // 2. Attempt to insert a random Integer element
            map.put(String.valueOf(newInteger2), newInteger2);
        }
    }
}
