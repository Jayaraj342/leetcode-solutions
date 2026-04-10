class Solution {
    public long numGoodSubarrays(int[] A, int k) {
        Map<Integer, Long> cnt = new HashMap<>();
        cnt.put(0, 1L);

        int pre = 0, n = A.length;
        long res = 0;
        // Count subarrays where sum % k == 0
        for (int num : A) {
            pre = (pre + num) % k;
            res += cnt.getOrDefault(pre, 0L);
            cnt.put(pre, cnt.getOrDefault(pre, 0L) + 1L);
        }

        // Remove invalid subarrays made of identical elements (deDuplication)
        for (int i = 0; i < n; ) {
            int j = i;
            while (j < n && A[j] == A[i]) {
                j++;
            }
            int len = j - i;// length - 1
            for (int end = 1; end < len; ++end)
                if (((long) end * A[i]) % k == 0) {
                    res -= (len - end);
                }
            i = j;
        }

        return res;
    }
}