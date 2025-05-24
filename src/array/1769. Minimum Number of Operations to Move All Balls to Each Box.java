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