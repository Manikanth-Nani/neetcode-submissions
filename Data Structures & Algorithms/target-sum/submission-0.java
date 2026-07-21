class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        return ways(nums, target, 0, 0);
    }

    private int ways(int[] nums, int target, int index, int currentSum) {
        // Base Case: Once we evaluate all numbers, check if our sum matches the target
        if (index == nums.length) {
            return currentSum == target ? 1 : 0;
        }

        // Choice 1: Add the current number
        int plus = ways(nums, target, index + 1, currentSum + nums[index]);
        
        // Choice 2: Subtract the current number
        int minus = ways(nums, target, index + 1, currentSum - nums[index]);

        // Return total ways from both decisions
        return plus + minus;
    }
}
