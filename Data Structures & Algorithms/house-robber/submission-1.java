class Solution {
    public int maxprofit(int[] nums, int n, int[] dp){
        if(n < 0) return 0;
        if(dp[n] != -1) return dp[n];
        //rob curr
        int rob = nums[n] + maxprofit(nums, n-2, dp);
        int norob = maxprofit(nums, n-1, dp);

        return dp[n] = Math.max(rob, norob);
    }
    public int rob(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);

        return maxprofit(nums, n-1, dp);
    }
}
