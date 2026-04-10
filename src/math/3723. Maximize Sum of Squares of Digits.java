class Solution {
    public String maxSumOfSquares(int num, int sum) {
        if (sum > num * 9) {
            return "";
        }

        StringBuilder sb = new StringBuilder().repeat('9', sum / 9);
        int remainder = sum % 9;
        if (remainder > 0) {
            sb.append(remainder);
        }
        sb.repeat('0', num - sb.length());

        return sb.toString();
    }
}