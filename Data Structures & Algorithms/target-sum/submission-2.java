class Solution {

    // Returns the number of subsets using nums[0...i]
    // whose sum is equal to remainingTarget.
    private int countSubsets(int[] nums, int i, int remainingTarget, int[][] dp) {

        // No elements remain.
        // This is one valid subset only if the required sum is 0.
        if (i < 0) {
            return remainingTarget == 0 ? 1 : 0;
        }

        // Return already computed result for this state.
        if (dp[i][remainingTarget] != -1) return dp[i][remainingTarget];

        // Option 1: Do not include nums[i].
        int notPick = countSubsets(nums, i - 1, remainingTarget, dp);

        // Option 2: Include nums[i], only if it does not make target negative.
        int pick = 0;
        if (nums[i] <= remainingTarget) {
            pick = countSubsets(nums, i - 1, remainingTarget - nums[i], dp);
        }

        // Store and return total ways.
        return dp[i][remainingTarget] = notPick + pick;
    }

    public int findTargetSumWays(int[] nums, int target) {

        int totalSum = 0;

        // Compute total sum of all numbers.
        for (int num : nums) {
            totalSum += num;
        }

        /*
         * Let:
         * positiveSum - negativeSum = target
         * positiveSum + negativeSum = totalSum
         *
         * So:
         * negativeSum = (totalSum - target) / 2
         */
        int difference = totalSum - target;

        // If target is impossible or the difference is odd, no answer exists.
        if (Math.abs(target) > totalSum || difference % 2 != 0) {
            return 0;
        }

        int subsetTarget = difference / 2;

        // dp[i][sum] = number of ways to form 'sum' using nums[0...i]
        int[][] dp = new int[nums.length][subsetTarget + 1];

        // Initialize all states as unsolved.
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return countSubsets(nums, nums.length - 1, subsetTarget, dp);
    }
}
