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
            Map<Long, Long> sumByCount = map.computeIfAbsent(num, k -> new HashMap<>());
            sumByCount.put(pre, sumByCount.getOrDefault(pre, 0L) + 1L);

            if (i > 0 && num == 0 && capacity[i - 1] == 0) res--;
        }

        return res;
    }
}