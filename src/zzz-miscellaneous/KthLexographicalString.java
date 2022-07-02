class Solution {
    public String getHappyString(int n, int k) {
        k -= 1;
        String res = "";
        for (int i = n - 1; i >= 0; i--) {
            res = (char) ((k % 26) + 'a') + res;
            k /= 26;
        }

        return res;
    }
}