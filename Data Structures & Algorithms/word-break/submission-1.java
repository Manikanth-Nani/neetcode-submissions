class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        // Convert list to set for O(1) average lookup time
        HashSet<String> set = new HashSet<>(wordDict);

        // memo[start] stores whether s.substring(start) can be segmented
        // null means "not computed yet"
        Boolean[] memo = new Boolean[s.length()];

        return dfs(s, 0, set, memo);
    }

    private boolean dfs(String s, int start, HashSet<String> set, Boolean[] memo) {
        // If we have reached the end, the whole string is successfully segmented
        if (start == s.length()) return true;

        // Return cached result if already computed
        if (memo[start] != null) return memo[start];

        // Try every possible end index for the next word
        for (int end = start + 1; end <= s.length(); end++) {
            // If current prefix is a word and the remaining suffix is breakable
            if (set.contains(s.substring(start, end)) && dfs(s, end, set, memo)) {
                memo[start] = true;
                return true;
            }
        }

        // No valid split found from this start index
        memo[start] = false;
        return false;
    }
}
