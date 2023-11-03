class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) {
            return 0;
        }

        Set<String> transformed = new HashSet<>();
        transformed.add(beginWord);

        int count = 1;
        while (!transformed.contains(endWord)) {
            Set<String> tempTransformed = new HashSet<>();
            for (String curr : transformed) {
                for (int i = 0; i < curr.length(); i++) {
                    char[] currArr = curr.toCharArray();
                    for (char letter = 'a'; letter <= 'z'; letter++) {
                        currArr[i] = letter;
                        String newTransform = new String(currArr);
                        if (wordSet.contains(newTransform)) {
                            tempTransformed.add(newTransform);
                            wordSet.remove(newTransform);
                        }
                    }
                }
            }
            count++;
            if (tempTransformed.isEmpty()) {
                return 0;
            }
            transformed = tempTransformed;
        }

        return count;
    }
}