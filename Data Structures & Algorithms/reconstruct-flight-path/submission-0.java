class Solution {
    // Stores final itinerary in reverse-build order.
    // We add airports to the front during DFS backtracking.
    private LinkedList<String> itinerary = new LinkedList<>();

    // Graph: source airport -> min heap of destination airports
    // Min heap ensures lexicographically smallest destination is used first.
    private Map<String, PriorityQueue<String>> graph = new HashMap<>();

    public List<String> findItinerary(List<List<String>> tickets) {
        // Build directed graph from tickets
        for (List<String> ticket : tickets) {
            String from = ticket.get(0);
            String to = ticket.get(1);

            graph.putIfAbsent(from, new PriorityQueue<>());
            graph.get(from).offer(to);
        }

        // Start DFS from JFK as given in the problem
        dfs("JFK");

        return itinerary;
    }

    private void dfs(String airport) {
        // Keep using the smallest lexicographic outgoing ticket
        // until no more tickets remain from this airport
        PriorityQueue<String> destinations = graph.get(airport);

        while (destinations != null && !destinations.isEmpty()) {
            String nextAirport = destinations.poll(); // use this ticket exactly once
            dfs(nextAirport);
        }

        // Add airport after exploring all outgoing edges.
        // This is the key step in Hierholzer's algorithm.
        itinerary.addFirst(airport);
    }
}
