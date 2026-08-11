class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int zeroCt = 0, idx = -1;
        int prod = 1;
        for(int i=0; i<n; i++){
            if(nums[i] == 0){
                idx = i;
                zeroCt++;
            } 
            else{
                prod = prod * nums[i];
            }
        }
        int[] res = new int[n];
        if(zeroCt > 1) return res;
        else if(zeroCt == 1){
            res[idx] = prod;
            return res;
        }
        else{
            for(int i=0;i<n; i++){
                res[i] = prod/nums[i];
            }
            return res;
        }
    }
}  
