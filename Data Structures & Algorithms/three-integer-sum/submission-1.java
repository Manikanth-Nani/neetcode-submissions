
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        
        // 1. Sort the array to easily handle duplicates and use two pointers
        Arrays.sort(nums);
        
        for (int i = 0; i < n - 2; i++) {
            // Skip duplicate values for the first element (i)
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            
            // Optimization: If the smallest possible sum is > 0, no triplets can sum to 0
            if (nums[i] > 0) {
                break;
            }
            
            int left = i + 1;
            int right = n - 1;
            
            // 2. Two-pointer approach for the remaining two elements
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                
                if (sum == 0) {
                    res.add(List.of(nums[i], nums[left], nums[right]));
                    
                    // Skip duplicate values for the second element (left)
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    // Skip duplicate values for the third element (right)
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    
                    // Move both pointers inward after finding a valid match
                    left++;
                    right--;
                } 
                else if (sum < 0) {
                    left++; // Sum is too small, move left pointer to increase sum
                } 
                else {
                    right--; // Sum is too large, move right pointer to decrease sum
                }
            }
        }
        
        return res;
    }
}
