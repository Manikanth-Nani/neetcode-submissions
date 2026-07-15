
class Solution {
    public int numDecodings(String s) {
        int n = s.length();

        // dp[i] = number of ways to decode the first i characters of s
        int[] dp = new int[n + 1];

        // Empty string has one valid decoding
        dp[0] = 1;

        // First character
        dp[1] = (s.charAt(0) == '0') ? 0 : 1;

        // Build the answer from left to right
        for (int i = 2; i <= n; i++) {

            // Check if the last one digit can be decoded
            if (s.charAt(i - 1) != '0') {
                dp[i] += dp[i - 1];
            }

            // Check if the last two digits form a valid code
            int twoDigit = Integer.parseInt(s.substring(i - 2, i));
            if (twoDigit >= 10 && twoDigit <= 26) {
                dp[i] += dp[i - 2];
            }
        }

        return dp[n];
    }
}
