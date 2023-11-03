class Solution {
    //2147483476
    public int nextGreaterElement(int n) {
        char[] arr = String.valueOf(n).toCharArray();
        int size = arr.length;
        int i = size - 2;
        while (i >= 0) {
            if (arr[i] < arr[i + 1]) {
                break;
            }
            i--;
        }
        if (i == -1) {
            return -1;
        }

        int k = size - 1;
        while (k >= 0) {
            if (arr[k] > arr[i]) {
                break;
            }
            k--;
        }

        char temp = arr[i];
        arr[i] = arr[k];
        arr[k] = temp;

        StringBuilder ans = new StringBuilder();
        for (int j = 0; j < i + 1; j++) {
            ans.append(arr[j]);
        }
        for (int j = size - 1; j >= i + 1; j--) {
            ans.append(arr[j]);
        }

        long ans_ = Long.parseLong(ans.toString());

        return (ans_ > Integer.MAX_VALUE) ? -1 : (int)ans_;
    }
}