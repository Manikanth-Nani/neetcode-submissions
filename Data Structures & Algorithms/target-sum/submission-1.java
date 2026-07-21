class Solution {

    // Returns the number of subsets using nums[0...i]
    // whose sum is equal to remainingTarget.
    private int countSubsets(int[] nums, int i, int remainingTarget) {

        // No elements remain.
        // This is one valid subset only if the required sum is 0.
        if (i < 0) {
            return remainingTarget == 0 ? 1 : 0;
        }

        // Option 1: Do not include nums[i].
        int notPick = countSubsets(nums,i - 1,remainingTarget);

        // Option 2: Include nums[i], but only if it does not
        // make the remaining target negative.
        int pick = 0;

        if (nums[i] <= remainingTarget) {
            pick = countSubsets(nums,i - 1,remainingTarget - nums[i]);
        }

        // Total subsets from both choices.
        return notPick + pick;
    }

    public int findTargetSumWays(int[] nums, int target) {

        int totalSum = 0;

        for (int num : nums) {
            totalSum += num;
        }

        /*
         * Let:
         * positiveSum - negativeSum = target
         * positiveSum + negativeSum = totalSum
         *
         * Therefore:
         * negativeSum = (totalSum - target) / 2
         */
        int difference = totalSum - target;

        // No solution if the target is outside [-totalSum, totalSum]
        // or if the difference cannot be divided equally.
        if (Math.abs(target) > totalSum || difference % 2 != 0) {
            return 0;
        }

        int subsetTarget = difference / 2;

        return countSubsets(nums, nums.length - 1, subsetTarget);
    }
}
