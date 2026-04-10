class ExamTracker {

    private final List<Long> times = new ArrayList<>();
    private final List<Long> prefixSum = new ArrayList<>();

    public ExamTracker() {
        times.add(0L);
        prefixSum.add(0L);
    }

    public void record(long time, long score) {
        times.add(time);
        prefixSum.add(prefixSum.get(prefixSum.size() - 1) + score);
    }

    public long totalScore(long startTime, long endTime) {
        int startIdx = Collections.binarySearch(times, startTime);
        if (startIdx < 0) startIdx = -startIdx - 1;

        int endIdx = Collections.binarySearch(times, endTime);
        if (endIdx < 0) endIdx = -endIdx - 2; // if its inserted - it'll give next index, so reduce 1

        long sumEnd = prefixSum.get(endIdx);
        long sumStart = prefixSum.get(startIdx - 1);// reduce 1 all the time

        return sumEnd - sumStart;
    }
}