
class Solution {

    public int maxProduct(int[] nums) {
        // If only one number exists, that number is the answer
        if (nums.length == 1) {
            return nums[0];
        }

        // maxProd = maximum product of a subarray ending at current index
        // minProd = minimum product of a subarray ending at current index
        int maxProd = nums[0];
        int minProd = nums[0];

        // Global answer
        int ans = nums[0];

        // Start from index 1 because index 0 is already used to initialize
        for (int i = 1; i < nums.length; i++) {
            int curr = nums[i];

            // Store previous maxProd before updating it
            // because minProd also depends on the old value
            int prevMax = maxProd;
            int prevMin = minProd;

            // For current element, the new maximum product can be:
            // 1) the current number itself
            // 2) current number * previous maximum product
            // 3) current number * previous minimum product
            maxProd = Math.max(curr, Math.max(curr * prevMax, curr * prevMin));

            // Similarly, the new minimum product can also come from
            // the same three possibilities
            minProd = Math.min(curr, Math.min(curr * prevMax, curr * prevMin));

            // Update the global maximum answer
            ans = Math.max(ans, maxProd);
        }

        return ans;
    }
}
