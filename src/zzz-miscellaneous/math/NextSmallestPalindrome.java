// https://www.geeksforgeeks.org/given-a-number-find-next-smallest-palindrome-larger-than-this-number/
class Solution {
    public static void main(String[] args) {
        int val = 9;
        System.out.println(nextPalindrome(val + ""));
    }

    private static String nextPalindrome(String val) {
        int n = val.length();

        int mid = n / 2;
        int i, j;
        if (n % 2 == 1) {
            i = mid - 1;
            j = mid + 1;
        } else {
            i = mid - 1;
            j = mid;
        }

        // find first non-different char
        int tempI = i, tempJ = j;
        while (tempI >= 0 && val.charAt(tempI) == val.charAt(tempJ)) {
            tempI--;
            tempJ++;
        }

        // add 1 to middle if right is greater : 1234 or 123, and copy left to right
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        if (tempI < 0 || val.charAt(tempI) < val.charAt(tempJ)) {
            carry = 1;
        }
        if (n % 2 == 1) {
            int curr = (val.charAt(mid) - '0') + carry;
            sb.append(curr % 10);
            carry = curr / 10;
        }

        while (i >= 0) {
            int curr = (val.charAt(i) - '0') + carry;
            sb.append(curr % 10);
            sb.insert(0, curr % 10);
            carry = curr / 10;
            i--;
        }
        if (carry != 0) {
            sb.append(carry);
            sb.insert(0, carry);
        }

        // for all 9's case remove 1 zero from result
        if (val.replace("9", "").length() == 0) {
            return sb.toString().replaceFirst("0", "");
        }
        return sb.toString();
    }
}