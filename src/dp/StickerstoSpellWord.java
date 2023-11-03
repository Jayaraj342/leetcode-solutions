class Solution {
    Map<String, Integer> dp = new HashMap<>();

    public int minStickers(String[] stickers, String target) {
        List<HashMap<Character, Integer>> counts = new ArrayList<>();

        Set<Character> unique = new HashSet<>();
        for (char c : target.toCharArray()) {
            unique.add(c);
        }
        for (String sticker : stickers) {
            HashMap<Character, Integer> count = new HashMap<>();
            for (char c : sticker.toCharArray()) {
                unique.remove(c);
                count.put(c, count.getOrDefault(c, 0) + 1);
            }
            counts.add(count);
        }
        if (!unique.isEmpty()) {
            return -1;
        }

        return dfs(counts, new StringBuilder(target));
    }

    private int dfs(List<HashMap<Character, Integer>> counts, StringBuilder target) {
        if (target.length() == 0) {
            return 0;
        }
        if (dp.containsKey(target.toString())) {
            return dp.get(target.toString());
        }

        int min = Integer.MAX_VALUE;
        for (HashMap<Character, Integer> sticker : counts) {
            StringBuilder newT = useCurrentSticker(target, sticker);
            if (newT.length() != target.length()) {
                min = Math.min(min, 1 + dfs(counts, newT));
            }
        }
        dp.put(target.toString(), min);
        return min;
    }

    private StringBuilder useCurrentSticker(StringBuilder target, Map<Character, Integer> sticker) {
        Map<Character, Integer> copy = new HashMap<>(sticker);
        StringBuilder newT = new StringBuilder();
        for (Character c : target.toString().toCharArray()) {
            if (copy.getOrDefault(c, 0) > 0) {
                copy.put(c, copy.get(c) - 1);
            } else {
                newT.append(c);
            }
        }

        return newT;
    }
}