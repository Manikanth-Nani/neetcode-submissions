
class Solution {

    public int LIS(int[] nums, int st, int prev, int[][] dp){

        // Base case: no more elements left to process
        if(st == nums.length) return 0;

        // Check memo table using prev + 1 because prev can be -1
        if(dp[st][prev + 1] != -1) return dp[st][prev + 1];

        int pick = 0;

        // Pick nums[st] only if it keeps the subsequence strictly increasing
        if(prev == -1 || nums[st] > nums[prev]){

            // Include current element and move forward
            pick = 1 + LIS(nums, st + 1, st, dp);

        }

        // Skip current element and move forward without changing prev
        int notpick = LIS(nums, st + 1, prev, dp);

        // Return the better of the two choices
        return dp[st][prev + 1] = Math.max(pick, notpick);

    }

    public int lengthOfLIS(int[] nums) {

        int n = nums.length;
        int[][] dp = new int[n][n + 1]; // prev: -1 to n-1, shift by 1 so 0 to n

        for(int i = 0; i < n; i++){
            Arrays.fill(dp[i], -1);
        }

        // Start recursion with no previous element chosen
        return LIS(nums, 0, -1, dp);

    }

}
