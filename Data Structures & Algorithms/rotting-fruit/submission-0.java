class Solution {
    class Pair{
        int i;
        int j;
        int time;

        Pair(int i, int j, int time){
            this.i = i;
            this.j = j;
            this.time = time;
        }
    }
    public int orangesRotting(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int freshorg = 0;
        Queue<Pair> q = new LinkedList<>();
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(grid[i][j] == 2){
                    q.add(new Pair(i, j, 0));
                }
                else if (grid[i][j] == 1){
                    freshorg++;
                }
            }
        }

        int tot_time = 0;
        while(q.size()> 0){
            Pair rem = q.remove();
            int r = rem.i;
            int c = rem.j;
            tot_time = rem.time;

            int[] dr = {-1, 0, 1, 0};
            int[] dc = {0, 1, 0, -1};

            for(int d =0; d<4; d++){
                int ni = r + dr[d];
                int nj = c + dc[d];

                if(ni>=0 && nj>=0 && ni<n && nj<m && grid[ni][nj] == 1){
                    q.add(new Pair(ni, nj, tot_time+1));
                    grid[ni][nj] = 2;
                    freshorg--;
                }
            }

        }

        if(freshorg != 0) return -1;
        return tot_time;
    }
}

