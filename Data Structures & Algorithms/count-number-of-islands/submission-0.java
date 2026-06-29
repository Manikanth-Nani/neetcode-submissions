class Solution {
    public void dfs(char[][] grid, int i, int j){
        grid[i][j] = '0';

        int[] dr = {1, 0, -1, 0};
        int[] dc = {0, 1, 0, -1};

        for(int d =0; d<4; d++){
            int ni = i + dr[d];
            int nj = j + dc[d];

            if(ni>=0 && nj>=0 && ni<grid.length && nj<grid[0].length && grid[ni][nj] == '1'){
                dfs(grid, ni, nj);
            }
        }
    }
    public int numIslands(char[][] grid) {
        

        int count= 0;
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[0].length; j++){
                if(grid[i][j] == '1'){
                    dfs(grid, i, j);
                    count++;
                }
            }
        }

        return count;
    }
}
