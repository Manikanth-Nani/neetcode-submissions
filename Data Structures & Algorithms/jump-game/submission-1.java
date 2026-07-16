class Solution {
    public boolean canJump(int[] nums) {
        int goal = nums.length - 1;

        // Scan from the second-to-last index toward the beginning.
        for (int current = nums.length - 2; current >= 0; current--) {

            // If current can reach the current goal,
            // current becomes the new goal.
            if (current + nums[current] >= goal) {
                goal = current;
            }
        }

        // If index 0 becomes the goal, the last index is reachable.
        return goal == 0;
    }
}
