class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            
            // Prior to adding the edge, check if u and v are already connected
            boolean[] visited = new boolean[n + 1];
            if (hasPath(u, v, graph, visited)) {
                return edge; // This is the redundant connection!
            }
            
            // If not connected, safely add the edge to the graph
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        
        return new int[0];
    }

    // Simple DFS helper to check if a path exists between src and dest
    private boolean hasPath(int src, int dest, ArrayList<ArrayList<Integer>> graph, boolean[] visited) {
        if (src == dest) return true;
        visited[src] = true;
        
        for (int neighbor : graph.get(src)) {
            if (!visited[neighbor]) {
                if (hasPath(neighbor, dest, graph, visited)) {
                    return true;
                }
            }
        }
        return false;
    }
}
