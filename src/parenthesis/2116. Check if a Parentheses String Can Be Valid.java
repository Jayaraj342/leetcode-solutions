class Solution {
    public boolean canBeValid(String s, String locked) {
        Stack<Integer> openParen = new Stack<>(), wildcard = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (locked.charAt(i) == '0') {
                wildcard.push(i);
            } else {
                if (s.charAt(i) == '(') {
                    openParen.push(i);
                } else {
                    if (!openParen.isEmpty()) {
                        openParen.pop();
                    } else if(!wildcard.isEmpty()) {
                        wildcard.pop();
                    } else {
                        return false;
                    }
                }
            }
        }

        int opensRemaining = openParen.size();
        for (int i = 0; i < opensRemaining; i++) {
            int lastOpen = openParen.pop();
            if (!wildcard.isEmpty() && wildcard.peek() > lastOpen) {
                wildcard.pop();
            } else {
                return false;
            }
        }

        return wildcard.size() % 2 == 0;
    }
}

// SC : O(1)
class Solution {
    public boolean canBeValid(String s, String locked) {
        int length = s.length();

        // If length of string is odd, return false.
        if (length % 2 == 1) {
            return false;
        }

        int open = 0, wildcard = 0;
        // Iterate through the string to handle '(' and ')'.
        for (int i = 0; i < length; i++) {
            if (locked.charAt(i) == '0') {
                wildcard++;
            } else if (s.charAt(i) == '(') {
                open++;
            } else if (s.charAt(i) == ')') {
                if (open > 0) {
                    open--;
                } else if (wildcard > 0) {
                    wildcard--;
                } else {
                    return false;
                }
            }
        }

        // Match remaining open brackets with wildcard characters.
        int close = 0;
        for (int i = length - 1; i >= 0; i--) {
            if (locked.charAt(i) == '0') {
                close++;
                wildcard--;
            } else if (s.charAt(i) == '(') {
                close--;
                open--;
            } else if (s.charAt(i) == ')') {
                close++;
            }
            if (close < 0) {
                return false;
            }
        }

        return open <= 0;
    }
}

// n^2 - won't pass
class Solution {
    Map<String, Boolean> memo;

    public boolean canBeValid(String s, String locked) {
        memo = new HashMap<>();
        return dfs(s.toCharArray(), locked.toCharArray(), 0, 0);
    }

    private boolean dfs(char[] s, char[] locked, int idx, int open) {
        if (idx == s.length) {
            return open == 0;
        }
        if (open < 0) {
            return false;
        }
        String key = idx + "," + open;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        char curr = s[idx];
        boolean res = false;
        if (curr == ')') {
            if (open == 0) {
                if (locked[idx] == '0') {
                    res = dfs(s, locked, idx + 1, open + 1);
                }
            } else {
                res = dfs(s, locked, idx + 1, open - 1) || (locked[idx] == '0' && dfs(s, locked, idx + 1, open + 1));
            }
        } else {
            res = dfs(s, locked, idx + 1, open + 1) || locked[idx] == '0' && dfs(s, locked, idx + 1, open - 1);
        }

        memo.put(key, res);
        return res;
    }
}