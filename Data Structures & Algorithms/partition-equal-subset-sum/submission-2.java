class Solution {

    public boolean canPartition(int[] nums) {

        int n = nums.length;
        int totalSum = 0;

        // Compute the sum of all elements in the array
        for (int i = 0; i < n; i++) totalSum += nums[i];

        // If total sum is odd, it cannot be split into two equal parts
        if (totalSum % 2 != 0) return false;

        // We need to check whether some subset has sum = totalSum / 2
        int target = totalSum / 2;

        /*
         * dp[i][j] = 1 if we can form sum 'j' using the first i elements
         *            nums[0...i-1] -> sum 0 can be formed by picking the 0 elements
         * dp[i][j] = 0 otherwise
         */
        int[][] dp = new int[n + 1][target + 1];

        // Sum 0 is always possible: choose no elements
        for (int i = 0; i <= n; i++) dp[i][0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= target; j++) {

                // Case 1: do not take current element
                int notPick = dp[i - 1][j];

                // Case 2: take current element, if it fits
                int pick = 0;
                if (nums[i - 1] <= j) {
                    pick = dp[i - 1][j - nums[i - 1]];
                }

                // If either choice works, mark this state as possible
                dp[i][j] = (notPick == 1 || pick == 1) ? 1 : 0;
            }
        }

        // Final answer: can we form sum = target using all elements?
        return dp[n][target] == 1;
    }
}
