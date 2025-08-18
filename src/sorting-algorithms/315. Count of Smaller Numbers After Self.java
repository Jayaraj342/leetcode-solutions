class Solution {
    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        Pair[] pairs = new Pair[n];
        for (int i = 0; i < n; i++) {
            pairs[i] = new Pair(nums[i], i);
        }

        int[] res = new int[n];
        mergeSort(pairs, 0, n - 1, res);

        List<Integer> resList = new ArrayList<>();
        for (int num : res) {
            resList.add(num);
        }

        return resList;
    }

    private void mergeSort(Pair[] pairs, int lo, int hi, int[] res) {
        if (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            mergeSort(pairs, lo, mid, res);
            mergeSort(pairs, mid + 1, hi, res);

            merge(pairs, lo, mid, hi, res);
        }
    }

    private void merge(Pair[] pairs, int lo, int mid, int hi, int[] res) {
        int n1 = mid - lo + 1, n2 = hi - mid;
        Pair[] temp1 = new Pair[n1], temp2 = new Pair[n2];

        for (int i = 0; i < n1; i++) {
            temp1[i] = pairs[lo + i];
        }
        for (int j = 0; j < n2; j++) {
            temp2[j] = pairs[mid + j + 1];
        }

        int i = 0, j = 0, k = lo;
        int cnt = 0;
        while (i < n1 && j < n2) {
            if (temp1[i].num > temp2[j].num) {
                cnt++;
                pairs[k++] = temp2[j++];
            } else {
                res[temp1[i].idx] += cnt;
                pairs[k++] = temp1[i++];
            }
        }
        while (i < n1) {
            res[temp1[i].idx] += cnt;
            pairs[k++] = temp1[i++];
        }
        while (j < n2) {
            pairs[k++] = temp2[j++];
        }
    }

    private static class Pair {
        int num;
        int idx;

        Pair(int num, int idx) {
            this.num = num;
            this.idx = idx;
        }
    }
}