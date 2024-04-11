class Solution {
    public boolean canTraverseAllPairs(int[] nums) {
        int n = nums.length;
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        Map<Integer, Integer> factorIndex = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int fac = 2, num = nums[i];
            while (fac * fac <= num) {
                if (num % fac == 0) {
                    if (factorIndex.containsKey(fac)) {
                        union(parent, i, factorIndex.get(fac));
                    } else {
                        factorIndex.put(fac, i);
                    }
                }
                while (num % fac == 0) {
                    num /= fac;
                }
                fac++;
            }
            if (num > 1) {
                if (factorIndex.containsKey(num)) {
                    union(parent, i, factorIndex.get(num));
                } else {
                    factorIndex.put(num, i);
                }
            }
        }

        int root = find(parent, parent[0]);
        for (int num : parent) {
            if (root != find(parent, num)) {
                return false;
            }
        }

        return true;
    }

    private void union(int[] parent, int u, int v) {
        int pu = find(parent, u), pv = find(parent, v);
        if (pu != pv) {
            parent[pu] = pv;
        }
    }

    private int find(int[] parent, int x) {
        if (parent[x] != x) {
            parent[x] = find(parent, parent[x]);
        }
        return parent[x];
    }
}