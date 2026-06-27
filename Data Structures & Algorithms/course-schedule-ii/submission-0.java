class Solution {

    public boolean dfs(List<List<Integer>> graph, int src, boolean[] vis, boolean[] path, Stack<Integer> st){
        // Mark the current node as visited globally and add it to the current recursion path
        vis[src] = true;
        path[src] = true;
        
        // Explore all neighbors/dependencies of the current course
        for(int nbr: graph.get(src)){
            // If the neighbor is already in the current recursion path, a cycle exists
            if(path[nbr] == true) return true;
            
            // If the neighbor hasn't been visited yet, recursively explore it
            if(vis[nbr] == false){
                if(dfs(graph, nbr, vis, path, st)){
                    return true; // Forward the cycle detection up the call stack
                }
            }
        }
        
        // Backtracking: remove the current node from the recursion path
        path[src] = false;
        
        // Post-order processing: push the course onto the stack after processing all its dependencies
        st.push(src);
        
        return false;
    }
    
    
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // Create an adjacency list to represent the graph
        List<List<Integer>> graph = new ArrayList<>();
        
        // vis tracks whether a course has been fully evaluated globally across all DFS runs
        boolean[] vis = new boolean[numCourses];
        // path tracks whether a course is part of the active DFS recursion branch (used for cycle detection)
        boolean[] path = new boolean[numCourses];

        // Initialize graph with empty lists for each course
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>()); 
        }

        // Build the dependency graph
        // An edge from v to u means course v must be taken before course u (v -> u)
        for (int i = 0; i < prerequisites.length; i++) {
            int u = prerequisites[i][0]; // Course that depends on course v
            int v = prerequisites[i][1]; // Course that needs to be taken first

            graph.get(v).add(u); // Add directed edge v -> u
        }

        // Stack to store the topological sort order
        Stack<Integer> st = new Stack<>();
        int[] ans = new int[numCourses];
        int idx = 0;
       
        // Run DFS from every unvisited node to handle disconnected graph components
        for (int i = 0; i < numCourses; i++) {
            if(vis[i] == false){
                // If a cycle is detected during DFS, a valid ordering is impossible
                if(dfs(graph, i, vis, path, st) == true){
                    return new int[]{}; // Return an empty array per problem requirements
                }
            }
        }

        // Pop elements from the stack to build the final valid course sequence
        while(st.size() > 0){
            ans[idx++] = st.pop();
        }
       
        return ans;
    }
}
