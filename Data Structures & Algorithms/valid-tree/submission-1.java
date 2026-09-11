class Solution {
    public boolean iscycle(ArrayList<ArrayList<Integer>> adj, int src, int parent, boolean[] vis){

        vis[src] = true;

        for(int nbr : adj.get(src)){
            if(vis[nbr] == false){

                if(iscycle(adj, nbr, src, vis)) return true;
            }
            else if(vis[nbr] && nbr != parent){
                return true;
            }
        }

        return false;
    }
    public boolean validTree(int n, int[][] edges) {

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for(int i=0; i<n; i++){
            adj.add(new ArrayList<>());
        }

        for(int i=0; i<edges.length; i++){
            int u = edges[i][0];
            int v = edges[i][1];

            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        boolean[] vis = new boolean[n];

        if(iscycle(adj, 0, -1, vis)){
            return false;
        }

        for(int i = 0; i < n; i++){
            if(vis[i] == false) {
                return false;
            }
        }
        return true
        ;
    }
}
