// O(n)
class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        int[] path = new int[1001];
        for (int[] trip : trips) {
            path[trip[1]] += trip[0];
            path[trip[2]] -= trip[0];
        }

        int netPassengers = 0;
        for (int noPassengers : path) {
            netPassengers += noPassengers;
            if (netPassengers > capacity) {
                return false;
            }
        }

        return true;
    }
}

//O(n.logn) for sorting keys
class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] trip : trips) {
            map.put(trip[1], map.getOrDefault(trip[1], 0) + trip[0]);
            map.put(trip[2], map.getOrDefault(trip[2], 0) - trip[0]);
        }

        int netPassengers = 0;
        List<Integer> srcDstPointsSorted = new ArrayList<>(map.keySet());
        Collections.sort(srcDstPointsSorted);
        for (int point : srcDstPointsSorted) {
            netPassengers += map.get(point);
            if (netPassengers > capacity) {
                return false;
            }
        }

        return true;
    }
}