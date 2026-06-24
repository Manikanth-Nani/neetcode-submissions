class Solution {
    class Pair{
        int i;
        int j;
        int dis;

        Pair(int i, int j, int dis){
            this.i = i;
            this.j = j;
            this.dis = dis;
        }
    }
    public void islandsAndTreasure(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        Queue<Pair> q = new LinkedList<>();
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(grid[i][j] == 0){
                    q.add(new Pair(i, j, 0));
                }
            }
        }

        while(q.size()> 0){
            Pair rem = q.remove();
            int r = rem.i;
            int c = rem.j;
            int dis = rem.dis;

            int[] dr = {-1, 0, 1, 0};
            int[] dc = {0, 1, 0, -1};

            for(int d =0; d<4; d++){
                int ni = r + dr[d];
                int nj = c + dc[d];

                if(ni>=0 && nj>=0 && ni<n && nj<m && grid[ni][nj] == 2147483647){
                    q.add(new Pair(ni, nj, dis+1));
                    grid[ni][nj] = dis+1;
                }
            }

        }
    }
}
