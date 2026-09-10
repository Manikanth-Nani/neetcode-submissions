class Solution {
    public void dfs(int[][] heights, int i, int j, boolean[][] vis){
        vis[i][j] = true;

        int[] dr = {1, 0, -1, 0};
        int[] dc = {0, 1, 0, -1};

        for(int d = 0; d < 4; d++){
            int ni = i + dr[d];
            int nj = j + dc[d];

            if(ni >= 0 && ni < heights.length && nj >= 0 && nj < heights[0].length && heights[ni][nj] >= heights[i][j] && !vis[ni][nj]){
                dfs(heights, ni, nj, vis);
            }
        }
    }

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> res = new ArrayList<>();
        if(heights.length == 0) return res;

        int n = heights.length;
        int m = heights[0].length;

        boolean[][] reachPac = new boolean[n][m];
        boolean[][] reachAtl = new boolean[n][m];

        // Top side (Pacific Ocean)
        for(int j = 0; j < m; j++){
            if(!reachPac[0][j]){
                dfs(heights, 0, j, reachPac);
            }
        }

        // Left side (Pacific Ocean)
        for(int i = 0; i < n; i++){
            if(!reachPac[i][0]){
                dfs(heights, i, 0, reachPac);
            }
        }

        // Bottom side (Atlantic Ocean)
        for(int j = 0; j < m; j++){
            if(!reachAtl[n - 1][j]){
                dfs(heights, n - 1, j, reachAtl);
            }
        }

        // Right side (Atlantic Ocean)
        for(int i = 0; i < n; i++){
            if(!reachAtl[i][m - 1]){
                dfs(heights, i, m - 1, reachAtl);
            }
        }

        // Find cells that can reach both oceans
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(reachPac[i][j] && reachAtl[i][j]){
                    res.add(Arrays.asList(i, j));
                }
            }
        }

        return res;
    }
}