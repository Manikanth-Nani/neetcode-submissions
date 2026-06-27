class Solution {
    public void dfs(ArrayList<ArrayList<Integer>> graph, int src, boolean[] vis){
        vis[src] = true;
        for(int nbr: graph.get(src)){
            if(vis[nbr] == false){
                dfs(graph, nbr, vis);
            }
        }
    }
    public int countComponents(int n, int[][] edges) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for(int i=0; i<n; i++){
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<edges.length; i++){
            int u = edges[i][0];
            int v = edges[i][1];

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        int count=0;
        boolean[] vis = new boolean[n];

        for(int i=0; i<n; i++){
            if(vis[i] == false){
                count++;
                dfs(graph, i, vis);
            }
        }

        return count;
    }
}
