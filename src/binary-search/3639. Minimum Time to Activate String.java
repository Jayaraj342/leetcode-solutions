// n, n
class Solution {
    public int minTime(String s, int[] order, int k) {
        int n = s.length();

        // Total substrings if all are *
        long total = (long) n * (n + 1) / 2;
        if (total < k) return -1;  // Impossible case

        // Doubly linked list simulation using arrays
        int[] prev = new int[n + 1];
        int[] next = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prev[i] = i - 1;
            next[i] = i + 1;
        }

        // Process removals from last to first
        for (int t = n - 1; t >= 0; t--) {
            int i = order[t];
            int left = prev[i], right = next[i];

            // Substrings lost due to removing i
            total -= (i - left) * (right - i);

            // If substrings drop below k, return current time
            if (total < k) return t;

            // Update linked list after removing i
            if (left >= 0) next[left] = right;
            prev[right] = left;
        }

        // Never drops below k
        return 0;
    }
}

// n.log(n), n
class Solution {
    public int minTime(String s, int[] order, int k) {
        int n = s.length();
        // Use a TreeSet to maintain a sorted list of indices
        TreeSet<Integer> pos = new TreeSet<>();
        pos.add(-1);
        pos.add(n);

        // Iterate through the order of removal
        for (int t = 0; t < order.length; ++t) {
            int i = order[t];

            // Find the elements in the sorted set that bracket the current index 'i'
            Integer r = pos.ceiling(i); // 'r' is the smallest element >= i
            Integer l = pos.floor(i);  // 'l' is the largest element <= i

            // The 'cost' to remove an item is the product of the distances to its neighbors
            k -= (i - l) * (r - i);
            pos.add(i);

            // If the total cost is exhausted, return the current time 't'
            if (k <= 0) {
                return t;
            }
        }

        // If all items are removed and k is not exhausted, return -1
        return -1;
    }
}