class Solution {
    public long repairCars(int[] ranks, int cars) {
        long lo = 1, hi = (long) ranks[0] * cars * cars;// time range required to repair all cars
        for (int rank : ranks) {
            hi = Math.min(hi, (long) rank * cars * cars);
        }

        long res = -1;
        while (lo <= hi) {
            long mid = lo + (hi - lo) / 2;
            if (canRepair(ranks, cars, mid)) {
                res = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        
        return res;
    }

    private boolean canRepair(int[] ranks, int carsToBeRepaired, long maxTime) {
        long totalCars = 0;
        for (int rank : ranks) {
            long carsCurrMechanicCanRepairWithMaxTime = (long) Math.sqrt(maxTime / rank);
            totalCars += carsCurrMechanicCanRepairWithMaxTime;
            
            if (totalCars >= carsToBeRepaired) return true;
        }
        
        return false;
    }
}