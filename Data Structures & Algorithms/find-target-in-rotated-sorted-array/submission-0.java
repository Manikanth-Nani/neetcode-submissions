class Solution {
    public int search(int[] nums, int target) {
        int n = nums.length;

        // Initialize two pointers for the search boundaries
        int l = 0, h = n - 1;

        while (l <= h) {
            int mid = l + (h - l) / 2;

            // Target found: immediately return its index
            if (nums[mid] == target) {
                return mid;
            }
            
            // Case 1: Check if the left half of the current range is strictly sorted
            else if (nums[mid] >= nums[l]) { 
                // If the target falls within the bounds of this sorted left half
                if (nums[l] <= target && target < nums[mid]) { // Fixed bug: changed <= nums[mid] to < nums[mid] since target != nums[mid]
                    h = mid - 1; // Eliminate the right half; search left
                } 
                // Target is not in the left sorted half
                else {
                    l = mid + 1; // Eliminate the left half; search right
                }
            }
            
            // Case 2: The right half must be sorted if the left half isn't
            else {
                // If the target falls within the bounds of this sorted right half
                if (nums[mid] < target && target <= nums[h]) { 
                    l = mid + 1; // Eliminate the left half; search right
                } 
                // Target is not in the right sorted half
                else {
                    h = mid - 1; // Eliminate the right half; search left
                }
            }
        }
        return -1;
    }
}
