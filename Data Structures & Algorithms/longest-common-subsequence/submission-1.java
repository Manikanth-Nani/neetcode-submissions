class Solution {

    // Returns the LCS length for text1[0..i] and text2[0..j]
    public int findLCS(String text1, String text2, int i, int j, int[][] dp) {

        // If either string is exhausted, no common subsequence is possible
        if (i < 0 || j < 0) return 0;

        // Return cached result if this subproblem was already solved
        if (dp[i][j] != -1) return dp[i][j];

        // If current characters match, include this character and move diagonally
        if (text1.charAt(i) == text2.charAt(j)) {
            return dp[i][j] = 1 + findLCS(text1, text2, i - 1, j - 1, dp);
        }

        // Otherwise, skip one character from either string and take the best result
        return dp[i][j] = Math.max(
            findLCS(text1, text2, i, j - 1, dp),
            findLCS(text1, text2, i - 1, j, dp)
        );
    }

    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();

        // dp[i][j] stores the LCS length for text1[0..i] and text2[0..j]
        int[][] dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }

        return findLCS(text1, text2, n - 1, m - 1, dp);
    }
}
