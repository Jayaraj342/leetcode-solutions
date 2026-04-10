class Solution {
    public boolean checkValidString(String s) {
        int minOpen = 0, maxOpen = 0;

        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                minOpen++;
                maxOpen++;
            } else if (ch == ')') {
                minOpen--;
                maxOpen--;
            } else { // '*'
                minOpen--;
                maxOpen++;
            }

            if (maxOpen < 0) return false; // too many ')'
            if (minOpen < 0) minOpen = 0; // '*' can compensate
        }

        return minOpen == 0;
    }
}

class Solution {
    public boolean checkValidString(String s) {
        Deque<Integer> open = new ArrayDeque<>();
        Deque<Integer> star = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                open.push(i);
            } else if (ch == '*') {
                star.push(i);
            } else { // ch == ')'
                if (!open.isEmpty()) {
                    open.pop();
                } else if (!star.isEmpty()) {
                    star.pop();
                } else {
                    return false;
                }
            }
        }

        // Match remaining '(' with available '*' (which must come after '(')
        while (!open.isEmpty() && !star.isEmpty()) {
            if (open.pop() > star.pop()) return false;
        }

        return open.isEmpty();
    }
}

// n^2, n^2
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