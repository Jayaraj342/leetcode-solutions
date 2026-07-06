// https://leetcode.com/problems/maximum-subarray-sum-with-one-deletion
// https://github.com/Jayaraj342/leetcode-solutions/blob/master/src/array/new/1186. Maximum Subarray Sum with One Deletion.java

// n, 1
class Solution {
    public int maximumSum(int[] arr) {
        int n = arr.length;

        // Base case: if array has 1 element, no deletion is possible
        int maxNoDeletion = arr[0];
        int maxOneDeletion = arr[0];
        int overallMax = arr[0];

        for (int i = 1; i < n; i++) {
            // maxOneDeletion choice 1: Delete current element -> take previous maxNoDeletion
            // maxOneDeletion choice 2: Keep current element -> add it to previous maxOneDeletion
            maxOneDeletion = Math.max(maxNoDeletion, maxOneDeletion + arr[i]);

            // Standard Kadane's algorithm for no deletions
            maxNoDeletion = Math.max(arr[i], maxNoDeletion + arr[i]);

            // Keep track of the highest global sum seen so far
            overallMax = Math.max(overallMax, Math.max(maxNoDeletion, maxOneDeletion));
        }

        return overallMax;
    }
}

// n, n
class Solution {
    public int maximumSum(int[] arr) {
        int n = arr.length;
        int max = arr[0];

        int[] maxEndHere = new int[n];
        maxEndHere[0] = arr[0];
        for (int i = 1; i < n; i++) {
            maxEndHere[i] = Math.max(arr[i], maxEndHere[i - 1] + arr[i]);
            max = Math.max(max, maxEndHere[i]);
        }

        int[] maxStartHere = new int[n];
        maxStartHere[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            maxStartHere[i] = Math.max(arr[i], maxStartHere[i + 1] + arr[i]);
        }

        for (int i = 1; i < n - 1; i++) {
            max = Math.max(max, maxEndHere[i - 1] + maxStartHere[i + 1]);
        }

        return max;
    }
}