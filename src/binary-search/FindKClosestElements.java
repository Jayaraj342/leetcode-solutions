class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int index = 0;
        int l = 0, r = arr.length - 1;

        while (l <= r) {
            int mid = l + (r - l) / 2;

            int currDiff = Math.abs(arr[mid] - x);
            int minDiff = Math.abs(arr[index] - x);
            if (currDiff < minDiff || (currDiff == minDiff && arr[mid] < arr[index])) {
                index = mid;
            }

            if (arr[mid] < x) {
                l = mid + 1;
            } else if (arr[mid] > x) {
                r = mid - 1;
            } else {
                break;
            }
        }

        l = index;
        r = index;
        for (int ignored : IntStream.range(0, k - 1).toArray()) {
            if (l == 0) {
                r++;
            } else if (r == arr.length - 1) {
                l--;
            } else if (x - arr[l - 1] <= arr[r + 1] - x) {
                l--;
            } else {
                r++;
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = l; i <= r; i++) {
            result.add(arr[i]);
        }

        return result;
    }
}

class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int l = 0, r = arr.length - k;

        while (l < r) {
            int mid = l + (r - l) / 2;

            int leftDiff = x - arr[mid];
            int rightDiff = arr[mid + k] - x;

            if (leftDiff > rightDiff) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = l; i < l + k; i++) {
            result.add(arr[i]);
        }

        return result;
    }
}