class Solution {
    public boolean canReach(int[] nums, int i){
        if(i >= nums.length-1) return true;
        if(nums[i] == 0) return false;

        for(int j=1; j<=nums[i]; j++){
            if(canReach(nums, i+j) == true){
                return true;
            }
        }

        return false;
    }
    public boolean canJump(int[] nums) {
        return canReach(nums, 0);
    }
}
