class Solution {

    public int maxProduct(int[] nums) {
        int n = nums.length;

        // Store the maximum product found so far
        int res = Integer.MIN_VALUE;

        // prefix = product from left to right
        // suffix = product from right to left
        int prefix = 1;
        int suffix = 1;

        for (int i = 0; i < n; i++) {

            // If product becomes 0, restart from 1
            // because a subarray cannot continue through zero
            if (prefix == 0) prefix = 1;
            if (suffix == 0) suffix = 1;

            // Multiply current number from the left
            prefix *= nums[i];

            // Multiply current number from the right
            suffix *= nums[n - i - 1];

            // Update answer using both directions
            res = Math.max(res, Math.max(prefix, suffix));
        }

        return res;
    }
}
