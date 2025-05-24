class Solution {
    public int mostProfitablePath(int[][] edges, int bob, int[] amount) {
        int n = amount.length;
        // initialize adjacency list
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            adj[u].add(v);
            adj[v].add(u);
        }

        // DFS - bob's simulation
        Map<Integer, Integer> bobsNodeVisitTime = new HashMap<>();
        dfs(adj, bob, -1, 0, bobsNodeVisitTime);

        // BFS - alice's simulation
        Queue<int[]> queue = new LinkedList<>(); // [currNode, prevNode, time, amountTillNow]
        queue.add(new int[]{0, -1, 0, amount[0]});

        int maxProfit = Integer.MIN_VALUE;
        while (!queue.isEmpty()) {
            int[] last = queue.remove();
            int currNode = last[0], prevNode = last[1], time = last[2], amountTillNow = last[3];

            if (currNode != 0 && adj[currNode].size() == 1) {
                maxProfit = Math.max(maxProfit, amountTillNow);
            }

            for (int nei : adj[currNode]) {
                if (nei == prevNode) continue;

                int neiTime = time + 1, neiAmount = amount[nei];
                if (bobsNodeVisitTime.containsKey(nei)) {
                    int bobTime = bobsNodeVisitTime.get(nei);
                    if (neiTime > bobTime) {
                        neiAmount = 0;
                    }
                    if (neiTime == bobTime) {
                        neiAmount /= 2;
                    }
                }

                queue.add(new int[]{nei, currNode, neiTime, amountTillNow + neiAmount});
            }
        }

        return maxProfit;
    }

    private boolean dfs(List<Integer>[] adj, int curr, int prev, int time, Map<Integer, Integer> bobsNodeVisitTime) {
        if (curr == 0) {
            bobsNodeVisitTime.put(curr, time);
            return true;
        }

        for (int nei : adj[curr]) {
            if (nei == prev) continue;

            if (dfs(adj, nei, curr, time + 1, bobsNodeVisitTime)) {
                bobsNodeVisitTime.put(curr, time);
                return true;
            }
        }

        return false;
    }
}