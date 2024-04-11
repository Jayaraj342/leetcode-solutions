class Solution {
    public int[] deckRevealedIncreasing(int[] deck) {
        Arrays.sort(deck);
        int n = deck.length;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            queue.add(i);
        }

        int[] res = new int[n];
        for (int card : deck) {
            int idx = queue.remove();
            res[idx] = card;
            if (!queue.isEmpty()) {
                queue.add(queue.remove());
            }
        }

        return res;
    }
}