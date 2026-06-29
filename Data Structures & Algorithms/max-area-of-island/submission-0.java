class Solution {

    // DFS returns the area of the connected island starting from cell (i, j)
    public int dfs(int[][] grid, int i, int j) {
        // Mark the current land cell as visited so we do not count it again
        grid[i][j] = 0;

        // 4-directional movement: down, right, up, left
        int[] dr = {1, 0, -1, 0};
        int[] dc = {0, 1, 0, -1};

        // Count the current cell itself
        int count = 1;

        // Explore all 4 neighbors
        for (int d = 0; d < 4; d++) {
            int ni = i + dr[d];
            int nj = j + dc[d];

            // If neighbor is inside the grid and is land, continue DFS there
            if (ni >= 0 && nj >= 0 && ni < grid.length && nj < grid[0].length && grid[ni][nj] == 1) {
                count = count + dfs(grid, ni, nj);
            }
        }

        // Return total area for this island component
        return count;
    }

    public int maxAreaOfIsland(int[][] grid) {
        int ans = 0;

        // Scan every cell in the grid
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {

                // Start DFS whenever we find an unvisited land cell
                if (grid[i][j] == 1) {
                    int area = dfs(grid, i, j);
                    ans = Math.max(ans, area);
                }
            }
        }

        return ans;
    }
}

