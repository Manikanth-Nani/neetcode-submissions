class Solution {
    // robber(nums, st, end, dp) = maximum money that can be robbed
    // from houses in the range [st ... end]
    public int robber(int[] nums, int st, int end, int[] dp) {

        // Base case: If end goes before st, there are no houses left to rob.
        if (end < st) {
            return 0;
        }

        // If already computed, return the stored answer.
        if (dp[end] != -1) {
            return dp[end];
        }

        // Choice 1: do not rob the current house 'end'
        int skipCurrent = robber(nums, st, end - 1, dp);

        // Choice 2: rob the current house 'end'
        // If we rob it, we cannot rob end - 1
        int takeCurrent = nums[end] + robber(nums, st, end - 2, dp);

        // Store and return the best of the two choices
        dp[end] = Math.max(skipCurrent, takeCurrent);
        return dp[end];
    }

    public int rob(int[] nums) {
        int n = nums.length;

        // Special case:
        // If there is only one house, rob it.
        if (n == 1) {
            return nums[0];
        }

        // houses are arranged in a circle.
        // That means first and last houses are neighbors,
        // so we cannot rob both together.
        //
        // Therefore, split into 2 independent cases:
        //
        // Case 1: Rob from house 0 to house n-2
        //         (exclude the last house)
        //
        // Case 2: Rob from house 1 to house n-1
        //         (exclude the first house)
        //
        // Final answer = max(case1, case2)

        int[] dp1 = new int[n];
        Arrays.fill(dp1, -1);
        int way1 = robber(nums, 0, n - 2, dp1);

        int[] dp2 = new int[n];
        Arrays.fill(dp2, -1);
        int way2 = robber(nums, 1, n - 1, dp2);

        return Math.max(way1, way2);
    }
}
