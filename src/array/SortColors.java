class Solution {
    public void sortColors(int[] nums) {
        int zeroIndex = 0, twoIndex = nums.length - 1;
        int i = 0;
        while (i <= twoIndex) {
            if (nums[i] == 0) {
                swap(nums, zeroIndex, i);
                zeroIndex++;
                i++;// we increment i, cause we're sure it was 1 the 0 is swapped with
            } else if (nums[i] == 2) {
                swap(nums, twoIndex, i);
                twoIndex--;
            } else {
                i++;
            }
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}