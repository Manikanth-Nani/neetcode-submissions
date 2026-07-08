class Solution {
    public int mincoins(int[] coins, int amount, int[] dp) {

        // Base case: If the remaining amount is 0, no more coins are needed
        if (amount == 0) return 0;
        
        // Memoization check: If we have already calculated the answer for this amount, reuse it
        if (dp[amount] != -1) return dp[amount];
        
        // Initialize minval to maximum integer value (acts as infinity)
        int minval = Integer.MAX_VALUE;
        
        // Loop through every available coin type
        for (int i = 0; i < coins.length; i++) {
            // Only consider the coin if its value is less than or equal to the remaining amount
            if (coins[i] <= amount) {
                
                // Recursively find the minimum coins needed for the remaining amount
                int res = mincoins(coins, amount - coins[i], dp);
                
                // BUG FIX: Only add 1 if the deeper path actually found a valid solution.
                // This prevents Integer.MAX_VALUE + 1 which causes a negative integer overflow.
                if (res != Integer.MAX_VALUE) {
                    minval = Math.min(minval, 1 + res);
                }
            }
        }

        // Cache the calculated minimum value in the dp array before returning it
        return dp[amount] = minval;
    }
    
    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        
        // dp[i] will store the minimum number of coins needed to make amount 'i'
        int[] dp = new int[amount + 1];
        
        // Initialize the array with -1 to indicate that no amounts have been calculated yet
        Arrays.fill(dp, -1);
        
        // Start the recursive tracking from the target amount
        int res = mincoins(coins, amount, dp);
        
        // If res is still Integer.MAX_VALUE, it means the amount cannot be formed by any coin combination
        if (res == Integer.MAX_VALUE) return -1;
        
        // Return the final minimum number of coins
        return res;
    }
}
