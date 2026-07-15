
class Solution {

    // Returns the number of ways to decode s starting from index i
    int ways(String s, int i, int[] dp) {

        // Reached the end exactly -> one valid decoding found
        if (i == s.length()) return 1;

        // Safety check: should not happen in a correct recursion
        if (i > s.length()) return 0;

        // A substring starting with '0' cannot be decoded
        if (s.charAt(i) == '0') return 0;

        if(dp[i] != -1) return dp[i];
        // Option 1: decode one digit
        int one = ways(s, i + 1, dp);

        // Option 2: decode two digits, if possible
        int two = 0;
        if (i + 1 < s.length()) {
            String pair = s.substring(i, i + 2);
            int val = Integer.parseInt(pair);

            // Two-digit code must be between 10 and 26
            if (val >= 10 && val <= 26) {
                two = ways(s, i + 2, dp);
            }
        }

        // Total ways from index i
        return dp[i] = one + two;
    }

    public int numDecodings(String s) {
        // If the string starts with 0, no decoding is possible
        if (s.charAt(0) == '0') return 0;
        int[] dp = new int[s.length()];
        Arrays.fill(dp, -1);
        return ways(s, 0, dp);
    }
}