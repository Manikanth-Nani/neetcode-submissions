class Solution {
    public int trap(int[] height) {
        int n = height.length;
        if(n==1){
            return 0;
        }
        // calculate right max and its index
        int rmax = height[0];
        int rmax_idx = -1;
        for(int i=1; i<n; i++){
            if(height[i] > rmax){
                rmax = height[i];
                rmax_idx = i;
            }
        }
        // on the go maintain left max and caluclate water stored till maxi idx 
        int lmax = height[0];
        int water = 0;
        for(int i=0; i<rmax_idx; i++){
            lmax = Math.max(lmax, height[i]);
            water += Math.min(lmax, rmax) - height[i];
        }

        // now right max will be left max from r maxi idx to n-1
        lmax = rmax;
        rmax = height[n-1];
        for(int i=n-2; i>rmax_idx; i--){
            rmax = Math.max(rmax, height[i]);
            water += Math.min(lmax, rmax) - height[i];
        }
        return water;
    }
}