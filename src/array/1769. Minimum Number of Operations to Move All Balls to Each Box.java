class Solution {
    public int[] minOperations(String boxes) {
        int n = boxes.length();
        int[] dpRightToLeft = new int[n], dpLeftToRight = new int[n];
        int suffix = 0, prefix = 0;// no. balls to right & left
        for (int i = n - 2; i >= 0; i--) {
            suffix += (boxes.charAt(i + 1) == '1' ? 1 : 0);
            dpRightToLeft[i] = dpRightToLeft[i + 1] + suffix;
        }

        for (int i = 1; i < n; i++) {
            prefix += (boxes.charAt(i - 1) == '1' ? 1 : 0);
            dpLeftToRight[i] = dpLeftToRight[i - 1] + prefix;
        }

        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = dpRightToLeft[i] + dpLeftToRight[i];
        }

        return res;
    }

    public static void main(String[] args) {
        new Solution().minOperations("001011");
    }
}

class Solution {
    public int[] minOperations(String boxes) {
        int n = boxes.length();

        int[] temp1 = new int[n];
        int balls = 0, moves = 0;
        for (int i = 0; i < n; i++) {
            temp1[i] = balls + moves;
            balls += boxes.charAt(i) == '1' ? 1 : 0;
            moves = temp1[i];
        }

        int[] temp2 = new int[n];
        balls = 0;
        moves = 0;
        for (int i = n - 1; i >= 0; i--) {
            temp2[i] = balls + moves;
            balls += boxes.charAt(i) == '1' ? 1 : 0;
            moves = temp2[i];
        }

        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = temp1[i] + temp2[i];
        }

        return res;
    }
}