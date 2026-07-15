/*
 * Word Break (LeetCode 139) - Dynamic Programming Solution
 *
 * Approach:
 * - Use DP where dp[i] is true if substring s[0..i-1] can be segmented into dictionary words.
 * - For each ending index i, check all possible previous split points j (where s[j..i-1] is a word).
 * - To optimize, use a HashSet for quick dictionary lookups and track max word length for pruning unnecessary checks.
 * - Base case: dp[0] = true (empty string can always be segmented)
 */

class Solution {

    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        int maxLen = Integer.MIN_VALUE;   // To record the longest word length in the dictionary

        HashSet<String> set = new HashSet<>();
        for(String word : wordDict){
            maxLen = Math.max(maxLen, word.length());
            set.add(word);
        }

        // dp[i]: True if s[0..i-1] can be segmented
        boolean[] dp = new boolean[n + 1];
        dp[0] = true; // An empty string can always be segmented

        // Check every substring ending at i
        for(int i = 1; i <= n; i++){
            // j ranges from max(0, i - maxLen) to i-1, since words longer than maxLen cannot match
            for(int j = Math.max(0, i - maxLen); j < i; j++){
                // If s[0..j-1] can be segmented and s[j..i-1] is in dictionary
                if(dp[j] && set.contains(s.substring(j, i))){
                    dp[i] = true;
                    break; // No need to check further if segmentation is possible at i
                }
            }
        }
        // The answer is whether the full string s[0..n-1] can be segmented
        return dp[n];
    }
}
