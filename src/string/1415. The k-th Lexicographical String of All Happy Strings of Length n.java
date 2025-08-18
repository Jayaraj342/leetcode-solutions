
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
                build(n - 1, k - (window - combPerChar), c);// newK -> start of next window
                return;
            }
            window += combPerChar;
        }
    }
}

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

class Solution {
    public String getHappyString(int n, int k) {
        int totalHappyStrings = 3 * (int) Math.pow(2, n - 1);
        StringBuilder res = new StringBuilder();
        String choices = "abc";

        int lo = 1, hi = totalHappyStrings;

        for (int i = 1; i <= n; i++) {
            int partitionSize = (hi - lo + 1) / choices.length();
            int curr = lo; // start of current partition
            for (char c : choices.toCharArray()) {
                if (k >= curr && k <= curr + partitionSize - 1) {
                    res.append(c);

                    lo = curr;
                    hi = curr + partitionSize - 1;

                    choices = "abc".replace(c + "", "");
                    break;
                }
                curr += partitionSize;
            }
        }

        return res.toString();
    }
}