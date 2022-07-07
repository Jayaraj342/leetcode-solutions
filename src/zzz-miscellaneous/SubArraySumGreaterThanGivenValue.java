//https://www.geeksforgeeks.org/minimum-length-subarray-sum-greater-given-value/
class GFG {
    public int subArraySum(int[] nums, int k) {
        int left = 0, right = 0;
        int n = nums.length;
        int sum = 0;

        int minSub = n;
        while (right < n) {
            while (sum <= k && right < n) {
                sum += nums[right++];
            }
            while (sum > k && left < right) {
                if (right - left < minSub) {
                    minSub = right - left;
                }
                sum -= nums[left++];
            }
        }

        return minSub;
    }

    public static void main(String[] args) {
        System.out.println(new GFG().subArraySum(new int[]{1, 11, 100, 1, 0, 200, 3, 2, 1, 250}, 280));
    }
}