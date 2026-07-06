class Solution {

    public int uniquePaths(int rows, int cols) {

        // dp[i][j] stores the number of ways to reach cell (i, j)
        // from the starting cell (0, 0)
        int[][] dp = new int[rows][cols];

        // First column: only one way to move straight down
        for (int row = 0; row < rows; row++) {
            dp[row][0] = 1;
        }

        // First row: only one way to move straight right
        for (int col = 0; col < cols; col++) {
            dp[0][col] = 1;
        }

        // Fill the rest of the table
        // Each cell can be reached either from the top or from the left
        for (int row = 1; row < rows; row++) {
            for (int col = 1; col < cols; col++) {
                dp[row][col] = dp[row - 1][col] + dp[row][col - 1];
            }
        }

        // Bottom-right cell contains the final answer
        return dp[rows - 1][cols - 1];
    }
}
