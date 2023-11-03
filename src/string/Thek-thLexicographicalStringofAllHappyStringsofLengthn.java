class Solution {
    StringBuilder sb = new StringBuilder();
    List<String> list = new ArrayList<>();

    public String getHappyString(int n, int k) {
        backtrack(n, ' ');
        if(list.size() < k) {
            return "";
        }
        return list.get(k - 1);
    }

    private void backtrack(int n, char prev) {
        if (sb.length() == n) {
            list.add(sb.toString());
            return;
        }
        for (char ch = 'a'; ch <= 'c'; ch++) {
            if (prev != ch) {
                sb.append(ch);
                backtrack(n, ch);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
}

// Input: n = 3, k = 9
// Output: "cab"
// ["aba", "abc", "aca", "acb", "bab", "bac", "bca", "bcb", "cab", "cac", "cba", "cbc"]
class Solution {
    StringBuilder sb = new StringBuilder();

    public String getHappyString(int n, int k) {
        build(n, k, '-');
        return sb.toString();
    }

    private void build(int n, int k, char prev) {
        if(n == 0) {
            return;
        }
        int combPerChar = (int) Math.pow(2, n - 1);
        int window = combPerChar;
        for (char c : "abc".toCharArray()) {
            if(c == prev) {
                continue;
            }
            if (k <= window) {
                sb.append(c);
                build(n - 1, k - (window - combPerChar), c);
                return;
            }
            window += combPerChar;
        }
    }
}