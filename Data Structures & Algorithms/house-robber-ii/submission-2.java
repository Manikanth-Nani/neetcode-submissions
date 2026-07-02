class Solution {

    // Solves the linear House Robber problem for houses in the range [start ... end].
    // We only keep two DP states:
    // prev1 = best answer up to the previous house
    // prev2 = best answer up to the house before the previous one
    //
    // For each house i:
    // - skip it  -> keep prev1
    // - rob it   -> nums[i] + prev2
    // Then choose the better option.
    private int robLinear(int[] nums, int start, int end) {
        int prev1 = 0;
        int prev2 = 0;

        for (int i = start; i <= end; i++) {
            int skipCurrent = prev1;
            int robCurrent = nums[i] + prev2;

            int current = Math.max(skipCurrent, robCurrent);

            // Shift the DP window forward by one house.
            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }

    public int rob(int[] nums) {
        int n = nums.length;

        // If there is only one house, rob it directly.
        if (n == 1) {
            return nums[0];
        }

        // Houses form a circle, so the first and last houses are adjacent.
        // We cannot rob both in the same plan.
        //
        // Split into two linear cases:
        // 1) Rob houses from index 0 to n - 2
        // 2) Rob houses from index 1 to n - 1
        //
        // The final answer is the maximum of these two cases.
        int maxRobFromFirstRange = robLinear(nums, 0, n - 2);
        int maxRobFromSecondRange = robLinear(nums, 1, n - 1);

        return Math.max(maxRobFromFirstRange, maxRobFromSecondRange);
    }
}
