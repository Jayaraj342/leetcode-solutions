// n, n (SC can be reduced)
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

// n, 1
class Solution {
    public int[] minOperations(String boxes) {
        int n = boxes.length();
        int[] answer = new int[n];

        int ballsToLeft = 0, movesToLeft = 0;
        int ballsToRight = 0, movesToRight = 0;

        // Single pass: calculate moves from both left and right
        for (int i = 0; i < n; i++) {
            // Left pass
            answer[i] += movesToLeft;
            ballsToLeft += Character.getNumericValue(boxes.charAt(i));
            movesToLeft += ballsToLeft;

            // Right pass
            int j = n - 1 - i;
            answer[j] += movesToRight;
            ballsToRight += Character.getNumericValue(boxes.charAt(j));
            movesToRight += ballsToRight;
        }

        return answer;
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