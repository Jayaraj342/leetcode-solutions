class Solution {
    public long putMarbles(int[] weights, int k) {
        // [1,3,5,1] => 1, 1 is common - cancelled out while diff
        // find (k - 1) pairs with max val - (k - 1) pairs with min val
        int n = weights.length;
        List<Integer> pairs = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            pairs.add(weights[i] + weights[i + 1]);
        }
        Collections.sort(pairs);

        long min = 0, max = 0;
        for (int i = 0; i < k - 1; i++) {
            min += pairs.get(i);
            max += pairs.get(n - 1 - i - 1);
        }

        return max - min;
    }
}