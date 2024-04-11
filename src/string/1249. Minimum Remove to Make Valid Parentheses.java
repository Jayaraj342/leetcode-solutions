class Solution {
    public String minRemoveToMakeValid(String s) {
        StringBuilder sb = new StringBuilder();
        int net = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                sb.append(c);
                net++;
            } else if (c == ')' && net > 0) {
                sb.append(c);
                net--;
            } else if (c >= 'a' && c <= 'z') {
                sb.append(c);
            }
        }

        StringBuilder res = new StringBuilder();
        for (char c : sb.reverse().toString().toCharArray()) {
            if (c == '(' && net > 0) {
                net--;
            } else {
                res.append(c);
            }
        }

        return res.reverse().toString();
    }
}