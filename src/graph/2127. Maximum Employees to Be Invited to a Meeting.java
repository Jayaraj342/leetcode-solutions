class Solution {
    public int maximumInvitations(int[] favorite) {
        // There will always be cycle - no. edges == no. nodes
        // find cycles and their length

        int n = favorite.length; // no. employees

        boolean[] visited = new boolean[n];
        int max = 0;
        List<Integer> cyclesOfLen2 = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!visited[i]) { // 'i' can be outside or inside loop
                int curr = i, len = 0;
                while (!visited[curr]) {
                    visited[curr] = true;
                    curr = favorite[curr];
                    len++;
                }

                int outsideNodes = 0, temp = i;
                while (temp != curr) {
                    outsideNodes++;
                    temp = favorite[temp];
                }

                int cycleLen = len - outsideNodes;
                // found length of cycle
                // if cycleLen != 2, ans is max of cycleLen
                // else find len of all nodes connected on both sides
                // [1,0,0,2,1,4,7,8,9,6,7,10,8]
                if (cycleLen == 2) {
                    // curr is inside 2 loop
                    cyclesOfLen2.add(curr);
                } else {
                    max = Math.max(max, cycleLen);
                }
            }
        }

        List<Integer>[] revGraph = new List[n];
        for (int i = 0; i < n; i++) {
            revGraph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            revGraph[favorite[i]].add(i);
        }

        int temp2 = 0;
        for (int src : cyclesOfLen2) {
            temp2 += bfs(revGraph, src, favorite[src]) + bfs(revGraph, favorite[src], src) + 2;
        }

        return Math.max(max, temp2);
    }

    // reverse edges
    // treating it as undirected graph won't work as a node can have 3 edges and we should not count it
    private int bfs(List<Integer>[] revGraph, int src, int siblingPair) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {src, 0}); // one node can have many outgoing nodes - have to choose max 1

        int res = 0;
        while (!queue.isEmpty()) {
            int[] last = queue.remove();
            int u = last[0], len = last[1];
            if (u == siblingPair) {
                continue;
            }
            res = Math.max(res, len);
            for (int nei : revGraph[u]) {
                queue.add(new int[] {nei, len + 1});
            }
        }

        return res;
    }
}