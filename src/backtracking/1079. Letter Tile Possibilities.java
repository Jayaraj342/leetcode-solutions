class Solution {
    public int numTilePossibilities(String tiles) {
        int[] count = new int[26];
        for (char c : tiles.toCharArray()) count[c - 'A']++;

        return dfs(count);
    }

    // we never handle duplicates because we start only once for a given char - even if they are repeated many times
    int dfs(int[] arr) {
        int sum = 0;
        for (int i = 0; i < 26; i++) {
            if (arr[i] == 0) continue;
            sum++; // Count this sequence

            arr[i]--; // Count this sequence
            sum += dfs(arr); // Count this sequence
            arr[i]++; // Backtrack
        }

        return sum;
    }
}

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
                counter.put(ch, counter.get(ch) - 1); // Count this sequence
                count += backtrack(counter); // Count this sequence
                counter.put(ch, counter.get(ch) + 1); // Backtrack
            }
        }

        return count;
    }
}