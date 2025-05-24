class Solution {
    public int[] rearrangeArray(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        int positive = 0, negative = 1;

        for (int num : nums) {
            if (num > 0) {
                res[positive] = num;
                positive += 2;
            } else {
                res[negative] = num;
                negative += 2;
            }
        }

        return res;
    }
}

// without extra space - but order won't be preserved so won't help
class Solution {
    public int[] rearrangeArray(int[] nums) {
        int i = 0, j = 1,  n = nums.length;
        while (i < n && j < n) {
            while (i < n && nums[i] >= 0) {
                i+=2;
            }
            while (j < n && nums[j] < 0) {
                j+=2;
            }
            if (i < n && j < n) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }

        return nums;
    }
}

// this won't help in this case
class Solution {
    public int[] rearrangeArray(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void quickSort(int[] nums, int lo, int hi) {
        if (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            quickSort(nums, lo, mid);
            quickSort(nums, mid + 1, hi);

            merge(nums, lo, hi);
        }
    }

    private void merge(int[] nums, int lo, int hi) {
        List<Integer> positives = new ArrayList<>();
        List<Integer> negatives = new ArrayList<>();
        for (int i = lo; i <= hi; i++) {
            if (nums[i] >= 0) {
                positives.add(nums[i]);
            } else {
                negatives.add(nums[i]);
            }
        }
        int p = positives.size(), n = negatives.size();
        int k = lo, i = 0, j = 0;
        while (i < p && j < n) {
            nums[k++] = positives.get(i++);
            nums[k++] = negatives.get(j++);
        }
        while (i < p) {
            nums[k++] = positives.get(i++);
        }
        while (j < n) {
            nums[k++] = negatives.get(j++);
        }
    }
}

// ---------------------------------------------------------- similar question
class Solution {
    public int[] sortArrayByParityII(int[] A) {
        int even = 0, odd = 1;
        while (true) {
            while (even < A.length && A[even] % 2 == 0)
                even += 2;
            while (odd < A.length && A[odd] % 2 != 0)
                odd += 2;
            if (odd >= A.length || even >= A.length) return A;

            int temp = A[even];
            A[even] = A[odd];
            A[odd] = temp;
        }
    }
}