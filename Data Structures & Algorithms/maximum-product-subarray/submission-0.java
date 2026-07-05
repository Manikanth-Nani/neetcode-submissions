class Solution {
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int res = Integer.MIN_VALUE;

        for(int i=0; i<n; i++){

            int curr = 1;
            for(int j=i; j<n; j++){

                curr = curr * nums[j];
                res= Math.max(res, curr);
            }
        }

        return res;
    }
}
