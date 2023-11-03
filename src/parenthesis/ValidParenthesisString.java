class Solution {
    public boolean checkValidString(String s) {
        int leftMin = 0;
        int leftMax = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                leftMin++;
                leftMax++;
            } else if (c == ')') {
                leftMin--;
                leftMax--;
            } else {
                leftMin--;
                leftMax++;
            }

            if (leftMax < 0) {
                return false;
            }
            if (leftMin < 0) {
                leftMin = 0;
            }
        }

        return leftMin == 0;
    }
}

class Solution {
    public boolean checkValidString(String s) {
        return dfs(s, 0, 0);
    }

    Map<String, Boolean> memo = new HashMap<>();

    private boolean dfs(String s, int i, int open) {
        if (open < 0) {
            return false;
        }
        if (i == s.length()) {
            return open == 0;
        }
        String key = i + "," + open;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        if (s.charAt(i) == '(') {
            return dfs(s, i + 1, open + 1);
        } else if (s.charAt(i) == ')') {
            return dfs(s, i + 1, open - 1);
        }

        boolean res = dfs(s, i + 1, open + 1) || dfs(s, i + 1, open - 1) || dfs(s, i + 1, open);
        memo.put(key, res);
        return res;
    }
}

// OR
// https://leetcode.com/problems/valid-parenthesis-string/solutions/825937/java-double-stack-0ms-beats-100-runtime/

// Timeout
class Solution {
    public boolean checkValidString(String s) {
        return dfs(s.toCharArray(), 0);
    }

    private boolean dfs(char[] s, int i) {
        if (i == s.length) {
            return isValid(new String(s));
        }

        boolean res = false;
        if (s[i] == '*') {
            s[i] = '(';
            res = dfs(s, i + 1);

            s[i] = ')';
            res = res || dfs(s, i + 1);

            s[i] = ' ';
            res = res || dfs(s, i + 1);

            s[i] = '*';
        } else {
            res = dfs(s, i + 1);
        }

        return res;
    }

    private boolean isValid(String s) {
        int open = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                open++;
            } else if(c == ')'){
                open--;
            }
            if (open < 0) {
                return false;
            }
        }

        return open == 0;
    }
}