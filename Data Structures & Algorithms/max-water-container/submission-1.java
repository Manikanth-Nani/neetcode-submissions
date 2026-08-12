class Solution {
    public int maxArea(int[] height) {
        int maxWater = 0;
        int left = 0;
        int right = height.length - 1;
        
        // Loop until the two pointers meet in the middle
        while (left < right) {
            // Width is the horizontal distance between the two lines
            int width = right - left;
            
            // The water level is limited by the shorter of the two lines (the bottleneck)
            int currentHeight = Math.min(height[left], height[right]);
            
            // Calculate the area for the current boundaries
            int currentArea = currentHeight * width;
            
            // Update the maximum water volume found so far
            maxWater = Math.max(maxWater, currentArea);
            
            // Move the pointer that points to the shorter line inward.
            // Keeping the shorter line while shrinking the width can never yield a larger area.
            if (height[left] < height[right]) {
                left++; // Discard the shorter left line
            } else {
                right--; // Discard the shorter right line (or equal to left)
            }
        }
        
        return maxWater;
    }
}
