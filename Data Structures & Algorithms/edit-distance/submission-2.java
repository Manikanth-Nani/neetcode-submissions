class Solution {

    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        // DP table uses 1-based indexing for easier boundary handling.
        // dp[i][j] means edit distance for word1[0..i-1] and word2[0..j-1]
        int[][] dp = new int[n+1][m+1];

        // Initialize base cases:
        // dp[i][0]: converting first i chars of word1 to empty string (all deletions)
        for(int i = 0; i <= n; i++) dp[i][0] = i;
        // dp[0][j]: converting empty string to first j chars of word2 (all insertions)
        for(int j = 0; j <= m; j++) dp[0][j] = j; 

        // Fill the DP table row by row
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                // If last characters match, inherit optimal answer from previous substrings
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // Otherwise, choose the best among: replace, delete, or insert
                    // Replace: change word1[i-1] to word2[j-1], move diagonally
                    int replace = dp[i-1][j-1];
                    // Delete: remove word1[i-1], move up
                    int delete = dp[i-1][j];
                    // Insert: add word2[j-1] to word1, move left
                    int insert = dp[i][j-1];

                    // Choose operation with minimum cost, add 1 for the current step
                    dp[i][j] = 1 + Math.min(replace, Math.min(delete, insert));
                }
            }
        }

        
        return dp[n][m];
    }
}