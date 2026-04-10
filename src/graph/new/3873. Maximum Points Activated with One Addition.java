// https://leetcode.com/problems/maximum-points-activated-with-one-addition/
// https://github.com/Jayaraj342/leetcode-solutions/blob/master/src/graph/new/3873. Maximum Points Activated with One Addition.java

class Solution {
    public int maxActivated(int[][] points) {
        Map<Integer, Integer> xMap = new HashMap<>();
        Map<Integer, Integer> yMap = new HashMap<>();

        int n = points.length;
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < n; i++) {
            int[] point = points[i];
            int x = point[0], y = point[1];

            if (!xMap.containsKey(x)) {
                xMap.put(x, i);
            } else {
                union(parent, i, xMap.get(x));
            }

            if (!yMap.containsKey(y)) {
                yMap.put(y, i);
            } else {
                union(parent, i, yMap.get(y));
            }
        }

        int[] size = new int[n];
        for (int i = 0; i < n; i++) {
            int pi = find(parent, i);
            size[pi]++;
        }

        int max1 = 0, max2 = 0;
        for (int num : size) {
            if (num > max1) {
                max2 = max1;
                max1 = num;
            } else if (num > max2) {
                max2 = num;
            }
        }

        return max1 + max2 + 1;
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