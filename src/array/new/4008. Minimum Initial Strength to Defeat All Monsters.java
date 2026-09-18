// https://leetcode.com/problems/minimum-initial-strength-to-defeat-all-monsters
// https://github.com/Jayaraj342/leetcode-solutions/blob/master/src/array/new/4008. Minimum Initial Strength to Defeat All Monsters.java

// n, n
class Solution {
    public long minInitialStrength(int[] monsters, int[][] boosts) {
        int n = monsters.length;

        long[] netBoosts = new long[n];
        for (int[] range : boosts) {
            int lo = range[0], hi = range[1];
            int val = range[2];

            netBoosts[lo] += val;
            if (hi + 1 < n) netBoosts[hi + 1] -= val;
        }
        for (int i = 1; i < n; i++) {
            netBoosts[i] += netBoosts[i - 1];
        }

        int i = n - 1;
        long minStrength = 0;
        while (i >= 0) {
            if (netBoosts[i] < monsters[i]) {
                minStrength = monsters[i] - netBoosts[i];
                i--;
                break;
            }
            i--;
        }
        while (i >= 0) {
            minStrength += monsters[i];
            i--;
        }

        return minStrength;
    }
}

// same n, n
class Solution {
    public long minInitialStrength(int[] monsters, int[][] boosts) {
        int n = monsters.length;
        long[] net = new long[n];
        for (int[] boost : boosts) {
            int lo = boost[0], hi = boost[1], val = boost[2];
            net[hi] += val;
            if (lo > 0) {
                net[lo - 1] -= val;
            }
        }

        long res = 0, bonus = 0;
        for (int i = n - 1; i >= 0; i--) {
            bonus += net[i];
            if (res > 0) {
                res += monsters[i];
            } else {
                res = Math.max(0L, monsters[i] - bonus);
            }
        }

        return res;
    }
}