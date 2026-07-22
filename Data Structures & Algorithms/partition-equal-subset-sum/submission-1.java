class Solution {
    private int canFormTarget(int[] nums, int index, int target, int[][] dp) {

        // If no elements are left, only target = 0 is valid.
        if (index < 0) return target == 0 ? 1 : 0;

        // Return already computed answer for this state.
        if (dp[index][target] != -1) return dp[index][target];

        // Option 1: do not take nums[index]
        int notPick = canFormTarget(nums, index - 1, target, dp);

        // Option 2: take nums[index], only if it does not exceed target
        int pick = 0;
        if (nums[index] <= target)
            pick = canFormTarget(nums, index - 1, target - nums[index], dp);

        // If either choice forms the target, return 1
        return dp[index][target] = (notPick == 1 || pick == 1) ? 1 : 0;
    }

    public boolean canPartition(int[] nums) {

        int n = nums.length;
        int totalSum = 0;

        // Compute total sum of all elements
        for (int i = 0; i < n; i++) totalSum += nums[i];

        // If total sum is odd, it cannot be split into two equal halves
        if (totalSum % 2 != 0) return false;

        int target = totalSum / 2;

        // dp[index][target] = whether we can form 'target'
        // using nums[0...index]
        int[][] dp = new int[n][target + 1];

        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        // Check whether a subset exists with sum = totalSum / 2
        return canFormTarget(nums, n - 1, target, dp) == 1;
    }
}
