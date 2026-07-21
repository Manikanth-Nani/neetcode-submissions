class Solution {

    public int findTargetSumWays(int[] nums, int target) {

        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        int difference = totalSum - target;

        if (Math.abs(target) > totalSum || difference % 2 != 0) {
            return 0;
        }

        int subsetTarget = difference / 2;
        int n = nums.length;

        int[][] dp = new int[n + 1][subsetTarget + 1];

        // Base case:
        // There is 1 way to make sum 0 using 0 elements: choose nothing.
        dp[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int sum = 0; sum <= subsetTarget; sum++) {

                int notPick = dp[i - 1][sum];

                int pick = 0;
                if (nums[i - 1] <= sum) {
                    pick = dp[i - 1][sum - nums[i - 1]];
                }

                dp[i][sum] = notPick + pick;
            }
        }

        return dp[n][subsetTarget];
    }
}
