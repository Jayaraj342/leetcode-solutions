public class Solution {
    public int numTilePossibilities(String tiles) {
        HashMap<Character, Integer> counter = new HashMap<>();
        for (char ch : tiles.toCharArray()) {
            counter.put(ch, counter.getOrDefault(ch, 0) + 1);
        }

        return backtrack(counter);
    }

    private int backtrack(HashMap<Character, Integer> counter) {
        int count = 0;
        for (char ch : counter.keySet()) {
            if (counter.get(ch) > 0) {
                count++;  // Count this sequence
                counter.put(ch, counter.get(ch) - 1); // Use character
                count += backtrack(counter); // Recur for remaining
                counter.put(ch, counter.get(ch) + 1); // Backtrack
            }
        }

        return count;
    }
}