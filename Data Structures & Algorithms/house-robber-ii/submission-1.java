
class Solution {

    // Solves the linear House Robber problem for houses in the range [start ... end].
    // dp[i] stores the maximum money that can be robbed from the subarray
    // nums[start ... i], considering the houses up to index i.
    private int robLinear(int[] nums, int start, int end) {
        // Base case: only one house in the range.
        if (start == end) {
            return nums[start];
        }

        int[] dp = new int[nums.length];

        // First house in this range.
        dp[start] = nums[start];

        // Second house in this range.
        // Choose the better of robbing the first or the second house.
        dp[start + 1] = Math.max(nums[start], nums[start + 1]);

        // Build the DP table from left to right.
        for (int i = start + 2; i <= end; i++) {
            // Option 1: skip current house -> take best up to i - 1
            // Option 2: rob current house -> nums[i] + best up to i - 2
            dp[i] = Math.max(dp[i - 1], nums[i] + dp[i - 2]);
        }

        return dp[end];
    }

    public int rob(int[] nums) {
        int n = nums.length;

        // If there is only one house, rob it directly.
        if (n == 1) {
            return nums[0];
        }

        // Houses form a circle, so first and last houses are adjacent.
        // We cannot rob both in the same plan.
        //
        // Therefore, split into two linear cases:
        // 1) Rob houses from index 0 to n - 2
        // 2) Rob houses from index 1 to n - 1
        //
        // The final answer is the maximum of these two cases.
        int maxRobFromFirstRange = robLinear(nums, 0, n - 2);
        int maxRobFromSecondRange = robLinear(nums, 1, n - 1);

        return Math.max(maxRobFromFirstRange, maxRobFromSecondRange);
    }
}
