class Solution {
    public boolean canConstruct(String s, int k) {
        int n = s.length();
        if (n < k) {
            return false;
        }

        Map<Character, Integer> freq = new HashMap<>();
        for (char c : s.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        int odds = 0;
        for (int val : freq.values()) {
            odds += val % 2;
        }

        return odds <= k;
    }
}