class Solution {
    public int countMonobit(int n) {
        return 32 - Integer.numberOfLeadingZeros(n + 1);
    }
}