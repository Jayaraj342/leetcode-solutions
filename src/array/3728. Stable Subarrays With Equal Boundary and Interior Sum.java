class Solution {
    public long countStableSubarrays(int[] capacity) {
        long n = capacity.length, res = 0, pre = 0;
        Map<Integer, Map<Long, Long>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int num = capacity[i];

            if (map.containsKey(num)) {
                Map<Long, Long> sumByCount = map.get(num);
                Long cnt = sumByCount.get(pre - num);
                if (cnt != null) res += cnt;
            }

            pre += num;
            Map<Long, Long> sumByCount = map.computeIfAbsent(num, k -> new HashMap<>());// Store num as key, not prefix
            sumByCount.put(pre, sumByCount.getOrDefault(pre, 0L) + 1L);// For a given num, store prefix as key after adding the num

            if (i > 0 && num == 0 && capacity[i - 1] == 0) res--;// [-4,4,0,0,0,-8,-4] => For a pair of continous zeros, 1 extra cnt will be added - not cnt extras..
        }

        return res;
    }
}