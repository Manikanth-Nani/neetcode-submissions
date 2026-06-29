class Solution {
    
    public int find(int[] parent, int v) {
        // Base case: If a node points to itself, it is the root of its set
        if (parent[v] == v) return v;
        
        // Path Compression optimization:
        // Recursively find the ultimate root and point the current node 'v'
        // directly to it. This flattens the tree for future O(1) lookups.
        return parent[v] = find(parent, parent[v]); 
    }

    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        
        // Vertices are 1-indexed (1 to n), so size is n + 1
        int[] parent = new int[n + 1];
        
        // Make Set: Initialize every vertex to be its own parent/group leader
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        // Process each edge sequentially
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            // Step 1: Find the ultimate group leaders (roots) of both nodes
            int up = find(parent, u); // Root of node u
            int vp = find(parent, v); // Root of node v

            // Step 2: Check for a cycle
            // If both nodes already share the exact same root, they are
            // already connected. Adding this edge creates a redundant loop.
            if (up == vp) return edge;

            // Step 3: Union Operation
            // Merges the two sets by connecting the ROOT of u to the ROOT of v
            parent[up] = vp; 
        }

        // Return an empty array if no redundant connection is found
        return new int[0];
    }
}
