// O(n)
class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        int[] path = new int[1001];
        for (int[] trip : trips) {
            path[trip[1]] += trip[0];
            path[trip[2]] -= trip[0];
        }

        int capacityRequired = 0;
        for (int stop : path) {
            capacityRequired += stop;
            if (capacityRequired > capacity) {
                return false;
            }
        }

        return true;
    }
}

//O(n.logn) for sorting keys
class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        int capacityRequired = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] trip : trips) {
            map.put(trip[1], map.getOrDefault(trip[1], 0) + trip[0]);
            map.put(trip[2], map.getOrDefault(trip[2], 0) - trip[0]);
        }

        int maxCapacity = 0;
        List<Integer> pointsSorted = new ArrayList<>(map.keySet());
        Collections.sort(pointsSorted);
        for (int point : pointsSorted) {
            capacityRequired += map.get(point);
            if (capacityRequired > capacity) {
                return false;
            }
        }

        return true;
    }
}