
class Solution {

    // Represents one flight edge or one state in the search
    class Triplet {
        int v;      // destination city
        int cost;   // total cost so far
        int stops;  // number of flights taken so far

        Triplet(int v, int c, int s) {
            this.v = v;
            cost = c;
            stops = s;
        }
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {

        // Build adjacency list:
        // graph[u] contains all cities reachable from u with flight cost
        ArrayList<ArrayList<Triplet>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < flights.length; i++) {
            int u = flights[i][0];
            int v = flights[i][1];
            int cost = flights[i][2];

            // Add directed edge u -> v
            graph.get(u).add(new Triplet(v, cost, 0));
        }

        // minStops[u] = minimum number of stops used so far to reach city u
        // Used as a pruning step to avoid exploring worse paths repeatedly
        int[] minStops = new int[n];
        Arrays.fill(minStops, Integer.MAX_VALUE);

        // Priority queue ordered by minimum cost first
        // This makes it similar to Dijkstra, but with an extra stops state
        PriorityQueue<Triplet> pq = new PriorityQueue<>(
            (a, b) -> {
                if (a.cost < b.cost) return -1;
                else if (a.cost > b.cost) return 1;
                else return 0;
            }
        );

        // Start from source with 0 cost and 0 stops
        pq.add(new Triplet(src, 0, 0));

        while (pq.size() > 0) {

            // Get the currently cheapest path state
            Triplet rem = pq.remove();
            int u = rem.v;
            int costsofar = rem.cost;
            int stopssofar = rem.stops;

            // If destination is reached, this is the cheapest valid answer
            if (u == dst) return costsofar;

            // If we exceeded the stop limit, discard this path
            if (stopssofar > k) continue;

            // If we already reached this city with fewer stops before,
            // this path is not useful
            if (stopssofar >= minStops[u]) continue;

            // Mark the best stop count seen so far for city u
            minStops[u] = stopssofar;

            // Expand neighbors: try all outgoing flights from city u
            for (Triplet nbr : graph.get(u)) {
                pq.add(new Triplet(nbr.v, nbr.cost + costsofar, stopssofar + 1));
            }
        }

        // If destination was never reached within k stops
        return -1;
    }
}
