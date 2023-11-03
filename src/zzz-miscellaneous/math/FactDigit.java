// https://practice.geeksforgeeks.org/problems/fact-digit-sum4125/1
class Solution {
    int[] fact = new int[10];
    ArrayList<Integer> arr = new ArrayList<>();

    ArrayList<Integer> FactDigit(int N) {
        fact[0] = 1;
        for (int i = 1; i < 10; i++) {
            fact[i] = fact[i - 1] * i;
        }
        fun(N);
        Collections.sort(arr);
        return arr;
    }

    public int fun(int n) {
        if (n < 0) return -1;
        if (n == 0) return 1;
        for (int i = 9; i >= 1; i--) {
            int p = fun(n - fact[i]);
            if (p == 1) {
                arr.add(i);
                return 1;
            }
        }
        return -1;
    }
}