
class Solution {
    public int numDecodings(String s) {
        int n = s.length();

        // dp[i] = number of ways to decode the substring s[i...n-1]
        int[] dp = new int[n + 1];

        // Base case:
        // An empty string has 1 valid decoding path
        dp[n] = 1;

        // Fill from right to left because dp[i] depends on dp[i+1] and dp[i+2]
        for (int i = n - 1; i >= 0; i--) {

            // A substring starting with '0' cannot be decoded
            if (s.charAt(i) == '0') {
                dp[i] = 0;
                continue;
            }

            // Option 1: take one digit
            dp[i] = dp[i + 1];

            // Option 2: take two digits if valid
            if (i + 1 < n) {
                int num = Integer.parseInt(s.substring(i, i + 2));
                if (num >= 10 && num <= 26) {
                    dp[i] += dp[i + 2];
                }
            }
        }

        return dp[0];
    }
}