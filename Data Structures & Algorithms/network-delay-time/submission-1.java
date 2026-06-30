
class Solution {

    // Pair stores:
    // v = destination node
    // t = time / distance value
    class Pair {
        int v;
        int t;

        Pair(int v, int t) {
            this.v = v;
            this.t = t;
        }
    }

    public int networkDelayTime(int[][] times, int n, int k) {

        // Build adjacency list graph
        // graph[u] contains all neighbors reachable from node u
        ArrayList<ArrayList<Pair>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        // Fill graph from input edges: u -> v with travel time t
        for (int i = 0; i < times.length; i++) {
            int u = times[i][0];
            int v = times[i][1];
            int t = times[i][2];

            graph.get(u).add(new Pair(v, t));
        }

        // dis[i] = shortest known/finalized time to reach node i
        // Initially all nodes are unreachable
        boolean[] vis = new boolean[n+1];

        // Min-heap based on time
        // The pair with smaller time gets higher priority
        PriorityQueue<Pair> pq = new PriorityQueue<>(
            (a, b) -> {
                if (a.t < b.t) return -1;
                else if (a.t > b.t) return 1;
                else return 0;
            }
        );

        // Start from source node k at time 0
        pq.add(new Pair(k, 0));

        // Will store the time when the last node gets finalized
        int tot_time = 0;

        // Standard Dijkstra processing
        while (pq.size() > 0) {
            Pair rem = pq.remove();
            int u = rem.v;
            int t = rem.t;

            // If distance for this node is already finalized, skip it
            if (vis[u] == true) continue;
            else {
                // First time we pop this node => shortest time to reach u
                vis[u] = true;

                // Since nodes are processed in increasing time order,
                // the latest finalized time is the answer candidate
                tot_time = t;

                // Relax neighbors
                for (Pair nbr : graph.get(u)) {
                    // Only push neighbor if it is not finalized yet
                    if (vis[nbr.v] == false) {
                        pq.add(new Pair(nbr.v, t + nbr.t));
                    }
                }
            }
        }

        // If any node is still unreachable, answer is -1
        for (int i = 1; i <= n; i++) {
            if (vis[i] == false) return -1;
        }

        // Otherwise, total delay is the maximum shortest path
        return tot_time;
    }
}
