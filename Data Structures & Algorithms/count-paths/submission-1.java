class Solution {

    // Returns the number of unique paths to reach cell (row, col)
    // from the start cell (0, 0) using memoization
    public int countPaths(int row, int col, int[][] dp) {

        // Out of bounds means no valid path
        if (row < 0 || col < 0) return 0;

        // Base case: only one way to stay at the starting cell
        if (row == 0 && col == 0) return dp[row][col] = 1;

        if (dp[row][col] != -1) return dp[row][col];

        // Move left
        int left = countPaths(row, col - 1, dp);

        // Move up
        int up = countPaths(row - 1, col, dp);
        
        return dp[row][col] = left + up;
    }

    public int uniquePaths(int m, int n) {

        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], -1);
        }

        // Bottom-right cell is (m - 1, n - 1)
        return countPaths(m - 1, n - 1, dp);
    }
}
