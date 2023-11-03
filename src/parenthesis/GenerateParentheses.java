class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList();
        backtrack(ans, new StringBuilder(), 0, 0, n);
        return ans;
    }

    public void backtrack(List<String> ans, StringBuilder cur, int open, int close, int max){
        if (cur.length() == max * 2) {
            ans.add(cur.toString());
            return;
        }

        if (open < max) {
            cur.append("(");
            backtrack(ans, cur, open+1, close, max);
            cur.deleteCharAt(cur.length() - 1);
        }
        if (close < open) {
            cur.append(")");
            backtrack(ans, cur, open, close+1, max);
            cur.deleteCharAt(cur.length() - 1);
        }
    }
}

class Solution {
    List<String> result = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        dfs(n, "", 0, 0);
        return result;
    }

    private void dfs(int n, String parenthesis, int open, int close) {
        if (close > open || open > n) {
            return;
        }
        if (open == n && close == n) {
            result.add(parenthesis);
            return;
        }

        dfs(n, parenthesis + "(", open + 1, close);
        dfs(n, parenthesis + ")", open, close + 1);
    }
}