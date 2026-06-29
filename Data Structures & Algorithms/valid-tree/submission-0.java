class Solution {
    
    
    public boolean iscycle(ArrayList<ArrayList<Integer>> graph, int src, int parent, boolean[] vis){
        // Mark the current source node as visited
        vis[src] = true;
        
        // Traverse through all adjacent neighbors of the current node
        for(int nbr: graph.get(src)){
            // Case 1: If the neighbor has not been visited yet, recurse deeper
            if(vis[nbr] == false){
                // If a cycle is detected further down this path, propagate 'true' upward
                if(iscycle(graph, nbr, src, vis) == true) return true;
            }
            // Case 2: Neighbor is visited AND it's NOT the node we just came from (parent)
            // This means we found an alternative path to an already visited node -> Cycle detected!
            else if(vis[nbr] == true && nbr != parent) {
                return true;
            }
            // Case 3: Neighbor is visited AND it IS the parent node
            else {
                // Do nothing. This is just moving backward along the exact same edge.
                // vis[nbr] == true && nbr == parent return false;
            }
        }

        // If the entire DFS path finishes without hitting Case 2, no cycle exists here
        return false;
    }

    public boolean validTree(int n, int[][] edges) {
        // Array to keep track of all visited nodes during traversal
        boolean[] vis = new boolean[n];
        
        // Step 1: Initialize the adjacency list structure for the graph
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for(int i = 0; i < n; i++){
            graph.add(new ArrayList<>());
        }

        // Step 2: Build the undirected graph using the input edges array
        for(int i = 0; i < edges.length; i++){
            int u = edges[i][0];
            int v = edges[i][1];

            // Because the graph is undirected, add edges in both directions
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        // Step 3: CHECK 1 - Look for cycles starting from an arbitrary node (node 0)
        // Pass -1 as the initial parent because node 0 has no parent
        if(iscycle(graph, 0, -1, vis)){
            return false; // If a cycle is found, it cannot be a valid tree
        }

        // Step 4: CHECK 2 - Verify graph connectivity
        // Check if any node was left unvisited by our DFS traversal
        for(int i = 0; i < n; i++){
            if(vis[i] == false) {
                return false; // Found an isolated node/component -> Graph is disconnected
            }
        }

        // If it passes both the cycle check and the connectivity check, it's a valid tree
        return true;
    }
}
