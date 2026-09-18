// worst case TC : O(n!) & best case : O(n)
class Solution {
    Set<Integer> visited;

    public int[] constructDistancedSequence(int n) {
        visited = new HashSet<>();

        int[] res = new int[2 * n - 1];
        backtrack(res, 0, n);
        return res;
    }

    private boolean backtrack(int[] res, int index, int maxNum) {
        if (index == res.length) {
            return true;
        }

        if (res[index] != 0) {
            return backtrack(res, index + 1, maxNum);
        }

        for (int num = maxNum; num >= 1; num--) {
            if (visited.contains(num)) continue;

            int j = index + num;
            boolean canPlace = num == 1 || (j < res.length && res[j] == 0);
            if (canPlace) {
                res[index] = num;
                if (num != 1) res[j] = num;
                visited.add(num);

                if (backtrack(res, index + 1, maxNum)) return true;

                res[index] = 0;
                if (num != 1) res[j] = 0;
                visited.remove(num);
            }
        }

        return false;
    }
}

// won't give lexographical sequence
class Solution {
    public int[] constructDistancedSequence(int n) {
        int[] res = new int[2 * (n - 1) + 1];
        dfs(n, res, n);

        return res;
    }

    private boolean dfs(int n, int[] res, int curr) {
        if (curr == 0) {
            return true;
        }

        for (int i = 0; i < res.length; i++) {
            if (res[i] == 0 && (((i + curr) >= 0 && (i + curr) < res.length && res[i + curr] == 0) || curr == 1)) {
                res[i] = curr;
                if (curr != 1) res[i + curr] = curr;

                if (dfs(n, res, curr - 1)) {
                    return true;
                }

                res[i] = 0;
                if (curr != 1) res[i + curr] = 0;
            }
        }

        return false;
    }
}