class Solution {
    public int maxArea(int[] height) {
        int maxWater = 0;
        int left = 0;
        int right = height.length - 1;
        
        while (left < right) {
            // Cache the current limiting height to avoid multiple Math.min calls
            int minHeight = Math.min(height[left], height[right]);
            
            // Calculate and update the max area with the current boundaries
            maxWater = Math.max(maxWater, minHeight * (right - left));
            
            // Fast-forward the left pointer: skip all lines that are shorter 
            // than or equal to the current bottleneck, as they cannot hold more water.
            while (left < right && height[left] <= minHeight) {
                left++;
            }
            
            // Fast-forward the right pointer: skip all lines that are shorter
            // than or equal to the current bottleneck.
            while (left < right && height[right] <= minHeight) {
                right--;
            }
        }
        
        return maxWater;
    }
}
