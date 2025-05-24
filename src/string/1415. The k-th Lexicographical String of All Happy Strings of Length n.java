class Solution {
    public String getHappyString(int n, int k) {
        int totalHappyStrings = 3 * (int) Math.pow(2, n - 1);
        StringBuilder res = new StringBuilder();
        String choices = "abc";

        int lo = 1, hi = totalHappyStrings;

        for (int i = 1; i <= n; i++) {
            int partitionSize = (hi - lo + 1) / choices.length();
            int curr = lo; // start of current partition
            for (char c : choices.toCharArray()) {
                if (k >= curr && k <= curr + partitionSize - 1) {
                    res.append(c);

                    lo = curr;
                    hi = curr + partitionSize - 1;

                    choices = "abc".replace(c + "", "");
                    break;
                }
                curr += partitionSize;
            }
        }

        return res.toString();
    }
}