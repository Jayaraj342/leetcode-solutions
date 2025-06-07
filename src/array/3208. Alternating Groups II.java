class Solution {
    public int numberOfAlternatingGroups(int[] colors, int k) {
        int n = colors.length, cnt = 0;
        int left = 0;
        for (int right = 1; right < n + k - 1; right++) {
            if (colors[right % n] == colors[(right - 1) % n]) {
                left = right;
            }
            if (right - left + 1 > k) {
                left++;
            }
            if (right - left + 1 == k) {
                cnt++;
            }
        }

        return cnt;
    }
}

class Solution {
    public int numberOfAlternatingGroups(int[] colors, int k) {
        int n = colors.length;

        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int res = 0;
        for (int i = 1; i < n + k - 1; i++) {
            int idx = i % n, idxMinusOne = (i - 1 + n) % n;
            if (colors[idx] != colors[idxMinusOne]) {
                dp[idx] = dp[idxMinusOne] + 1;
            }

            if (dp[idx] >= k) {
                res++;
            }
        }

        return res;
    }
}

class Solution {
    int cnt;

    public int numberOfAlternatingGroups(int[] colors, int k) {
        cnt = 0;
        int i = 0;
        while (i < colors.length) {
            i = noAlternatingGroupsFrom(colors, k, i);
        }

        return cnt;
    }

    // returns index to start next iteration (if idx returned is >= n : end the invocation)
    private int noAlternatingGroupsFrom(int[] colors, int k, int i) {
        int n = colors.length;
        int j = i + 1;
        while (j - i + 1 < k) {
            if (colors[j % n] != colors[(j - 1) % n]) {
                j++;
            } else {
                return j;
            }
        }

        // i -> start of first alternating group
        // j -> end of first alternating group
        // k == 3 => colors[i] == colors[j]
        if (k % 2 == 0) {
            while (i < n && colors[i] != colors[j % n]) {
                cnt++;
                i++;
                j++;
            }
        } else {
            while (i < n && colors[i] == colors[j % n]) {
                cnt++;
                i++;
                j++;
            }
        }

        return i + 1;
    }

    public static void main(String[] args) {
        new Solution().numberOfAlternatingGroups(new int[]{0, 1, 0, 1, 0}, 3);
    }
}