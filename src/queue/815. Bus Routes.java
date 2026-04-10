class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) return 0;

        int n = routes.length;

        // Build mapping: busStop -> list of bus routes passing through it
        Map<Integer, List<Integer>> stopToRoutes = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int stop : routes[i]) {
                stopToRoutes.computeIfAbsent(stop, k -> new ArrayList<>()).add(i);
            }
        }

        // BFS setup
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visitedStops = new HashSet<>();
        Set<Integer> visitedRoutes = new HashSet<>();

        queue.add(source);
        visitedStops.add(source);

        int buses = 0;

        // BFS traversal
        while (!queue.isEmpty()) {
            int size = queue.size();
            buses++; // Each BFS level = taking one more bus

            for (int i = 0; i < size; i++) {
                int currStop = queue.remove();

                // Get all bus routes passing through this stop
                List<Integer> routesList = stopToRoutes.getOrDefault(currStop, Collections.emptyList());

                for (int routeIndex : routesList) {
                    if (visitedRoutes.contains(routeIndex)) continue;
                    visitedRoutes.add(routeIndex);

                    // Traverse all stops of this route
                    for (int nextStop : routes[routeIndex]) {
                        if (nextStop == target) return buses;
                        if (visitedStops.add(nextStop)) {
                            queue.offer(nextStop);
                        }
                    }
                }
            }
        }

        return -1; // No path found
    }
}