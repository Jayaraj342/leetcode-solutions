// O(n^2)
class Solution {
    public int punishmentNumber(int n) {
        int res = 0;
        for (int i = 1; i <= n; i++) {
            int square = i * i;
            if (canBreakIntoSum(i, String.valueOf(square), 0, 0)) {
                res += square;
            }
        }

        return res;
    }

    private boolean canBreakIntoSum(int num, String square, int idx, int sum) {
        if (sum > num) {
            return false;
        }
        if (idx == square.length()) {
            return sum == num;
        }

        for (int i = idx + 1; i <= square.length(); i++) {
            int part = Integer.parseInt(square.substring(idx, i));
            if (canBreakIntoSum(num, square, i, sum + part)) {
                return true;
            }
        }

        return false;
    }
}