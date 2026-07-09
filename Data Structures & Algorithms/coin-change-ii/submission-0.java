class Solution {
    public int change(int amount, int[] coins) {
        int n = coins.length;
        
        // dp[i] stores the total number of combinations to make amount 'i'
        int[] dp = new int[amount + 1];
        
        // Base case: There is exactly 1 way to make an amount of 0 (by picking zero coins)
        dp[0] = 1;

        // Loop through each coin one by one. 
        // Processing coin-by-coin ensures that combinations are unique and avoids counting permutations (like [1,2] and [2,1] as separate ways).
        for (int j = 0; j < n; j++) {
            
            // For the current coin, calculate combinations for all amounts from 1 up to the target amount
            for (int i = 1; i <= amount; i++) {
                
                // Only use the coin if its value is less than or equal to the current sub-amount 'i'
                if (coins[j] <= i) {
                    
                    // The new ways to make amount 'i' equals:
                    // 1. The ways we already found WITHOUT using this coin (dp[i])
                    // 2. Plus the ways to make the remaining amount AFTER using this coin (dp[i - coins[j]])
                    dp[i] = dp[i] + dp[i - coins[j]];
                }
            }
        }

        // Return the total number of ways to make the final target amount
        return dp[amount];
    }
}
