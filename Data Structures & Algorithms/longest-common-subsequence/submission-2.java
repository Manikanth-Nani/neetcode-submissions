class Solution {

    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();

        // dp[row][col] stores the LCS length for:
        // text1[0..row-1] and text2[0..col-1]
        int[][] dp = new int[n + 1][m + 1];

        // Row 0 and column 0 remain 0
        // because an empty string has LCS length 0 with any string

        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= m; col++) {

                // If current characters match, include it in the LCS
                if (text1.charAt(row - 1) == text2.charAt(col - 1)) {
                    dp[row][col] = 1 + dp[row - 1][col - 1];
                } 
                // Otherwise, skip one character from either string
                else {
                    dp[row][col] = Math.max(dp[row - 1][col], dp[row][col - 1]);
                }
            }
        }

        // Bottom-right cell stores the final LCS length
        return dp[n][m];
    }
}
