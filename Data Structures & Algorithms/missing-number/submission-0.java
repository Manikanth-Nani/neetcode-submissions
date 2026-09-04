class Solution {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        
        // Calculate the sum of numbers from 0 to n using Gauss' formula
        int expectedSum = n * (n + 1) / 2;
        
        // Calculate the actual sum of elements present in the array
        int actualSum = 0;
        for (int num : nums) {
            actualSum += num;
        }
        
        // The difference is the missing number
        return expectedSum - actualSum;
    }
}
