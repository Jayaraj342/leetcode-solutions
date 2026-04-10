class Solution {
    public long minimumCost(String s, String t, int flipCost, int swapCost, int crossCost) {
        long res = 0;
        int cnt01 = 0, cnt10 = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0' && t.charAt(i) == '1') cnt01++;
            else if (s.charAt(i) == '1' && t.charAt(i) == '0') cnt10++;
        }

        // Pair opposite mismatches
        int p = Math.min(cnt01, cnt10);
        res += (long) p * Math.min(swapCost, 2 * flipCost);

        cnt01 -= p;
        cnt10 -= p;

        // Remaining same-type mismatches
        int remain = cnt01 + cnt10;
        int pairs = remain / 2;

        res += (long) pairs * Math.min(2 * flipCost, crossCost + swapCost);
        if (remain % 2 == 1) res += flipCost;

        return res;
    }
}
