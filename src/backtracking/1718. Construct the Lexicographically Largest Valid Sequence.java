// worst case TC : O(n!) & best case : O(n)
class Solution {
    public int[] constructDistancedSequence(int n) {
        int[] res = new int[2 * n - 1];
        backtrack(res, n, 0, new HashSet<>());

        return res;
    }

    private boolean backtrack(int[] arr, int n, int idx, Set<Integer> visited) {
        if (idx == arr.length) {
            return true;
        }

        for (int i = n; i >= 1; i--) {
            int num = i;
            if (visited.contains(num)) {
                continue;
            }
            if (num != 1 && (idx + num >= arr.length || arr[idx + num] != 0)) {
                continue;
            }

            arr[idx] = num;
            if (i != 1) {
                arr[idx + num] = num;
            }
            visited.add(num);

            int j = idx + 1;
            while (j < arr.length && arr[j] != 0) {
                j++;
            }
            if (backtrack(arr, n, j, visited)) {
                return true;
            }

            arr[idx] = 0;
            if (num != 1) {
                arr[idx + num] = 0;
            }
            visited.remove(num);
        }

        return false;
    }
}

class Solution {
    public int[] constructDistancedSequence(int n) {
        int[] res = new int[2 * n - 1];
        backtrack(res, n, 0, new boolean[n + 1]);

        return res;
    }

    private boolean backtrack(int[] arr, int n, int idx, boolean[] visited) {
        if (idx == arr.length) {
            return true;
        }
        if (arr[idx] != 0) {
            return backtrack(arr, n, idx + 1, visited);
        }

        for (int num = n; num >= 1; num--) {
            if (visited[num]) {
                continue;
            }
            int nextIdx = (num == 1) ? idx : idx + num;
            if (nextIdx >= arr.length || arr[nextIdx] != 0) {
                continue;
            }

            arr[idx] = num;
            if (num != 1) arr[nextIdx] = num;
            visited[num] = true;

            if (backtrack(arr, n, idx + 1, visited)) {
                return true;
            }

            arr[idx] = 0;
            if (num != 1) arr[nextIdx] = 0;
            visited[num] = false;
        }

        return false;
    }
}