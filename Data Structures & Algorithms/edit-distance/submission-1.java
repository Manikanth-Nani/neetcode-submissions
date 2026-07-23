class Solution {
    public int minOp(String word1, String word2, int i, int j, int[][] dp) {

        // If word1 is exhausted, we must insert all remaining characters of word2
        if (i < 0) return j + 1;

        // If word2 is exhausted, we must delete all remaining characters of word1
        if (j < 0) return i + 1;

        // Return already computed result for this state
        if (dp[i][j] != -1) return dp[i][j];

        // If current characters match, no operation is needed
        // Move diagonally to the previous characters in both strings
        if (word1.charAt(i) == word2.charAt(j)) {
            return dp[i][j] = minOp(word1, word2, i - 1, j - 1, dp);
        }

        // Operation 1: Replace word1[i] with word2[j]
        int replace = minOp(word1, word2, i - 1, j - 1, dp);

        // Operation 2: Delete word1[i]
        int delete = minOp(word1, word2, i - 1, j, dp);

        // Operation 3: Insert word2[j]
        int insert = minOp(word1, word2, i, j - 1, dp);

        // Add 1 for the current operation and take the minimum
        return dp[i][j] = 1 + Math.min(replace, Math.min(delete, insert));
    }

    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        // dp[i][j] stores the answer for word1[0...i] and word2[0...j]
        int[][] dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }

        // Start from the last characters of both strings
        return minOp(word1, word2, n - 1, m - 1, dp);
    }
}
