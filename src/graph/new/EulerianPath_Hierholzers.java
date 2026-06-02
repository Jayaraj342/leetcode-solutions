//You are given a list of airline tickets represented as:
//
//        [from, to]
//
//Reconstruct the itinerary in order such that:
//
//The itinerary uses all tickets exactly once
//Starts from:
//        "JFK"
//If multiple valid itineraries exist:
//        👉 return the lexicographically smallest

//tickets = [
//        ["JFK","SFO"],
//        ["JFK","ATL"],
//        ["SFO","ATL"],
//        ["ATL","JFK"],
//        ["ATL","SFO"]
//        ]
//Output:
//
//        ["JFK","ATL","JFK","SFO","ATL","SFO"]

// Eulerian path or Hierholzer's algorithm
// E.log(E), E + V
class Solution {
    public List<String> findItinerary(List<List<String>> tickets) {
        Map<String, PriorityQueue<String>> graph = new HashMap<>();
        for (List<String> route : tickets) {
            String src = route.get(0), dst = route.get(1);
            graph.putIfAbsent(src, new PriorityQueue<>());
            var pq = graph.get(src);
            pq.add(dst);
        }

        List<String> res = new ArrayList<>();
        dfs("JFK", graph, res);
        Collections.reverse(res);

        System.out.println(res);
        return res;
    }

    private void dfs(String curr, Map<String, PriorityQueue<String>> graph, List<String> res) {
        PriorityQueue<String> queue = graph.getOrDefault(curr, new PriorityQueue<>());
        while (!queue.isEmpty()) {
            String smallestNei = queue.remove();
            dfs(smallestNei, graph, res);
        }
        res.add(curr);
    }

    public static void main(String[] args) {
        new Solution().findItinerary(List.of(List.of("JFK", "KUL"), List.of("JFK", "NRT"), List.of("NRT", "JFK")));
    }
}