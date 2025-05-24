//[1 2 3 9]
//[4 5 7 11]
//=> [1 2 3 4 5 7 9 11]
//ans => (4+5)/2

// O(log(min(m, n)))
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums2.length < nums1.length)
            return findMedianSortedArrays(nums2, nums1);
        int m = nums1.length, n = nums2.length;
        // m because 1st array can have 0 elements too, right1 can be m in some cases
        int i = 0, j = m;

        int k = (m + n) / 2;
        while (i <= j) {
            int cut1 = (i + j) / 2;
            int cut2 = k - cut1;

            int left1 = cut1 == 0 ? Integer.MIN_VALUE : nums1[cut1 - 1];
            int left2 = cut2 == 0 ? Integer.MIN_VALUE : nums2[cut2 - 1];
            int right1 = cut1 == m ? Integer.MAX_VALUE : nums1[cut1];
            int right2 = cut2 == n ? Integer.MAX_VALUE : nums2[cut2];

            if (left1 > right2) {
                j = cut1 - 1;
            } else if (left2 > right1) {
                i = cut1 + 1;
            } else {
                return ((m + n) % 2 == 0) ? (Math.max(left1, left2) + Math.min(right1, right2)) / 2.0 : Math.min(right1, right2);
            }
        }

        return 0.0;
    }
}
// can do it with cut2 = (k - cut1 - 2) as well
// then can do cut1 + 1 for right1 ..

// O(log(min(m, n)))
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) return findMedianSortedArrays(nums2, nums1);

        int m = nums1.length, n = nums2.length;
        int k = (m + n) / 2;
        int l = 0, r = m - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums1[mid] < nums2[k - mid - 1]) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        double candidate1 = Math.min(
                l == m ? Integer.MAX_VALUE : nums1[l],
                k - l == n ? Integer.MAX_VALUE : nums2[k - l]
        );
        if ((m + n) % 2 == 1) {
            return candidate1;
        }
        double candidate2 = Math.max(
                l == 0 ? Integer.MIN_VALUE : nums1[l - 1],
                k - l == 0 ? Integer.MIN_VALUE : nums2[k - l - 1]
        );
        return (candidate1 + candidate2) / 2;
    }
}

// O(m+n)
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        int sum = n + m;

        int i = 0, j = 0;
        int prev = 0, current = 0;

        while (i + j <= sum / 2) {
            prev = current;
            if (i == n) {
                current = nums2[j++];
            } else if (j == m) {
                current = nums1[i++];
            } else {
                current = nums1[i] > nums2[j] ? nums2[j++] : nums1[i++];
            }
        }

        if (sum % 2 == 0) {
            return (float) (prev + current) / 2;
        }

        return current;
    }
}
