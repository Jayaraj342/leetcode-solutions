class Solution {
    public int bestTeamScore(int[] scores, int[] ages) {
        int n = scores.length;
        Pair[] pairs = new Pair[n];
        for (int i = 0; i < n; i++) {
            pairs[i] = new Pair(scores[i], ages[i]);
        }
        Arrays.sort(pairs, (a, b) -> a.score != b.score ? a.score - b.score : a.age - b.age);

        int[] dp = new int[n];
        int max = 0;
        for (int i = n - 1; i >= 0; i--) {
            int currMax = 0;
            for (int j = i + 1; j < n; j++) {
                if (pairs[j].score == pairs[i].score || pairs[j].age >= pairs[i].age) {
                    currMax = Math.max(currMax, dp[j]);
                }
            }
            dp[i] = pairs[i].score + currMax;
            max = Math.max(max, dp[i]);
        }

        return max;
    }

    static class Pair {
        int score, age;

        Pair(int score, int age) {
            this.score = score;
            this.age = age;
        }
    }
}