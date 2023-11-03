// O(log(n-k)*k)
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

// O(log(n)*k)
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int idx = 0;
        int i = 0, j = arr.length - 1;

        while (i <= j) {
            int mid = i + (j - i) / 2;

            int currDiff = Math.abs(arr[mid] - x);
            int minDiff = Math.abs(arr[idx] - x);
            if (currDiff < minDiff || (currDiff == minDiff && arr[mid] < arr[idx])) {
                idx = mid;
            }

            if (arr[mid] < x) {
                i = mid + 1;
            } else if (arr[mid] > x) {
                j = mid - 1;
            } else {
                break;
            }
        }

        i = idx;
        j = idx;
        k = k - 1;// idx already is 1 element
        while(k > 0) {
            if (i == 0) {
                j++;
            } else if (j == arr.length - 1) {
                i--;
            } else if (x - arr[i - 1] <= arr[j + 1] - x) {
                i--;
            } else {
                j++;
            }
            k--;
        }

        List<Integer> result = new ArrayList<>();
        for (int itr = i; itr <= j; itr++) {
            result.add(arr[itr]);
        }

        return result;
    }
}