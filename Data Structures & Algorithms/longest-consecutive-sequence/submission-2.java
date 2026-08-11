class Solution {
    public int longestConsecutive(int[] nums) {
        int n = nums.length;
        if(n == 0) return 0;
        HashSet<Integer> set = new HashSet<>();
        for(int i=0; i<n; i++){
            set.add(nums[i]);
        }

        int ans = 0;

        for(int i=0; i<n; i++){
            // if curr element starting of the sequence
            if(set.contains(nums[i]) && set.contains(nums[i] -1) == false){
                int curr = nums[i], cnt = 0;

                // Then check for next elements in the sequence
                while(set.contains(curr)){
                    set.remove(curr);
                    curr++;
                    cnt++;
                }

                ans = Math.max(ans, cnt);

            }
        }

        return ans;
    }
}