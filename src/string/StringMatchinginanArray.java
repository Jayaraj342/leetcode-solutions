// O(m*n)^2
class Solution {
    public List<String> stringMatching(String[] words) {
        String sum = String.join(" ", words);
        List<String> list = new ArrayList<>();
        for (String word : words) {
            if (sum.indexOf(word) != sum.lastIndexOf(word)) {
                list.add(word);
            }
        }

        return list;
    }
}

