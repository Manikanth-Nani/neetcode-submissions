class Solution {
    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        
        // dp[i] will store the minimum number of coins needed to make amount 'i'
        int[] dp = new int[amount + 1];
        
        // Base case: 0 coins are needed to make an amount of 0
        dp[0] = 0;
        
        // Loop through all sub-amounts from 1 up to our target amount
        for (int i = 1; i <= amount; i++) {
            int minval = Integer.MAX_VALUE;
            
            // Try every coin for the current amount 'i'
            for (int j = 0; j < n; j++) {
                if (coins[j] <= i) {
                    int res = dp[i - coins[j]];
                    
                    // Only update if the remaining amount had a valid solution
                    if (res != Integer.MAX_VALUE) {
                        minval = Math.min(minval, res + 1);
                    }
                }   
            }

            // Save the best result for amount 'i'
            dp[i] = minval;
        }

        // If dp[amount] is still infinity, it's impossible to make change
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
}
